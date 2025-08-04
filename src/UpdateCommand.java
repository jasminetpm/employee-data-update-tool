public class UpdateCommand implements Command {
    //params
    private String params;
    private Employee updatedEmployee;

    //receiver
    private Receiver receiver;

    public UpdateCommand(Receiver receiver, String params) {
        this.receiver = receiver;
        this.params = params;
        this.updatedEmployee = null; //TODO: fix this class
    }

    @Override
    public void execute() {
        receiver.update(params, updatedEmployee);
    }

    @Override
    public void undo() {
//        receiver.getEmployees().add(deletedEmployee);
    }

    public void setUpdatedEmployee(Employee updatedEmployee) {
        this.updatedEmployee = updatedEmployee;
    }
}
