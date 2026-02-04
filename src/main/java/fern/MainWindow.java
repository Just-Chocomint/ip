package fern;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;

// Code from https://se-education.org/guides/tutorials/javaFxPart4.html

/**
 * Controller for the main GUI.
 */
public class MainWindow extends AnchorPane {
    @FXML
    private ScrollPane scrollPane;
    @FXML
    private VBox dialogContainer;
    @FXML
    private TextField userInput;
    @FXML
    private Button sendButton;

    private Fern fern;

    private final Image userImage = new Image(this.getClass().getResourceAsStream("/images/DaUser.png"));
    private final Image fernImage = new Image(this.getClass().getResourceAsStream("/images/DaFern.png"));

    @FXML
    public void initialize() {
        String fernIntroduction = "Hello!! These are my commands:\n" +
                "- list\n" +
                "- mark <task_number> (from list command)\n" +
                "- delete <task_number> (from list command)\n" +
                "- todo <task_description>\n" +
                "- event <task_description> /from <start_date> /to <end_date>\n" +
                "- deadline <task_description> /by <deadline>\n" +
                "- find <keyword>";
        scrollPane.vvalueProperty().bind(dialogContainer.heightProperty());
        dialogContainer.getChildren().addAll(
                DialogBox.getFernDialog(fernIntroduction, fernImage)
        );
    }

    /** Injects the Fern instance */
    public void setFern(Fern f) {
        fern = f;
    }

    /**
     * Creates two Dialog boxes, one echoing user input and the other containing Ferb's reply and then appends them to
     * the Dialog container. Clears the user input after processing.
     */
    @FXML
    private void handleUserInput() {
        String input = userInput.getText();
        String response = fern.getResponse(input);
        dialogContainer.getChildren().addAll(
                DialogBox.getUserDialog(input, userImage),
                DialogBox.getFernDialog(response, fernImage)
        );
        userInput.clear();
    }
}

