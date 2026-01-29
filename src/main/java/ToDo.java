/**
 * Class for tasks with no dates
 */
public class ToDo extends Task {
    /**
     * Constructor that takes in description
     **/
    public ToDo(String description) {
        super(description);
    }

    /**
     * Constructor that takes in description and completion status
     **/
    public ToDo(String description, boolean completed) {
        super(description, completed);
    }

    @Override
    public String getType() {
        return "T";
    }

    @Override
    public String toString() {
        return "[To Do]" + super.toString();
    }
}
