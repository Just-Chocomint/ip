package fern;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.time.LocalDate;

/**
 * Class to handle storing and retrieving data from file
 */
public class Storage {
    private static final Path PATH = Paths.get("./data/fern.txt");
    private static final String SEPARATOR = "///";
    private static final String TODO = "T";
    private static final String DEADLINE = "D";
    private static final String EVENT = "E";
    private static final int TYPE_INDEX = 0;
    private static final int COMPLETED_INDEX = 1;
    private static final int DESCRIPTION_INDEX = 2;
    private static final int FIRST_DATE_INDEX = 3;
    private static final int SECOND_DATE_INDEX = 4;

    /**
     * Initialise Storage if it doesn't exist, load into taskList if exists
     * @param taskList task list to load tasks into
     **/
    public void loadStorage(TaskList taskList) throws IOException, FernException {
        assert taskList != null : "TaskList to be loaded cannot be null";
        if (Files.notExists((PATH))) {
            Files.createDirectories(PATH.getParent());
            Files.createFile(PATH);
        }

        // Parse lines into tasks to add into taskList
        for (String task : Files.readAllLines(PATH)) {
            String[] splitLine = task.split(SEPARATOR);
            if (splitLine.length < 3) {
                throw new FernException("Corrupted storage file");
            }
            boolean completed = splitLine[COMPLETED_INDEX].equals("1");
            switch (splitLine[TYPE_INDEX]){
                case TODO:
                    taskList.addWithoutStorage(new ToDo(splitLine[DESCRIPTION_INDEX], completed));
                    break;
                case DEADLINE:
                    LocalDate by = DateHandler.stringToDate(splitLine[FIRST_DATE_INDEX]);
                    taskList.addWithoutStorage(new Deadline(splitLine[DESCRIPTION_INDEX], by, completed));
                    break;
                case EVENT:
                    LocalDate from = DateHandler.stringToDate(splitLine[FIRST_DATE_INDEX]);
                    LocalDate to = DateHandler.stringToDate(splitLine[SECOND_DATE_INDEX]);
                    taskList.addWithoutStorage(new Event(splitLine[DESCRIPTION_INDEX], from, to, completed));
                    break;
                default:
                    throw new FernException("Unknown task type");
            }
        }
    }

    /**
    * Add task objects into storage file
    **/
    public void add(Task task) throws IOException, FernException {
        String taskString = task.getType() + SEPARATOR
                + (task.getCompletion() ? "1" : "0") + SEPARATOR
                + task.getDescription();
        if (task.getType().equals(DEADLINE)) {
            taskString += SEPARATOR + DateHandler.dateToString(task.getFirstDate());
        } else if (task.getType().equals(EVENT)) {
            taskString += SEPARATOR + DateHandler.dateToString(task.getFirstDate());
            taskString += SEPARATOR + DateHandler.dateToString(task.getSecondDate());
        }
        taskString += "\n";
        Files.writeString(PATH, taskString, StandardOpenOption.CREATE, StandardOpenOption.APPEND);
    }

    /**
    * Overwrite storage file with input task list
    **/
    public void updateAll(TaskList taskList) throws IOException, FernException {
        Files.writeString(PATH, "");
        for (Task task: taskList.getAll()) {
            add(task);
        }
    }
}
