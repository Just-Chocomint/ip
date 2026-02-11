package fern;

import java.time.LocalDate;

/**
 * Class for default task
 */
public class Task {
    protected String description;
    protected boolean isCompleted;

    /**
     * Constructor that takes in description
     **/
    public Task(String description) {
        this.description = description;
        this.isCompleted = false;
    }

    /**
     * Constructor that takes in description and completion status
     **/
    public Task(String description, boolean isCompleted) {
        this.description = description;
        this.isCompleted = isCompleted;
    }

    /**
     * Returns the type of current task
     **/
    public String getType(){
        return "No type";
    }

    /**
     * Returns the first date e.g. deadline and event start
     **/
    public LocalDate getFirstDate() {
        return LocalDate.now();
    }

    /**
     * Returns the second date e.g. event end
     **/
    public LocalDate getSecondDate() {
        return LocalDate.now();
    }

    /**
     * Toggles completion from true to false/ false to true
     **/
    public void toggleCompletion() {
        isCompleted = !isCompleted;
    }

    public String getCompletionString() {
        return "[" + (isCompleted ? "X" : " ") + "] "; // mark done task with X
    }

    public boolean getCompletion() {
        return this.isCompleted;
    }

    public String getDescription() {
        return this.description;
    }

    @Override
    public String toString() {
        return getCompletionString() + this.description;
    }
}

