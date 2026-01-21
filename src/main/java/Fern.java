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
            String userInput = scanner.nextLine().trim();
            String[] userInputSplit = userInput.split(" ");
            String firstWord = userInputSplit[0].toLowerCase();

            if (userInput.equalsIgnoreCase("bye")){
                break;
            } else if (userInput.equalsIgnoreCase("list")) {
                printList();
                continue;
            }

            String taskDescription = "";
            switch (firstWord) {
                case "mark":
                case "unmark":
                    if (userInputSplit.length < 2) {
                        System.out.println("Fern: enter the task number u want to mark bro");
                        continue;
                    }
                    try { // try block to parse task number from string to int
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
                    break;
                case "todo":
                    taskDescription = userInput.substring(5);
                    tasks[counter] = new ToDo(taskDescription);
                    System.out.println("Fern: Added ToDo (" + taskDescription + ")");
                    if (counter < 99) {
                        counter++;
                    }
                    break;
                case "deadline":
                    int startOfDate = userInput.indexOf("/by");
                    if (startOfDate > 0) {
                        taskDescription = userInput.substring(8, startOfDate).trim();
                        String by = userInput.substring(startOfDate + 4);
                        tasks[counter] = new Deadline(taskDescription, by);
                        System.out.println("Fern: Added Deadline (" + taskDescription + ")");
                        if (counter < 99) {
                            counter++;
                        }
                    } else {
                        System.out.println("Fern: by what date? use /by thx");
                    }
                    break;
                case "event":
                    int startOfFrom = userInput.indexOf("/from");
                    int startOfTo = userInput.indexOf("/to");
                    if (startOfFrom > 0 && startOfTo > 0) {
                        taskDescription = userInput.substring(5, startOfFrom).trim();
                        String from = userInput.substring(startOfFrom + 6, startOfTo);
                        String to = userInput.substring(startOfTo + 4);
                        tasks[counter] = new Event(taskDescription, from, to);
                        System.out.println("Fern: Added Event (" + taskDescription + ")");
                        if (counter < 99) {
                            counter++;
                        }
                    } else {
                        System.out.println("Fern: dates? use /from and /to k thx");
                    }
                    break;
                default:
                    System.out.println("Fern: ? idk that command");
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
            int idx = i + 1;
            System.out.println((idx < 10 ? ("0" + idx) : idx) + ". "
                    + tasks[i].toString());
        }
    }
}