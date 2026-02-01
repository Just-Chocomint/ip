package fern;

import java.time.LocalDate;

/**
 * Class for tasks with deadline
 */
public class Deadline extends Task {
    private final LocalDate by;
    private static final String TYPE = "D";

    /**
     * Constructor that takes in description and deadline
     **/
    public Deadline(String description, LocalDate by) {
        super(description);
        this.by = by;
    }

    /**
     * Constructor that takes in description, deadline and the completion status if the task
     **/
    public Deadline(String description, LocalDate by, boolean isCompleted) {
        super(description, isCompleted);
        this.by = by;
    }

    @Override
    public LocalDate getFirstDate() {
        return this.by;
    }

    @Override
    public String getType() {
        return TYPE;
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
