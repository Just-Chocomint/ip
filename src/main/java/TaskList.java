import java.io.IOException;
import java.util.ArrayList;

public class TaskList {
    private static final ArrayList<Task> tasks = new ArrayList<>();
    private Storage storage = new Storage();

    public int size() {
        return tasks.size();
    }

    public void addWithoutStorage(Task t) {
        tasks.add(t);
    }

    public void add(Task t) throws FernException {
        try {
            storage.add(t);
        } catch (IOException e) {
            throw new FernException("Fail to update storage");
        }
        tasks.add(t);
    }

    public void remove(int idx) throws FernException {
        tasks.remove(idx);
        try {
            storage.updateAll(this);
        } catch (IOException e) {
            throw new FernException("Fail to update storage");
        }
    }

    public Task get(int idx) {
        return tasks.get(idx);
    }

    public ArrayList<Task> getAll() {
        return tasks;
    }
}
