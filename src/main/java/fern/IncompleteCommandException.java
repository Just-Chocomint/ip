package fern;

/**
 * Represents an exception for incomplete commands.
 */
public class IncompleteCommandException extends FernException {
    /**
     * Constructs an IncompleteCommandException with the specified missing element.
     *
     * @param msg the missing element in the command
     */
    public IncompleteCommandException(String msg) {
        super("Your command is incomplete, missing: " + msg);
    }
}
