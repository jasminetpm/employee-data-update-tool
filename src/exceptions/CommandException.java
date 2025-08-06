package exceptions;

/**
 * Custom exception class for all non Throwable error messages
 */
public class CommandException extends Exception {
    /**
     * Constructs a new CommandException with specific message.
     *
     * @param message details describing the error
     */
    public CommandException(String message) {
        super(message);
    }
}