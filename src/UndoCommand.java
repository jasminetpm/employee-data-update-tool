import java.util.Stack;

public class UndoCommand implements Command {
    //no params because no inputs required

    //history
    private Stack<Command> history;
    private Receiver receiver;

    public UndoCommand(Receiver receiver,  Stack<Command> history) {
        this.history = history;
        this.receiver = receiver;
    }

    @Override
    public boolean execute() {
        if (!history.isEmpty()) {
            Command last = history.pop();
            last.undo();
        } else {
            System.out.println("Nothing to undo.");
        }
        return false;
    }

    @Override
    public void undo() {
    }
}
