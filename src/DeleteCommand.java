public class DeleteCommand implements Command {
    //params
    private int index;
    private Employee deletedEmployee;

    //receiver
    private Receiver receiver;

    public DeleteCommand(Receiver receiver, int index) {
        this.receiver = receiver;
        this.index = index-1; //index is based off -1 of the index shown using the List command
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
