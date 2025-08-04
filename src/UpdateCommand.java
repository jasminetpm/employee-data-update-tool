import java.util.List;

import static java.lang.Character.toTitleCase;

public class UpdateCommand implements Command {
    //params
    private String params;
    private int index;
    private Employee updatedEmployee;

    //receiver
    private Receiver receiver;

    public UpdateCommand(Receiver receiver, String params) {
        this.receiver = receiver;
        this.params = params;
    }


    /**
     * execute parses payloads for index & data1/2/3
     * verifies payload format
     * checks index if employee exists
     * calls receiver.update(index, updatedEmployee); to update the employee
     * @return boolean true/false based on update success
     */
    @Override
    public boolean execute() {
        String[] parts = params.split(" ");
        List<Employee> employees = receiver.getEmployees();

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

        // retrieve the employee object stored at that index
        this.updatedEmployee = employees.get(index);
        String firstName = updatedEmployee.getFirstName();
        String lastName = updatedEmployee.getLastName();
        String email = updatedEmployee.getEmail();

        // Update fields based on how many parts are present
        // update only firstName
        if (parts.length >= 2) {
            firstName = Helper.toTitleCase(parts[1]);
        }
        // update firstName + secondName
        if (parts.length >= 3) {
            lastName = Helper.toTitleCase(parts[2]);
        }
        // update all 3 data fields
        if (parts.length == 4) {
            String data3 = parts[3];
            if (!Helper.isValidEmail(data3)) {
                System.out.println("Invalid format for <data3>.");
                return false;
            }
            email = data3;
        }
        receiver.update(index, new Employee(firstName, lastName, email));
        return true;
    }

    @Override
    public void undo() {
        receiver.update(index, updatedEmployee);
    }

}
