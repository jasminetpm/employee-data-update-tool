package commands;

import exceptions.CommandException;
import receiver.Receiver;

import java.util.Stack;

/**
 * UndoCommand class implements Command interface.
 * This command reverses most recent command in history.
 * Pops last command and invokes its undo(), throwing CommandException
 * if there is nothing to undo
 * Note: This command itself is not undoable, always returns false
 */
public class UndoCommand implements Command {
    /**
     * history stack containing previously successfully executed commands
     */
    private Stack<Command> history;
    /**
     * receiver object passed into the constructor from the client
     */
    private Receiver receiver;

    /**
     * public constructor for the class UndoCommand
     *
     * @param receiver object to link to receiver class
     * @param history  stack of Commands that were successfully executed
     */
    public UndoCommand(Receiver receiver, Stack<Command> history) {
        this.history = history;
        this.receiver = receiver;
    }

    /**
     * executes undo by popping most recent command from history and invoking
     * their undo methods
     *
     * @return false is returned as undo cannot be undone
     * @throws CommandException is thrown if history is empty with nothing to undo
     */
    @Override
    public boolean execute() throws CommandException {
        if (!history.isEmpty()) {
            Command last = history.pop();
            System.out.println("Undo");
            last.undo();
        } else {
            throw new CommandException("Nothing to undo.");
        }
        return false;
    }

    /**
     * empty body as this command is undoable
     */
    @Override
    public void undo() {
        // no undo behaviour as this command is undoable
    }
}
