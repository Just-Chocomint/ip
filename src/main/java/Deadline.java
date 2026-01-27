public class Deadline extends Task {
    protected String by;
    public Deadline(String description, String by) {
        super(description);
        this.by = by;
    }

    public Deadline(String description, String by, boolean completed) {
        super(description, completed);
        this.by = by;
    }

    @Override
    public String getFirstDate() {
        return this.by;
    }

    // Return's the type of this task "D" for Deadline
    @Override
    public String getType(){
        return "D";
    }

    @Override
    public String toString() {
        return "[Deadline]" + super.toString() + " (by: " + by + ")";
    }
}
