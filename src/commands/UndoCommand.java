package commands;

import exceptions.CommandException;
import receiver.Receiver;

import java.util.Stack;

public class UndoCommand implements Command {
    //no params because no inputs required

    //history
    private Stack<Command> history;
    private Receiver receiver;

    public UndoCommand(Receiver receiver, Stack<Command> history) {
        this.history = history;
        this.receiver = receiver;
    }

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

    @Override
    public void undo() {
    }
}
