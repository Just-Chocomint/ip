import java.util.Scanner;

public class Fern {
    private static final TaskList tasks = new TaskList();
    private static final Commands commands = new Commands(tasks);
    /**
     * Starts the chatbot
     */
    public static void main(String[] args) throws FernException{
        tasks.loadDB();
        UI.start();
        Scanner scanner = new Scanner(System.in);
        while(true) {
            System.out.print("You: \n");
            String userInput = scanner.nextLine().trim();
            if (userInput.equalsIgnoreCase("bye")){
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