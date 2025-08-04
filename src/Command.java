import java.util.Stack;

public interface Command {

    public boolean execute() throws CommandException;

    public void undo();

}
