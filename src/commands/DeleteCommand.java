package commands;

import employee.Employee;
import exceptions.CommandException;
import receiver.Receiver;

/**
 * DeleteCommand class implements Command interface.
 * It contains the logic for deleting (and its undo) an employee from the system.
 * Executing the command parses a payload String, validates its formats,
 * and deletes the employee at a specified index through receiver.delete().
 * Note: This command supports undo functionality, allowing the deleted employee
 * to be restored if necessary.
 */
public class DeleteCommand implements Command {
    /**
     * raw payload received from client
     */
    private String index;
    /**
     * parsed payload if possible
     */
    private int indexNum;
    /**
     * Employee object that was deleted
     */
    private Employee deletedEmployee;
    /**
     * receiver object passed into the constructor from the client
     */
    private Receiver receiver;

    /**
     * public constructor for the class DeleteCommand
     *
     * @param receiver object to link to receiver class
     * @param index    payload in format <index> of employee to be deleted
     */
    public DeleteCommand(Receiver receiver, String index) {
        this.receiver = receiver;
        this.index = index;
    }

    /**
     * Checks if the index input is valid, and deletes the element at
     * the index if it is valid. Verifies index format is valid and
     * calls receiver.delete(indexNum).
     *
     * @return boolean true/false based on delete success
     * @throws CommandException is thrown if index is invalid
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

    /**
     * Undo delete command by inserting the previously deleted employee
     * at the specified index
     */
    @Override
    public void undo() {
        receiver.getEmployees().add(indexNum, deletedEmployee);
    }
}
