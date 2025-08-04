import java.util.List;
import java.util.Stack;

public class Invoker {
    private Command[] cmdToExecute;

    public void setCommandsForExecution(Command[] commands) {
        this.cmdToExecute = commands;
    }

    public void executeCommand(Stack<Command> history) {
        if (cmdToExecute == null) {
            System.out.println("No commands set.");
            return;
        }

        else if (cmdToExecute.length == 0) {
            System.out.println("No commands to undo.");
            return;
        }

        for (Command cmd : cmdToExecute) {
            try {
                boolean undoableCommand = cmd.execute();
                if (undoableCommand) { // Only add to history if undoable
                    // add/update/delete returns true/false,
                    // undo/list should always return false as they will never push to history
                    history.push(cmd);
                }
            } catch (CommandException e) {
                System.out.println("Error executing command: " + e.getMessage());
            }
        }

    }


}
