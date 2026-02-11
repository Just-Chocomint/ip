package fern;

import java.io.IOException;
import java.util.Scanner;

/**
 * Main class to start the chatbot
 */
public class Fern {
    private static final TaskList tasks = new TaskList();
    private static final Commands commands = new Commands(tasks);
    private static final Storage storage = new Storage();

    static {
        try {
            storage.loadStorage(tasks);
        } catch (IOException | FernException e) {
            System.exit(1);
        }
    }

    /**
     * Gets Bot's response to userInput
     */
    public String getResponse(String userInput) {
        if (userInput.equalsIgnoreCase("bye")) {
            return UI.end();
        } else if (userInput.equalsIgnoreCase("list")) {
            return UI.printList(tasks);
        } else {
            try {
                return commands.handle(userInput);
            } catch (FernException e){
                return (e.getMessage());
            }
        }
    }
}