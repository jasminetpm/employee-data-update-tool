public class AddCommand implements Command {
    //params
    private Employee employee;
    private String params;

    //receiver
    private Receiver receiver;

    // constructor for receiver with String payload params
    public AddCommand(Receiver receiver, String params) {
        this.receiver = receiver;
        this.params = params;
    }

    public AddCommand(Receiver receiver, Employee employee) {
        this.receiver = receiver;
        this.employee = employee;
    }
//     execute method for String params
//    @Override
//    public void execute() {
//        receiver.add(params);
//    }


    @Override
    public boolean execute() {
        receiver.add(employee);
        return true;
    }

    @Override
    public void undo() {
        receiver.getEmployees().remove(employee); //remove the Employee object using the List .remove method
    }
}
