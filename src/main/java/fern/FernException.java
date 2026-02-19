package fern;

/**
 * Represents an exception specific to the Fern application.
 */
public class FernException extends Exception {
    /**
     * Constructs a FernException with the specified message.
     *
     * @param msg the error message
     */
    public FernException(String msg) {
        super(msg);
    }
}
