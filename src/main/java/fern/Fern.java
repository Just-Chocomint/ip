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
    
    /**
     * Starts the chatbot
     */
    public static void main(String[] args) {
        try {
            storage.loadStorage(tasks);
        } catch (IOException | FernException e) {
            UI.say("Failed to load file, reload app pls");
            System.exit(1);
        }

        UI.start();
        Scanner scanner = new Scanner(System.in);
        while(true) {
            System.out.print("You: \n");
            String userInput = scanner.nextLine().trim();
            if (userInput.equalsIgnoreCase("bye")) {
                break;
            } else if (userInput.equalsIgnoreCase("list")) {
                UI.printList(tasks);
                continue;
            }
            try {
                commands.handle(userInput);
            } catch (FernException e){
                UI.say(e.getMessage());
            }

        }
        UI.end();
        scanner.close();
    }
}