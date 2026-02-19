package fern;

import java.time.LocalDate;

/**
 * Represents a default task with description and completion status.
 */
public class Task {
    protected String description;
    protected boolean isCompleted;

    /**
     * Constructs a Task with the given description.
     *
     * @param description the task description
     */
    public Task(String description) {
        this.description = description;
        this.isCompleted = false;
    }

    /**
     * Constructs a Task with the given description and completion status.
     *
     * @param description the task description
     * @param isCompleted the completion status of the task
     */
    public Task(String description, boolean isCompleted) {
        this.description = description;
        this.isCompleted = isCompleted;
    }

    /**
     * Returns the type of current task.
     *
     * @return the task type as a string
     */
    public String getType(){
        return "No type";
    }

    /**
     * Returns the first date (e.g., deadline or event start date).
     *
     * @return the first date as LocalDate
     */
    public LocalDate getFirstDate() {
        return LocalDate.now();
    }

    /**
     * Returns the second date (e.g., event end date).
     *
     * @return the second date as LocalDate
     */
    public LocalDate getSecondDate() {
        return LocalDate.now();
    }

    /**
     * Toggles completion status from true to false or false to true.
     */
    public void toggleCompletion() {
        isCompleted = !isCompleted;
    }

    /**
     * Returns the completion status as a formatted string.
     *
     * @return completion status string with [X] for completed or [ ] for pending
     */
    public String getCompletionString() {
        return "[" + (isCompleted ? "X" : " ") + "] "; // mark done task with X
    }

    /**
     * Returns the completion status of the task.
     *
     * @return true if task is completed, false otherwise
     */
    public boolean getCompletion() {
        return this.isCompleted;
    }

    /**
     * Returns the description of the task.
     *
     * @return the task description
     */
    public String getDescription() {
        return this.description;
    }

    @Override
    public String toString() {
        return getCompletionString() + this.description;
    }
}

