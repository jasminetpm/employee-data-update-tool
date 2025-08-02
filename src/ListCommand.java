public class ListCommand implements Command {
    // No params because no inputs required

    //receiver
    private Receiver receiver;

    public ListCommand(Receiver receiver) {
        this.receiver = receiver;
    }

    @Override
    public void execute() {
        if (receiver.getEmployees().isEmpty()) {
            System.out.println("No employees.");
        }
        else {
            receiver.list();
        }
    }

    @Override
    public void undo() {

    }
}
