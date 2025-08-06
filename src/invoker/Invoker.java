package invoker;

import commands.Command;
import exceptions.CommandException;


import java.util.Stack;

/**
 * Invoker class responsible for executing commands array through their
 * respectively execute() methods.
 * Invoker also pushes successful Add/Delete/Update Commands into history stack
 */
public class Invoker {
    /**
     * Array of Commands to be executed
     */
    private Command[] cmdToExecute;

    /**
     * Sets command array for execution
     *
     * @param commands array of Command objects for execution
     */
    public void setCommandsForExecution(Command[] commands) {
        this.cmdToExecute = commands;
    }

    /**
     * Executes commands in the cmdToExecute array.
     * Each command calls execute and pushes to history for add/update/delete
     * if they are successful, catching CommandExceptions if errors are encountered
     *
     * @param history Stack to track successfully executed commands
     */
    public void executeCommand(Stack<Command> history) {
        // if nothing to execute we can return
        if (cmdToExecute == null) {
            System.out.println("No commands set.");
            return;
        } else if (cmdToExecute.length == 0) {
            System.out.println("No commands to undo.");
            return;
        }
        // loop through the array of Commands and call their execute methods
        for (Command cmd : cmdToExecute) {
            try {
                boolean doableCommand = cmd.execute();
                if (doableCommand) {
                    // push to history if doable add/update/delete
                    history.push(cmd);
                }
            } catch (CommandException e) {
                System.out.println("Error executing command: " + e.getMessage());
            }
        }

    }


}
