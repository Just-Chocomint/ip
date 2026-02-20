package fern;

import java.time.LocalDate;

/**
 * Represents a task with start and end dates.
 */
public class Event extends Task {
    private final LocalDate from;
    private final LocalDate to;
    private static final String TYPE = "E";

    /**
     * Constructs an Event task with description, start and end dates.
     *
     * @param description the task description
     * @param from the start date
     * @param to the end date
     */
    public Event(String description, LocalDate from, LocalDate to) {
        super(description);
        this.from = from;
        this.to = to;
    }

    /**
     * Constructs an Event task with description, start date, end date and completion status.
     *
     * @param description the task description
     * @param from the start date
     * @param to the end date
     * @param isCompleted the completion status of the task
     */
    public Event(String description, LocalDate from, LocalDate to, boolean isCompleted) {
        super(description, isCompleted);
        this.from = from;
        this.to = to;
    }

    @Override
    public LocalDate getFirstDate() {
        return this.from;
    }

    @Override
    public LocalDate getSecondDate() {
        return this.to;
    }

    /**
     * Returns "E" for Events.
     *
     * @return the task type "E"
     */
    @Override
    public String getType() {
        return TYPE;
    }

    @Override
    public String toString() {
        try {
            return "[Event]" + super.toString() + " (From: "
                    + DateHandler.dateToString(from) + " | To: "
                    + DateHandler.dateToString(to) +")";

        } catch (FernException e) {
            return "[Event]" + super.toString() + " (From: " + from + " | To: " + to +")";
        }

    }
}
