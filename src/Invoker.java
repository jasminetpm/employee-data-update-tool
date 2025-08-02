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

        for (Command cmd : cmdToExecute) {
            if (cmd != null) {
                cmd.execute();
                history.push(cmd);  // Only add to history if undoable
            }
        }
    }
}
