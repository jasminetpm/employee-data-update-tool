import java.util.List;
import java.util.Stack;

public class Invoker {
    private Command[] cmdToExecute;

    public void setCommandsForExecution(Command[] commands) {
        this.cmdToExecute = commands;
    }

    public void executeCommand(Stack<Command> history) throws CommandException {
        if (cmdToExecute == null) {
            throw new CommandException("No commands set.");
        }

        else if (cmdToExecute.length == 0) {
            throw new CommandException("No commands to execute.");
        }

        for (Command cmd : cmdToExecute) {
            boolean undoableCommand = cmd.execute();
                if (undoableCommand) { // Only add to history if undoable
                    history.push(cmd);
                }
                // add/update/delete returns true/false,
                // undo/list should always return false as they will never push to history
        }

    }


}
