public class ListCommand implements Command {
    // No params because no inputs required

    //receiver
    private Receiver receiver;

    public ListCommand(Receiver receiver) {
        this.receiver = receiver;
    }

    @Override
    public boolean execute() {
        if (receiver.getEmployees().isEmpty()) {
            System.out.println("No employees.");
            return false;
        }
        else {
            receiver.list();
            return true;
        }
    }

    @Override
    public void undo() {

    }
}
