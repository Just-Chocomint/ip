public class Event extends Task{
    protected String from;
    protected String to;

    public Event(String description, String from, String to) {
        super(description);
        this.from = from;
        this.to = to;
    }

    public Event(String description, String from, String to, boolean completed) {
        super(description, completed);
        this.from = from;
        this.to = to;
    }

    @Override
    public String getFirstDate() {
        return this.from;
    }

    @Override
    public String getSecondDate() {
        return this.to;
    }

    // Return's the type of this task "E" for Event
    @Override
    public String getType(){
        return "E";
    }

    @Override
    public String toString() {
        return "[Event]" + super.toString() + " (From: " + from + "| To: " + to +")";
    }
}
