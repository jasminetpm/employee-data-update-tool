/**
 * Custom exception class for all non Throwable error messages
 */
public class CommandException extends Exception {
    public CommandException(String message) {
        super(message);
    }
}
