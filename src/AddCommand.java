public class AddCommand implements Command {
    //params
    private Employee addedEmployee;
    private String params;
    //receiver
    private Receiver receiver;

    // constructor for receiver with String payload params
    public AddCommand(Receiver receiver, String params) {
        this.receiver = receiver;
        this.params = params;
    }

    /**
     * method parses instance variable String params and
     * 1. splits into an array, verifying correct input (3 parts)
     * 2. applies TitleCase to firstName and lastName
     * 3. calls isValidEmail to verify <data3> with regex
     * 4. if no failure, initialize Employee object and add it arrayList
     *
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
        // initialize Employee object with formatted + verified inputs
        this.addedEmployee = new Employee(firstName, lastName, email);
        receiver.add(addedEmployee);
        return true;
    }

    @Override
    public void undo() {
        receiver.getEmployees().remove(addedEmployee); // remove the Employee object using the List .remove method
    }
}
