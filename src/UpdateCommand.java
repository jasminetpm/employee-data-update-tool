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
    public boolean execute() {
        // @jinghan please add in update checking logic
        receiver.update(params, updatedEmployee);
        return true;
    }

    @Override
    public void undo() {
//        receiver.getEmployees().add(deletedEmployee);
    }

    public void setUpdatedEmployee(Employee updatedEmployee) {
        this.updatedEmployee = updatedEmployee;
    }
}
