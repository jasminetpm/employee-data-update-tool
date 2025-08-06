package commands;

import employee.Employee;
import receiver.Receiver;

import java.util.List;

/**
 * UpdateCommand class implements Command interface.
 * It handles the logic for updating (and its undo) existing employees.
 * Executing the command parses a payload String, validates its formats,
 * and passes the valid employee with index to receiver.update() for updating.
 */
public class UpdateCommand implements Command {
    /**
     * command payload received from client
     */
    private String params;
    /**
     * index of the employee we wish to update
     */
    private int index;
    /**
     * employee object of the employee before updating
     */
    private Employee updatedEmployee;
    /**
     * receiver object passed into the constructor from the client
     */
    private Receiver receiver;

    /**
     * public constructor for the class UpdateCommand
     *
     * @param receiver object to link to receiver class
     * @param params   payload in format <index> <data1> <data2?> <data3?>
     */
    public UpdateCommand(Receiver receiver, String params) {
        this.receiver = receiver;
        this.params = params;
    }

    /**
     * Method execute parses payloads for index and data1-3.
     * Verifies payload format, checks index if employee exists,
     * calls receiver.update(index, updatedEmployee); to update the employee
     *
     * @return boolean true/false based on update success
     */
    @Override
    public boolean execute() {
        // retrieve our arraylist of employees
        List<Employee> employees = receiver.getEmployees();

        // verify payload has valid number of parts
        String[] parts = params.split(" ");
        if (parts.length < 2 || parts.length > 4) {
            System.out.println("Invalid payload format. Expected: <index> <data1> <data2> <data3>");
            return false;
        }
        // get the value of index from our String params
        try {
            index = Integer.parseInt(parts[0]) - 1; // Convert to 0-based index
        } catch (NumberFormatException e) {
            System.out.println("Invalid index format.");
            return false;
        }
        // check index exists within our employees list
        if (index < 0 || index >= employees.size()) {
            System.out.println("Index out of bounds.");
            return false;
        }
        // retrieve and store the employee object stored at that index
        this.updatedEmployee = employees.get(index);
        String firstName = updatedEmployee.getFirstName();
        String lastName = updatedEmployee.getLastName();
        String email = updatedEmployee.getEmail();
        // Update fields based on how many parts are present and add titlecase
        // update only firstName
        if (parts.length >= 2) {
            firstName = Helper.toTitleCase(parts[1]);
        }
        // update firstName + lastName
        if (parts.length >= 3) {
            lastName = Helper.toTitleCase(parts[2]);
        }
        // update all firstName + lastName + email data fields
        if (parts.length == 4) {
            String data3 = parts[3];
            if (!Helper.isValidEmail(data3)) {
                System.out.println("Invalid format for <data3>.");
                return false;
            }
            email = Helper.toTitleCase(data3);
        }
        System.out.println("update");
        receiver.update(index, new Employee(firstName, lastName, email));
        return true;
    }

    /**
     * Overridden undo method to undo an update command
     * calls receiver.update() with the saved data of overridden employee
     */
    @Override
    public void undo() {
        receiver.update(index, updatedEmployee);
    }

}
