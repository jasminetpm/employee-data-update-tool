public class AddCommand implements Command {
    //params
    private Employee employee;

    //receiver
    private Receiver receiver;

    public AddCommand(Receiver receiver, Employee employee) {
        this.receiver = receiver;
        this.employee = employee;
    }

    @Override
    public void execute() {
        receiver.add(employee);
    }

    @Override
    public void undo() {
        receiver.getEmployees().remove(employee);
    }
}
