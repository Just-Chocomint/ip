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

    /**
     * Initialise Storage if it doesn't exist, load into taskList if exists
     * @param taskList task list to load tasks into
     **/
    public void loadStorage(TaskList taskList) throws IOException, FernException {
        if (Files.notExists((PATH))) {
            Files.createDirectories(PATH.getParent());
            Files.createFile(PATH);
        }

        // Parse lines into tasks to add into taskList
        for (String task : Files.readAllLines(PATH)) {
            String[] splitLine = task.split(SEPARATOR);
            boolean completed = splitLine[1].equals("1");
            switch (splitLine[0]){
                case TODO:
                    taskList.addWithoutStorage(new ToDo(splitLine[2], completed));
                    break;
                case DEADLINE:
                    LocalDate by = DateHandler.stringToDate(splitLine[3]);
                    taskList.addWithoutStorage(new Deadline(splitLine[2], by, completed));
                    break;
                case EVENT:
                    LocalDate from = DateHandler.stringToDate(splitLine[3]);
                    LocalDate to = DateHandler.stringToDate(splitLine[4]);
                    taskList.addWithoutStorage(new Event(splitLine[2], from, to, completed));
                    break;
                default:
                    throw new FernException("Unknown task type");
            }
        }
    }

    /**
    * Add tasks to storage
    **/
    public void add(Task task) throws IOException, FernException {
        String taskString = task.getType() + SEPARATOR
                + (task.getIsCompleted() ? "1" : "0") + SEPARATOR
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
    * Overwrite storage file with task list
    **/
    public void updateAll(TaskList taskList) throws IOException, FernException {
        Files.writeString(PATH, "");
        for (Task task: taskList.getAll()) {
            add(task);
        }
    }
}
