package exceptions;

/**
 * Custom exception class for all non Throwable error messages
 * occurring during the execution of a command
 */
public class CommandException extends Exception {
    /**
     * Constructs a new CommandException with specific message.
     *
     * @param message detail message describing the error
     */
    public CommandException(String message) {
        super(message);
    }
}