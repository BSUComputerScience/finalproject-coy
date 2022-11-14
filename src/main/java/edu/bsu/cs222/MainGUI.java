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

    final boolean TESTING = true;   // set to True to use test file, not API
    Button checkButton = new Button("Display");
    Text sceneTitle = new Text("Star Wars Trivia");
    Text filmTitle = new Text();
    Text filmOpeningCrawl = new Text();
    Text releaseDate = new Text();
    Text episodeID = new Text();
    final String[] filmList = { "A New Hope", "The Empire Strikes Back", "Return of the Jedi",
            "The Phantom Menace", "Attack of the Clones", "Revenge of the Sith"};
    ComboBox<String> selectMovie = new ComboBox<>(FXCollections.observableArrayList(filmList));

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
        grid.setPadding(new Insets(50, 50, 50, 50));
        grid.setPrefSize(1000, 1000);
        // Set title and background
        InputStream backgroundImageFile = Thread.currentThread().getContextClassLoader().getResourceAsStream("stars.jpeg");
        if (backgroundImageFile != null) {
            BackgroundImage backgroundImage = new BackgroundImage(new Image(backgroundImageFile),
                    BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.DEFAULT,
                    BackgroundSize.DEFAULT);
            Background background = new Background(backgroundImage);
            grid.setBackground(background);
        }
        // Formatting text and add to grid, 2 columns wide, Order is (col, row, colspan, rowspan)
        formatTextFieldsForDisplay();
        grid.add(sceneTitle, 0, 0, 2, 1);
        grid.add(selectMovie, 0, 1);
        grid.add(checkButton, 1, 1);
        grid.add(filmTitle, 0, 2, 2, 1);
        grid.add(episodeID, 0, 3, 1, 1);
        grid.add(releaseDate, 1, 3, 1, 1);
        grid.add(filmOpeningCrawl, 0, 4, 2, 1);
        // configure button action
        checkButton.setOnAction(e -> handleButtonClick());
        // add grid to the scene and the scene to the stage
        Scene scene = new Scene(grid, 1000, 1000); // width, height
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    private void formatTextFieldsForDisplay() {
        sceneTitle.setFont(Font.font("Verdana", FontWeight.BOLD, FontPosture.REGULAR, 96));
        sceneTitle.setFill(Color.BLACK);
        sceneTitle.setStrokeWidth(2);
        sceneTitle.setStroke(Color.YELLOW);
        sceneTitle.setTextAlignment(TextAlignment.CENTER);
        filmTitle.setFont(Font.font("Verdana", FontWeight.BOLD, FontPosture.REGULAR, 48));
        filmTitle.setFill(Color.BLACK);
        filmTitle.setStrokeWidth(2);
        filmTitle.setStroke(Color.YELLOW);
        filmTitle.setTextAlignment(TextAlignment.CENTER);
        releaseDate.setFont(Font.font("Lucida Sans Unicode", FontWeight.NORMAL, FontPosture.ITALIC, 18));
        releaseDate.setFill(Color.YELLOW);
        releaseDate.setTextAlignment(TextAlignment.CENTER);
        episodeID.setFont(Font.font("Lucida Sans Unicode", FontWeight.NORMAL, FontPosture.ITALIC, 18));
        episodeID.setFill(Color.YELLOW);
        episodeID.setTextAlignment(TextAlignment.CENTER);
        filmOpeningCrawl.setFont(Font.font("Lucida Sans Unicode", FontWeight.NORMAL, FontPosture.ITALIC, 20));
        filmOpeningCrawl.setFill(Color.WHITE);
        filmOpeningCrawl.setTextAlignment(TextAlignment.CENTER);
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
            releaseDate.setText(film.getRelease_date());
            episodeID.setText("Episode " + film.getEpisode_id());
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
