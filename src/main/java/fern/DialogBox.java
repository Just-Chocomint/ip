package fern;

import java.io.IOException;
import java.util.Collections;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.shape.Circle;

// Code from https://se-education.org/guides/tutorials/javaFxPart4.html

/**
 * Represents a dialog box consisting of an ImageView to represent the speaker's face
 * and a label containing text from the speaker.
 */
public class DialogBox extends HBox {
    @FXML
    private Label dialog;
    @FXML
    private ImageView displayPicture;

    private DialogBox(String text, Image img) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(MainWindow.class.getResource("/view/DialogBox.fxml"));
            fxmlLoader.setController(this);
            fxmlLoader.setRoot(this);
            fxmlLoader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }

        dialog.setText(text);
        displayPicture.setImage(img);
        Circle circleClip = new Circle(25, 25, 25);
        displayPicture.setClip(circleClip);
    }

    /**
     * Flips the dialog box such that the ImageView is on the left and text on the right.
     */
    private void flip() {
        ObservableList<Node> tmp = FXCollections.observableArrayList(this.getChildren());
        Collections.reverse(tmp);
        getChildren().setAll(tmp);
        setAlignment(Pos.TOP_LEFT);
    }

    public static DialogBox getUserDialog(String text, Image img) {
        var db = new DialogBox(text, img);
        db.dialog.setStyle(
                "-fx-background-color: #c3ffb5;" +
                        "-fx-text-fill: black;" +
                        "-fx-padding: 10;" +
                        "-fx-background-radius: 10;"
        );
        HBox.setMargin(db.dialog, new Insets(5, 10, 0, 10));
        return db;
    }

    public static DialogBox getFernDialog(String text, Image img) {
        var db = new DialogBox(text, img);
        db.dialog.getStyleClass().add("fern-bubble");
        db.dialog.setStyle(
                "-fx-background-color: white;" +
                        "-fx-text-fill: black;" +
                        "-fx-padding: 10;" +
                        "-fx-background-radius: 10;"
        );
        HBox.setMargin(db.dialog, new Insets(5, 10, 0, 10));
        db.flip();
        return db;
    }
}
