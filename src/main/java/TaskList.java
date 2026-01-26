import java.util.ArrayList;

public class TaskList {
    private static final ArrayList<Task> tasks = new ArrayList<>();

    public int size() {
        return tasks.size();
    }

    public void add(Task t) {
        tasks.add(t);
    }

    public void remove(int idx) {
        tasks.remove(idx);
    }

    public Task get(int idx) {
        return tasks.get(idx);
    }

    public ArrayList<Task> getAll() {
        return tasks;
    }
    /**
     * Load file into TaskList.
     * Initialise file if not exists.
     **/
    public void loadDB(){

    }
}
