package edu.bsu.cs222;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.image.Image;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.*;
import javafx.stage.Stage;
import java.io.IOException;
import java.io.InputStream;

public class MainGUI extends Application{

    final boolean TESTING = false;   // set to True to use test file, not API
    Button checkButton;
    Text filmTitle = new Text();
    Text filmOpeningCrawl = new Text();
    final String[] filmList = { "A New Hope", "The Empire Strikes Back", "Return of the Jedi",
            "The Phantom Menace", "Attack of the Clones", "Revenge of the Sith"};
    ComboBox<String> selectMovie = new ComboBox<String>(FXCollections.observableArrayList(filmList));

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
        grid.add(selectMovie, 0, 0, 1, 1);
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
        Film film;
        FilmParser filmParser = new FilmParser();
        OpeningCrawlFormatter openingCrawlFormatter = new OpeningCrawlFormatter();
        // set up request
        String targetPage = "films/" + getIndexFromTitle(selectMovie.getValue());
        // connect to API
        InputStream APIresponseStream;
        if (TESTING == false) {
            SWAPIconnect SWAPIconnection = new SWAPIconnect(targetPage);
            APIresponseStream = SWAPIconnection.connect();
        } else {
            String filename = String.format("films-%d.json", getIndexFromTitle(selectMovie.getValue()));
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

    private int getIndexFromTitle(String title) {
        for (int i=0; i<filmList.length; i++) {
            if (title.equals(filmList[i])) {
                return (i+1); // A New Hope is located at [0], but has SWAPI index of 1
            }
        }
        // did not find film in list
        return 0;
    }
}
