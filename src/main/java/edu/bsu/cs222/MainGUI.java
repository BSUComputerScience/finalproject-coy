package edu.bsu.cs222;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.*;
import javafx.stage.Stage;
import java.io.IOException;
import java.io.InputStream;

public class MainGUI extends Application{

    final boolean TESTING = true;   // set to True to use test file, not API
    Button checkButton;
    Text filmTitle = new Text();
    Text filmOpeningCrawl = new Text();

    public static void main(String[] args) {
        launch(args);
    }
    @Override
    public void start(Stage primaryStage) {
        // Set title, background, etc
        primaryStage.setTitle("Star Wars Trivia");
        InputStream backgroundImageFile = Thread.currentThread().getContextClassLoader().getResourceAsStream("stars.jpeg");
        BackgroundImage backgroundImage = new BackgroundImage(new Image(backgroundImageFile),
                BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.DEFAULT,
                BackgroundSize.DEFAULT);
        Background background = new Background(backgroundImage);
        // Set the layout and add background image to grid
        GridPane grid = new GridPane();
        grid.setAlignment(Pos.CENTER);
        grid.setHgap(10);
        grid.setVgap(10);
        grid.setPadding(new Insets(25, 25, 25, 25));
        grid.setBackground(background);
        // Formatting
        filmTitle.setFont(Font.font("Verdana", FontWeight.BOLD, FontPosture.REGULAR, 48));
        filmTitle.setFill(Color.BLACK);
        filmTitle.setStrokeWidth(2);
        filmTitle.setStroke(Color.YELLOW);
        filmTitle.setTextAlignment(TextAlignment.CENTER);
        filmOpeningCrawl.setFont(Font.font("Lucida Sans Unicode", FontWeight.NORMAL, FontPosture.ITALIC, 20));
        filmOpeningCrawl.setFill(Color.WHITE);
        filmOpeningCrawl.setTextAlignment(TextAlignment.CENTER);
        // Set up contents
        checkButton = new Button("Use the force, Luke!");
        grid.add(checkButton, 1, 0, 1, 1);
        grid.add(filmTitle, 0, 1, 3, 1);
        grid.add(filmOpeningCrawl, 0, 2, 3, 1);
        // code to handle button action
        checkButton.setOnAction(e -> handleButtonClick());
        // add to the stage
        Scene scene = new Scene(grid, 1000, 1000); // width, height
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
        InputStream APIresponseStream;
        if (TESTING == false) {
            SWAPIconnect SWAPIconnection = new SWAPIconnect(targetPage);
            APIresponseStream = SWAPIconnection.connect();
        } else {
            String filename = String.format("films-%d.json", 1);
            APIresponseStream = Thread.currentThread().getContextClassLoader().getResourceAsStream(filename);
        }
        // process response and display
        try {
            film = filmParser.parseFilm(APIresponseStream);
            filmTitle.setText(film.getTitle());
            filmOpeningCrawl.setText(openingCrawlFormatter.format(film));

        } catch (IOException e) {
            output = "No response received from the Star Wars API site.";
            filmOpeningCrawl.setText(output);
        }
        checkButton.setDisable(false);
    }
}
