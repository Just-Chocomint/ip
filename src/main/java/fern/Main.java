package fern;

import java.io.IOException;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

// Code from https://se-education.org/guides/tutorials/javaFxPart4.html

/**
 * A GUI for Fern using FXML.
 */
public class Main extends Application {

    private final Fern fern = new Fern();

    @Override
    public void start(Stage stage) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("/view/MainWindow.fxml"));
            AnchorPane ap = fxmlLoader.load();
            Scene scene = new Scene(ap);
            stage.setScene(scene);
            stage.setTitle("Fern");
            fxmlLoader.<MainWindow>getController().setFern(fern);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}