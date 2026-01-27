import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;

public class Storage {
    private final Path PATH = Paths.get("./data/fern.txt");
    /**
     * Initialise Storage if it doesn't exist, load into taskList if exists
     * @param taskList task list to load tasks into
     **/
    public void loadStorage(TaskList taskList) throws IOException {
        System.out.println(Paths.get("").toAbsolutePath());

        if (Files.notExists((PATH))) {
            Files.createDirectories(PATH.getParent());
            Files.createFile(PATH);
        }

        // Parse lines into tasks to add into taskList
        for (String task : Files.readAllLines(PATH)) {
            String[] splitLine = task.split("///");
            boolean completed = splitLine[1].equals("1");
            switch (splitLine[0]){
                case "T":
                    taskList.addWithoutStorage(new ToDo(splitLine[2], completed));
                    break;
                case "D":
                    taskList.addWithoutStorage(new Deadline(splitLine[2], splitLine[3], completed));
                    break;
                case "E":
                    taskList.addWithoutStorage(new Event(splitLine[2], splitLine[3], splitLine[4], completed));
                    break;
                default:
            }
        }
    }

    // Add task into file
    public void add(Task task) throws IOException {
        String taskString = task.getType() + "///"
                + (task.getIsCompleted() ? "1" : "0") + "///"
                + task.getDescription();
        if (task.getType().equals("D")) {
            taskString += "///" + task.getFirstDate();
        } else if (task.getType().equals("E")) {
            taskString += "///" + task.getFirstDate();
            taskString += "///" + task.getSecondDate();
        }
        taskString += "\n";
        Files.writeString(PATH, taskString, StandardOpenOption.CREATE, StandardOpenOption.APPEND);
    }

    /**
     * Overwrite storage file with task list
     **/
    public void updateAll(TaskList taskList) throws IOException {
        Files.writeString(PATH, "");
        for (Task task: taskList.getAll()) {
            add(task);
        }
    }
}
