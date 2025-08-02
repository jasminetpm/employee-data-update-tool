public class DeleteCommand implements Command {
    //params
    private int index;
    private Employee deletedEmployee;

    //receiver
    private Receiver receiver;

    public DeleteCommand(Receiver receiver, int index) {
        this.receiver = receiver;
        this.index = index;
    }

    /**
     * Checks if the index input is valid
     * Then deletes the element at the index, if valid
     */
    @Override
    public void execute() {
        if (index >= 0 && index < receiver.getEmployees().size()) {
            // Store the deleted employee for undo method to use
            deletedEmployee = receiver.getEmployees().get(index);
            // deletes the element at the index
            receiver.delete(index);
        } else {
            System.out.println("Invalid index for delete.");
        }
    }

    @Override
    public void undo() {
        receiver.getEmployees().add(deletedEmployee);
    }
}
