public class DeleteCommand implements Command {
    //params
    private int index;
    private Employee deletedEmployee;

    //receiver
    private Receiver receiver;

    public DeleteCommand(Receiver receiver, String index) throws CommandException {
        this.receiver = receiver;
        try {
            this.index = Integer.parseInt(index) - 1; //index is based off -1 of the index shown using the List command
        } catch (NumberFormatException e) {
            System.out.println("Invalid index: please enter a whole number.");
        }
    }

    /**
     * Checks if the index input is valid
     * Then deletes the element at the index, if valid
     */
    @Override
    public boolean execute() throws CommandException {
        if (index >= 0 && index < receiver.getEmployees().size()) {
            // Store the deleted employee for undo method to use
            deletedEmployee = receiver.getEmployees().get(index);
            // deletes the element at the index
            System.out.println("Delete");
            receiver.delete(index);
            return true;
        } else {
            throw new CommandException("Invalid index provided for delete command.");
        }
    }

    @Override
    public void undo() {
        receiver.getEmployees().add(index,deletedEmployee);
    }
}
