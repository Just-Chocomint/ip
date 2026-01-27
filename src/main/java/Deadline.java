import java.time.LocalDate;

public class Deadline extends Task {
    protected LocalDate by;
    public Deadline(String description, LocalDate by) {
        super(description);
        this.by = by;
    }

    public Deadline(String description, LocalDate by, boolean completed) {
        super(description, completed);
        this.by = by;
    }

    @Override
    public LocalDate getFirstDate() {
        return this.by;
    }

    // Return's the type of this task "D" for Deadline
    @Override
    public String getType(){
        return "D";
    }

    @Override
    public String toString() {
        try {
            return "[Deadline]" + super.toString() + " (by: " + DateHandler.dateToString(by) + ")";
        } catch (FernException e) {
            return "[Deadline]" + super.toString() + " (by: " + by + ")";
        }
    }
}
