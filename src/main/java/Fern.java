import java.util.Scanner;

public class Fern {
    private static final TaskList tasks = new TaskList();
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
            String[] userInputSplit = userInput.split(" ");
            String firstWord = userInputSplit[0].toLowerCase();

            if (userInput.equalsIgnoreCase("bye")){
                break;
            } else if (userInput.equalsIgnoreCase("list")) {
                UI.printList(tasks);
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
                        UI.say("(" + tasks.get(markIdx).getDescription() + ") " + completion);

                        break;
                    case "todo":
                        taskDescription = userInput.substring(4).trim();
                        if (taskDescription.isEmpty()) {
                            throw new IncompleteCommandException("Description");
                        }
                        tasks.add(new ToDo(taskDescription));
                        UI.say("Added ToDo (" + taskDescription + ")");
                        break;
                    case "deadline":
                        int startOfDate = userInput.indexOf("/by");
                        if (startOfDate > 0) {
                            taskDescription = userInput.substring(8, startOfDate).trim();
                            String by = userInput.substring(startOfDate + 4);
                            tasks.add(new Deadline(taskDescription, by));
                            UI.say("Added Deadline (" + taskDescription + ")");
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
                            UI.say("Added Event (" + taskDescription + ")");
                        } else {
                            throw new IncompleteCommandException("/from and /to");
                        }
                        break;
                    case "delete":
                        if (userInputSplit.length < 2) {
                            throw new IncompleteCommandException("Task nunmber");
                        }
                        int deleteIdx = checkTaskExist(userInputSplit);
                        String deletedText = tasks.get(deleteIdx).toString();
                        tasks.remove(deleteIdx);
                        UI.say("(" + deletedText + ") deleted. Left " + tasks.size() + " Tasks");
                        break;
                    default:
                        throw new UnkownCommandException();
                }
            } catch (FernException e){
                UI.say(e.getMessage());
            }

        }
        UI.end();
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



}