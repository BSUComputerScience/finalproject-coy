package edu.bsu.cs222;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class MainGUI extends Application{

    Label prompt;
    TextField textField;
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
        checkButton = new Button("Click this button!");
        grid.add(checkButton, 1, 1);
        grid.add(filmTitle, 0, 2, 2, 1);
        // code to handle button action
        checkButton.setOnAction(e -> handleButtonClick());
        // add to the stage
        Scene scene = new Scene(grid, 500, 1500); // width, height
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    private void handleButtonClick() {
        StringBuilder output = new StringBuilder("Clicking the button worked!");

//        // gather information & output
//        WikipediaRevisionReader wikipediaRevisionReader = new WikipediaRevisionReader();
//        ArrayList<Revision> revisions;
//        RevisionFormatter revisionFormatter = new RevisionFormatter();
//        StringBuilder output = new StringBuilder();
//        // connect and read, then display
//        revisions = wikipediaRevisionReader.connectAndRead(userEntry);
//        // TODO: need to refactor to extract message of redirect
//        // TODO: also need to handle the "no article of given name", such as aasdflkj
//        // TODO: network errors need a modal box
//        for (Revision revision : revisions) {
//            output.append(revisionFormatter.format(revision));
//        }
        filmTitle.setText(output.toString());
    }
}
