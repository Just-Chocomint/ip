public class ToDo extends Task {
    public ToDo(String description) {
        super(description);
    }

    public ToDo(String description, boolean completed) {
        super(description, completed);
    }

    // Return's the type of this task "T" for Todo
    @Override
    public String getType(){
        return "T";
    }

    @Override
    public String toString() {
        return "[To Do]" + super.toString();
    }
}
