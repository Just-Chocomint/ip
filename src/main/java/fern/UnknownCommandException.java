package fern;

public class UnknownCommandException extends FernException {
    public UnknownCommandException() {
        super("fern.Fern: ? idk that command");
    }
}
