package fern;

import java.time.LocalDate;

/**
 * Represents a task with a deadline.
 */
public class Deadline extends Task {
    private final LocalDate by;
    private static final String TYPE = "D";

    /**
     * Constructs a Deadline task with description and deadline.
     *
     * @param description the task description
     * @param by the deadline date
     */
    public Deadline(String description, LocalDate by) {
        super(description);
        this.by = by;
    }

    /**
     * Constructs a Deadline task with description, deadline and completion status.
     *
     * @param description the task description
     * @param by the deadline date
     * @param isCompleted the completion status of the task
     */
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
