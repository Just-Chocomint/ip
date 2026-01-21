import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Scanner;

public class Fern {
    private static final ArrayList<Task> tasks = new ArrayList<>(100);

    /**
     * Starts the chatbot
     */
    public static void main(String[] args) throws FernException{
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
        say("Hii, i am\n" + name + "Whats up?");

        Scanner scanner = new Scanner(System.in);
        while(true) {
            System.out.print("You: \n");
            String userInput = scanner.nextLine().trim();
            String[] userInputSplit = userInput.split(" ");
            String firstWord = userInputSplit[0].toLowerCase();

            if (userInput.equalsIgnoreCase("bye")){
                break;
            } else if (userInput.equalsIgnoreCase("list")) {
                printList();
                continue;
            }
            try {
                String taskDescription = "";
                switch (firstWord) {
                    case "mark":
                    case "unmark":
                        if (userInputSplit.length < 2) {
                            throw new IncompleteCommandException("Task nunmber");
                        }
                        int markIdx = checkTaskExist(userInputSplit);
                        tasks.get(markIdx).toggleCompletion();
                        String completion = tasks.get(markIdx).isCompleted ? "marked" : "unmarked";
                        say("(" + tasks.get(markIdx).getDescription() + ") " + completion);

                        break;
                    case "todo":
                        taskDescription = userInput.substring(4).trim();
                        if (taskDescription.isEmpty()) {
                            throw new IncompleteCommandException("Description");
                        }
                        tasks.add(new ToDo(taskDescription));
                        say("Added ToDo (" + taskDescription + ")");
                        break;
                    case "deadline":
                        int startOfDate = userInput.indexOf("/by");
                        if (startOfDate > 0) {
                            taskDescription = userInput.substring(8, startOfDate).trim();
                            String by = userInput.substring(startOfDate + 4);
                            tasks.add(new Deadline(taskDescription, by));
                            say("Added Deadline (" + taskDescription + ")");
                        } else {
                            throw new IncompleteCommandException("/by");
                        }
                        break;
                    case "event":
                        int startOfFrom = userInput.indexOf("/from");
                        int startOfTo = userInput.indexOf("/to");
                        if (startOfFrom > 0 && startOfTo > 0) {
                            taskDescription = userInput.substring(5, startOfFrom).trim();
                            String from = userInput.substring(startOfFrom + 6, startOfTo);
                            String to = userInput.substring(startOfTo + 4);
                            tasks.add(new Event(taskDescription, from, to));
                            say("Added Event (" + taskDescription + ")");
                        } else {
                            throw new IncompleteCommandException("/from and /to");
                        }
                        break;
                    case "delete":
                        if (userInputSplit.length < 2) {
                            throw new IncompleteCommandException("Task nunmber");
                        }
                        int deleteIdx = checkTaskExist(userInputSplit);
                        delete(deleteIdx);
                        break;
                    default:
                        throw new UnkownCommandException();
                }
            } catch (FernException e){
                say(e.getMessage());
            }

        }
        say("Byebye~~");
        System.out.println(line);
        scanner.close();
    }

    /**
     * Check that task exists
     * Return index if there is no problem
     **/
    private static int checkTaskExist(String[] userInputSplit) throws FernException{
        try { // try block to parse task number from string to int
            int idx = Integer.parseInt(userInputSplit[1]) - 1;
            if (idx >= tasks.size() || idx < 0) {
                throw new FernException("Fern: that task no exist");
            } else if (userInputSplit[0].equals("mark") && tasks.get(idx).getCompletion()) {
                throw new FernException("Fern: u alr completed");
            } else if (userInputSplit[0].equals("unmark") && !tasks.get(idx).getCompletion()) {
                throw new FernException("Fern: it was alr unmarked bruh");
            }
            return idx;
        } catch (NumberFormatException e) {
            throw new FernException("Fern: what task is that");
        }
    }

    /**
     * Delete task from list
     **/
    private static void delete(int idx) {
        say("(" + tasks.get(idx).toString() + ") deleted. Left " + (tasks.size() - 1) + " Tasks");
        tasks.remove(idx);
    }

    /**
     * Print the list of tasks
     **/
    private static void printList() {
        say("");
        for(int i = 0; i < tasks.size(); i++){
            int idx = i + 1;
            System.out.println((idx < 10 ? ("0" + idx) : idx) + ". "
                    + tasks.get(i).toString());
        }
    }
    /**
     * Make fern speak
     **/
    private static void say(String msg) {
        System.out.println("Fern: " + msg);
    }
}