public class ListCommand implements Command {
    // No params because no inputs required

    //receiver
    private Receiver receiver;

    public ListCommand(Receiver receiver) {
        this.receiver = receiver;
    }

    @Override
    public boolean execute() throws CommandException {
        if (receiver.getEmployees().isEmpty()) {
            throw new CommandException("No employees.");
        }
        else {
            receiver.list();
        }
        return false;
    }

    @Override
    public void undo() {

    }
}
