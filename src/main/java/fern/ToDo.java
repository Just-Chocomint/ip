package fern;

/**
 * Represents a task with no dates.
 */
public class ToDo extends Task {
    private static final String TYPE = "T";
    /**
     * Constructs a ToDo task with description.
     *
     * @param description the task description
     */
    public ToDo(String description) {
        super(description);
    }

    /**
     * Constructs a ToDo task with description and completion status.
     *
     * @param description the task description
     * @param completed the completion status of the task
     */
    public ToDo(String description, boolean completed) {
        super(description, completed);
    }

    @Override
    public String getType() {
        return TYPE;
    }

    @Override
    public String toString() {
        return "[To Do]" + super.toString();
    }
}
