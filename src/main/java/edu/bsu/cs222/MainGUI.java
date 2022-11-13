package edu.bsu.cs222;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import java.io.IOException;
import java.io.InputStream;

public class MainGUI extends Application{

    Button checkButton;
    Text filmTitle = new Text();

    public static void main(String[] args) {
        launch(args);
    }
    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("Star Wars Trivia");
        // Set the layout
        GridPane grid = new GridPane();
        grid.setAlignment(Pos.CENTER);
        grid.setHgap(10);
        grid.setVgap(10);
        grid.setPadding(new Insets(25, 25, 25, 25));
        // Set up contents
        checkButton = new Button("Use the force, Luke!");
        grid.add(checkButton, 1, 0, 1, 1);
        grid.add(filmTitle, 0, 1, 3, 1);
        // code to handle button action
        checkButton.setOnAction(e -> handleButtonClick());
        // add to the stage
        Scene scene = new Scene(grid, 500, 500); // width, height
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    private void handleButtonClick() {
        checkButton.setDisable(true);
        String output;
        String targetPage = "films/1";
        Film film;
        FilmParser filmParser = new FilmParser();
        OpeningCrawlFormatter openingCrawlFormatter = new OpeningCrawlFormatter();
        // connect to API
        SWAPIconnect SWAPIconnection = new SWAPIconnect(targetPage);
        InputStream APIresponseStream = SWAPIconnection.connect();
        // process response and display
        try {
            film = filmParser.parseFilm(APIresponseStream);
            //JSONArray APIjsonResult = JsonPath.read(APIresponseStream, "$..opening_crawl");
            output = openingCrawlFormatter.format(film);
        } catch (IOException e) {
            output = "No response received from the Star Wars API site.";
        }
        filmTitle.setText(output);
        checkButton.setDisable(false);
    }
}
