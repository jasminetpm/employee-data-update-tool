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

        for (Command cmd : cmdToExecute) {
            cmd.execute();
            if (!((cmd instanceof UndoCommand) || (cmd instanceof ListCommand))) {
                history.push(cmd);  // Only add to history if undoable
            }
            System.out.println("history "+history);
            System.out.println();
        }

    }


}
