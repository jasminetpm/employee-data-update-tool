import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.file.*;
import java.nio.file.Path;
import java.nio.file.Paths;
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
                //TODO: check execute's boolean return to decide if we should .push
                // should we push list commands?
                history.push(cmd);  // Only add to history if undoable
            }
        }

    }


}
