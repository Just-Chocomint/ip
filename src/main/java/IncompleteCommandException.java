public class IncompleteCommandException extends FernException{
    public IncompleteCommandException(String msg) {
        super("Fern: your command incomplete, missing:" + msg);
    }
}
