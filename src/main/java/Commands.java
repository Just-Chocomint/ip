public class Commands {
    private final TaskList tasks;
    private String[] userInputSplit;
    private String userInput;

    public Commands(TaskList taskList){
        this.tasks = taskList;
    }

    public void handle(String userInput) throws FernException{
        this.userInput = userInput;
        this.userInputSplit = userInput.split(" ");
        String firstWord = userInputSplit[0].toLowerCase();
        switch (firstWord) {
        case "mark":
        case "unmark":
            handleMark();
            break;
        case "todo":
            handleToDo();
            break;
        case "deadline":
            handleDeadline();
            break;
        case "event":
            handleEvent();
            break;
        case "delete":
            handleDelete();
            break;
        default:
            throw new UnkownCommandException();
        }
    }

    /**
     * Handle marking/unmarking tasks
     **/
    private void handleMark() throws FernException{
        if (userInputSplit.length < 2) {
            throw new IncompleteCommandException("Task nunmber");
        }
        int markIdx = checkTaskExist();
        tasks.get(markIdx).toggleCompletion();
        String completion = tasks.get(markIdx).isCompleted ? "marked" : "unmarked";
        UI.say("(" + tasks.get(markIdx).getDescription() + ") " + completion);
    }

    /**
     * Handle checking and adding todo tasks
     **/
    private void handleToDo() throws FernException{
        String taskDescription = userInput.substring(4).trim();
        if (taskDescription.isEmpty()) {
            throw new IncompleteCommandException("Description");
        }
        tasks.add(new ToDo(taskDescription));
        UI.say("Added ToDo (" + taskDescription + ")");
    }

    /**
     * Handle checking and adding Deadline tasks
     **/
    private void handleDeadline() throws FernException{
        int startOfDate = userInput.indexOf("/by");
        if (startOfDate > 0) {
            String taskDescription = userInput.substring(8, startOfDate).trim();
            String by = userInput.substring(startOfDate + 4);
            tasks.add(new Deadline(taskDescription, by));
            UI.say("Added Deadline (" + taskDescription + ")");
        } else {
            throw new IncompleteCommandException("/by");
        }
    }

    /**
     * Handle checking and adding Event tasks
     **/
    private void handleEvent() throws FernException{
        int startOfFrom = userInput.indexOf("/from");
        int startOfTo = userInput.indexOf("/to");
        if (startOfFrom > 0 && startOfTo > 0) {
            String taskDescription = userInput.substring(5, startOfFrom).trim();
            String from = userInput.substring(startOfFrom + 6, startOfTo);
            String to = userInput.substring(startOfTo + 4);
            tasks.add(new Event(taskDescription, from, to));
            UI.say("Added Event (" + taskDescription + ")");
        } else {
            throw new IncompleteCommandException("/from and /to");
        }
    }

    /**
     * Handle deleting tasks
     **/
    private void handleDelete() throws FernException{
        if (userInputSplit.length < 2) {
            throw new IncompleteCommandException("Task nunmber");
        }
        int deleteIdx = checkTaskExist();
        String deletedText = tasks.get(deleteIdx).toString();
        tasks.remove(deleteIdx);
        UI.say("(" + deletedText + ") deleted. Left " + tasks.size() + " Tasks");
    }

    /**
     * Check that task exists
     * Return index if there is no problem
     **/
    private int checkTaskExist() throws FernException{
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
}
