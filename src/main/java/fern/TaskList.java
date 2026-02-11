package fern;

import java.io.IOException;
import java.util.ArrayList;

/**
 * List of tasks that is synced to storage
 */
public class TaskList {
    private static final ArrayList<Task> tasks = new ArrayList<>();
    private final Storage storage = new Storage();

    /**
     * Returns size of task list
     **/
    public int size() {
        return tasks.size();
    }

    /**
     * Add items into task list without updating storage
     * @param t task to be added
     **/
    public void addWithoutStorage(Task t) {
        tasks.add(t);
    }

    /**
     * Add items into task list and storage
     * @param t task to be added
     **/
    public void add(Task t) throws FernException {
        try {
            storage.add(t);
        } catch (IOException e) {
            throw new FernException("Fail to update storage");
        }
        tasks.add(t);
    }

    /**
     * Remove item at index idx
     * @param idx index of task to be removed
     * @throws FernException when storage update fails
     **/
    public void remove(int idx) throws FernException {
        assert idx >= 0 && idx < tasks.size() : "Index out of range";
        tasks.remove(idx);
        try {
            storage.updateAll(this);
        } catch (IOException e) {
            throw new FernException("Fail to update storage");
        }
    }

    /**
     * Search Task that contains keyword
     **/
    public ArrayList<Task> find(String search) {
        ArrayList<Task> results = new ArrayList<>();
        for (Task task : tasks) {
            if (task.getDescription().contains(search)) {
                results.add(task);
            }
        }
        return results;
    }

    /**
     * Retrieve item at index idx
     * @param idx index of task to be gotten
     **/
    public Task get(int idx) {
        assert idx >= 0 && idx < tasks.size() : "index not out of range";
        return tasks.get(idx);
    }

    /**
     * Returns all tasks
     **/
    public ArrayList<Task> getAll() {
        return tasks;
    }
}
