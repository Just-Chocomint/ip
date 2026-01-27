import java.time.LocalDate;

public class Event extends Task{
    protected LocalDate from;
    protected LocalDate to;

    public Event(String description, LocalDate from, LocalDate to) {
        super(description);
        this.from = from;
        this.to = to;
    }

    public Event(String description, LocalDate from, LocalDate to, boolean completed) {
        super(description, completed);
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

    // Return's the type of this task "E" for Event
    @Override
    public String getType(){
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
