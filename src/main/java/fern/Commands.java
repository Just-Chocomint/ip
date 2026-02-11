package fern;

import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;

/**
 * Executes the commands that user inputs
 */
public class Commands {
    private final TaskList tasks;
    private final Storage storage = new Storage();
    private String[] userInputSplit;
    private String userInput;

    /**
    * Constructor
    * @param taskList the list to operate the commands on
    **/
    public Commands(TaskList taskList) {
        assert taskList != null: "Task list is null";
        this.tasks = taskList;
    }

    /**
    * Handle user input into commands
    * @param userInput full user input
    **/
    public String handle(String userInput) throws FernException {
        assert userInput != null : "input string cant be null";

        this.userInput = userInput;
        this.userInputSplit = userInput.split(" ");
        String firstWord = userInputSplit[0].toLowerCase();
        switch (firstWord) {
        case "mark":
            // Fall through
        case "unmark":
            return handleMark();
        case "todo":
            return handleToDo();
        case "deadline":
            return handleDeadline();
        case "event":
            return handleEvent();
        case "delete":
            return handleDelete();
        case "find":
            return handleFind();
        default:
            throw new UnknownCommandException();
        }
    }

    /**
     * Toggles completion state of a task specified by the user
     * @throws FernException if updating storage fails
     **/
    private String handleMark() throws FernException {
        if (userInputSplit.length < 2) {
            throw new IncompleteCommandException("Task nunmber");
        }
        int markIdx = checkTaskExist();
        tasks.get(markIdx).toggleCompletion();
        try {
            storage.updateAll(tasks);
        } catch (IOException e) {
            throw new FernException("Fail to update storage");
        }
        String completion = tasks.get(markIdx).getCompletion() ? "marked" : "unmarked";
        return ("(" + tasks.get(markIdx).getDescription() + ") " + completion);
    }

    /**
     * Handle checking and adding todo tasks
     * @throws FernException if inputted task is empty
     **/
    private String handleToDo() throws FernException {
        String taskDescription = userInput.substring("todo".length()).trim();
        if (taskDescription.isEmpty()) {
            throw new IncompleteCommandException("Description");
        }
        ToDo newTask = new ToDo(taskDescription);
        tasks.add(newTask);
        return ("Added ToDo (" + taskDescription + ")");
    }

    /**
     * Handle checking and adding Deadline tasks
     * @throws FernException if missing deadline in user input
     **/
    private String handleDeadline() throws FernException {
        int startOfDate = userInput.indexOf("/by");
        if (startOfDate > 0) {
            String taskDescription = userInput.substring("deadline".length(), startOfDate).trim();
            String byString = userInput.substring(startOfDate + 4);
            LocalDate by = DateHandler.stringToDate(byString.trim());
            tasks.add(new Deadline(taskDescription, by));
            return ("Added Deadline (" + taskDescription + ")");
        } else {
            throw new IncompleteCommandException("/by");
        }
    }

    /**
     * Handle checking and adding Event tasks
     * @throws FernException if /from or /to is missing
     **/
    private String handleEvent() throws FernException {
        int startOfFrom = userInput.indexOf("/from");
        int startOfTo = userInput.indexOf("/to");
        if (startOfFrom > 0 && startOfTo > 0) {
            String taskDescription = userInput.substring("event".length(), startOfFrom).trim();
            String fromString = userInput.substring(startOfFrom + 6, startOfTo);
            String toString = userInput.substring(startOfTo + 4);
            LocalDate from = DateHandler.stringToDate(fromString.trim());
            LocalDate to = DateHandler.stringToDate(toString.trim());
            tasks.add(new Event(taskDescription, from, to));
            return ("Added Event (" + taskDescription + ")");
        } else {
            throw new IncompleteCommandException("/from and /to");
        }
    }

    /**
     * Handle deleting tasks
     * @throws FernException if task to be deleted not specified
     **/
    private String handleDelete() throws FernException {
        if (userInputSplit.length < 2) {
            throw new IncompleteCommandException("Task nunmber");
        }
        int deleteIdx = checkTaskExist();
        String deletedText = tasks.get(deleteIdx).toString();
        tasks.remove(deleteIdx);
        return ("(" + deletedText + ") deleted. Left " + tasks.size() + " Tasks");
    }

    /**
     * Handle searching for keyword
     * @throws FernException if no keyword to search
     **/
    private String handleFind() throws FernException {
        if (userInputSplit.length < 2) {
            throw new IncompleteCommandException("Keyword missing");
        }
        String keyword = userInput.substring(5).trim();
        ArrayList<Task> found = tasks.find(keyword);
        return UI.printList(found);
    }

    /**
     * Check that task exists
     * @throws FernException if task specified by user does not exist
     * Return index if there is no problem
     **/
    private int checkTaskExist() throws FernException {
        try { // try block to parse task number from string to int
            int idx = Integer.parseInt(userInputSplit[1]) - 1;
            if (idx >= tasks.size() || idx < 0) {
                throw new FernException("that task no exist");
            } else if (userInputSplit[0].equals("mark") && tasks.get(idx).getCompletion()) {
                throw new FernException("u alr completed");
            } else if (userInputSplit[0].equals("unmark") && !tasks.get(idx).getCompletion()) {
                throw new FernException("it was alr unmarked bruh");
            }
            return idx;
        } catch (NumberFormatException e) {
            throw new FernException("Fern: what task is that");
        }
    }
}
