import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;

public class Fern {
    private static final Task[] tasks = new Task[100];
    // counter is the amount of tasks added
    private static int counter = 0;

    /**
     * Starts the chatbot
     */
    public static void main(String[] args) {
        String name = ",------.  \n"
                + "|  .---',---. ,--.--.,--,--,  \n"
                + "|  `--,| .-. :|  .--'|      \\ \n"
                + "|  |`  \\   --.|  |   |  ||  | \n"
                + "`--'    `----'`--'   `--''--' \n";

        DateTimeFormatter timeFormatter = DateTimeFormatter.ofPattern("dd/MM/yyyy hh.mm a");
        String line = "--------------------"
                + LocalDateTime.now().format(timeFormatter)
                + "---------------------";

        System.out.println(line);
        System.out.println("Hii, i am\n" + name + "Whats up?\n" );

        Scanner scanner = new Scanner(System.in);
        while(true) {
            System.out.print("You: ");
            String userInput = scanner.nextLine();
            String[] userInputSplit = userInput.split(" ");
            String firstWord = userInputSplit[0].toLowerCase();

            if (userInput.equals("")){
                System.out.println("Fern: ?");
            } else if (userInput.equalsIgnoreCase("bye")){
                break;
            } else if (userInput.equalsIgnoreCase("list")) {
                printList();
            } else if (firstWord.equals("mark") || firstWord.equals("unmark")){
                if (userInputSplit.length < 2) {
                    System.out.println("Fern: enter the task number u want to mark bro");
                    continue;
                }
                try {
                    // curTask is the task user chose to mark/unmark
                    int idx = Integer.parseInt(userInputSplit[1]) - 1;
                    if (idx >= counter || idx < 0) {
                        System.out.println("Fern: that task no exist");
                    } else if (firstWord.equals("mark") && tasks[idx].getCompletion()) {
                        System.out.println("Fern: u alr completed");
                    } else if (firstWord.equals("unmark") && !tasks[idx].getCompletion()) {
                        System.out.println("Fern: it was alr unmarked bruh");
                    } else {
                        tasks[idx].toggleCompletion();
                        String completion = tasks[idx].isCompleted ? "marked" : "unmarked";
                        System.out.println("(" + tasks[idx].getDescription() + ") " + completion);
                    }
                } catch (NumberFormatException e) {
                    System.out.println("Fern: what task is that");
                }

            } else {
                tasks[counter] = new Task(userInput);
                System.out.println("Fern: Added item (" + userInput + ")");
                if (++counter == 100) {
                    counter = 0;
                }
            }
        }
        System.out.println("Fern: Byebye~~");
        System.out.println(line);
        scanner.close();
    }

    /**
     * Prints the list of tasks
     **/
    private static void printList() {
        for(int i = 0; i < counter; i++){
            System.out.println((i + 1) + ". "
                    + tasks[i].getCompletionString()
                    + tasks[i].getDescription());
        }
    }
}
