package fern;

public class IncompleteCommandException extends FernException {
    public IncompleteCommandException(String msg) {
        super("your command incomplete, missing:" + msg);
    }
}
