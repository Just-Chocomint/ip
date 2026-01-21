public class Task {
    protected String description;
    protected boolean isCompleted;

    public Task(String description) {
        this.description = description;
        this.isCompleted = false;
    }

    public void toggleCompletion() {
        isCompleted ^= true;
    }

    public String getCompletionString() {
        return "[" + (isCompleted ? "x" : " ") + "] "; // mark done task with X
    }

    public boolean getCompletion() {
        return this.isCompleted;
    }

    public String getDescription() {
        return this.description;
    }
}

