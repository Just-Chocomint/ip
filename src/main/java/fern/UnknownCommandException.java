package fern;

/**
 * Represents an exception for unknown commands.
 */
public class UnknownCommandException extends FernException {
    /**
     * Constructs an UnknownCommandException.
     */
    public UnknownCommandException() {
        super("Unknown command. Type 'help' for available commands.");
    }
}
