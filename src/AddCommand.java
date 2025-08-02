public class AddCommand implements Command {
    //params
    private Employee employee;
    private String payload;

    //receiver
    private Receiver receiver;

    // constructor for receiver with String payload params
    public AddCommand(Receiver receiver, String payload) {
        this.receiver = receiver;
        this.payload = payload;
    }

    public AddCommand(Receiver receiver, Employee employee) {
        this.receiver = receiver;
        this.employee = employee;
    }
    // execute method for String payload
//    @Override
//    public void execute() {
//        receiver.add(payload);
//    }


    @Override
    public void execute() {
        receiver.add(employee);
    }

    @Override
    public void undo() {
        receiver.getEmployees().remove(employee); //remove the Employee object using the List .remove method
    }
}
