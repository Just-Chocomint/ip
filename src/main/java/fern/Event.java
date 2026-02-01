package fern;

import java.time.LocalDate;

/**
 * Class for tasks with start and end date
 */
public class Event extends Task {
    protected LocalDate from;
    protected LocalDate to;

    /**
     * Constructor that takes in description, from and to
     **/
    public Event(String description, LocalDate from, LocalDate to) {
        super(description);
        this.from = from;
        this.to = to;
    }

    /**
     * Constructor that takes in description, from, to and completion status of task
     **/
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
     * Return "E" for Events
     **/
    @Override
    public String getType() {
        return "E";
    }

    @Override
    public String toString() {
        try {
            return "[Event]" + super.toString() + " (From: "
                    + DateHandler.dateToString(from) + "| To: "
                    + DateHandler.dateToString(to) +")";

        } catch (FernException e) {
            return "[Event]" + super.toString() + " (From: " + from + "| To: " + to +")";
        }

    }
}
