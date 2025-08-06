package commands;

import receiver.Receiver;
import employee.Employee;
import exceptions.CommandException;

public class AddCommand implements Command {
    /**
     * employee object of the employee we wish to add
     */
    private Employee addedEmployee;
    /**
     * command payload received from client
     */
    private String params;
    /**
     * receiver object passed into the constructor from the client
     */
    private Receiver receiver;

    /**
     * public constructor for the class AddCommand
     *
     * @param receiver object to link to receiver class
     * @param params   payload in format <data1> <data2> <data3>
     */
    public AddCommand(Receiver receiver, String params) {
        this.receiver = receiver;
        this.params = params;
    }

    /**
     * method parses instance variable String params and
     * 1. splits into an array, verifying correct input (3 parts)
     * 2. applies TitleCase to firstName and lastName
     * 3. calls isValidEmail to verify <data3> with regex
     * 4. if no failure, initialize employee.Employee object and add it arrayList
     *
     * @return boolean true if success, false if any failure encountered
     */
    @Override
    public boolean execute() throws CommandException {
        // splits params
        String[] parts = params.split(" ");
        if (parts.length != 3) {
            throw new CommandException("Invalid payload format. Expected: <data1> <data2> <data3>");
        }
        // updates params to desired TitleCase format
        String firstName = Helper.toTitleCase(parts[0]);
        String lastName = Helper.toTitleCase(parts[1]);
        String email = parts[2];
        // calls isValidEmail method to verify params email format
        if (!Helper.isValidEmail(email)) {
            throw new CommandException("Invalid email format.");
        }
        email = Helper.toTitleCase(email);
        // initialize employee.Employee object with formatted + verified inputs
        this.addedEmployee = new Employee(firstName, lastName, email);
        System.out.println("add");
        receiver.add(addedEmployee);
        return true;
    }

    /**
     * Overridden undo method to undo an add command
     * calls receiver.update with the saved data of overridden employee
     */
    @Override
    public void undo() {
        receiver.getEmployees().remove(addedEmployee); // remove the employee.Employee object using the List .remove method
    }
}
