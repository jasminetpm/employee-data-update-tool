public class DeleteCommand implements Command {
    //params
    private String index;
    private int indexNum;
    private Employee deletedEmployee;

    //receiver
    private Receiver receiver;

    public DeleteCommand(Receiver receiver, String index) {
        this.receiver = receiver;
        this.index = index;
    }

    /**
     * Checks if the index input is valid
     * Then deletes the element at the index, if valid
     */
    @Override
    public boolean execute() throws CommandException {
        try {
            indexNum = Integer.parseInt(index) - 1; //index is based off -1 of the index shown using the List command
            if (indexNum >= 0 && indexNum < receiver.getEmployees().size()) {
                // Store the deleted employee for undo method to use
                deletedEmployee = receiver.getEmployees().get(indexNum);
                // deletes the element at the index
                System.out.println("Delete");
                receiver.delete(indexNum);
                return true;
            } else {
                throw new CommandException("Invalid index provided for delete command.");
            }
        } catch (NumberFormatException e) {
            System.out.println("Invalid index type: please enter a whole number.");
            return false;
        }
    }

    @Override
    public void undo() {
        receiver.getEmployees().add(indexNum,deletedEmployee);
    }
}
