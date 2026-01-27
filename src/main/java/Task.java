public class Task {
    protected String description;
    protected boolean isCompleted;

    public Task(String description) {
        this.description = description;
        this.isCompleted = false;
    }

    public Task(String description, boolean isCompleted) {
        this.description = description;
        this.isCompleted = isCompleted;
    }

    public String getType(){
        return "No type";
    }

    public String getFirstDate(){
        return "No date";
    }

    public String getSecondDate(){
        return "No date";
    }

    public void toggleCompletion() {
        isCompleted ^= true;
    }

    public boolean getIsCompleted() {
        return this.isCompleted;
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
    public String toString(){
        return getCompletionString() + this.description;
    }
}

