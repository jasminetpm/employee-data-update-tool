import java.util.Stack;

public class UndoCommand implements Command {
    //no params because no inputs required

    //history
    private Stack<Command> history;

    public UndoCommand(Receiver receiver, String params) {
        this.history = history;
    }

    @Override
    public void execute() {
        if (!history.isEmpty()) {
            Command last = history.pop();
            last.undo();
        } else {
            System.out.println("Nothing to undo.");
        }
    }

    @Override
    public void undo() {

    }
}
