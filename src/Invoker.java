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
                history.push(cmd);  // Only add to history if undoable
            }
        }
        // store successfully executed commands into the dataStore.txt
        storeToFile(history);
    }

    /**
     *  storeToFile() takes in history stack and overwrites the
     *  dataStore.txt after every successful executeCommand
     * @param history Command Stack history of successfully executed commands
     */
    public static void storeToFile(Stack<Command> history) {
        Path filePath = Paths.get("src/dataStore.txt");

        try (BufferedWriter writer = Files.newBufferedWriter(filePath)) {
            for (Command cmd : history) {
                writer.write(cmd.toString());
                writer.newLine();
            }
            System.out.println("Command history saved to " + filePath.toAbsolutePath());

        } catch (IOException e) {
            System.err.println("Error: Failed to write file: " + e.getMessage());
        }
    }
}
