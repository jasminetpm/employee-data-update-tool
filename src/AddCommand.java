public class AddCommand implements Command {
    //params
    private Employee employee;
    private String params;
    //receiver
    private Receiver receiver;

    // constructor for receiver with String payload params
    public AddCommand(Receiver receiver, String params) {
        this.receiver = receiver;
        this.params = params;
    }

    public AddCommand(Receiver receiver, Employee employee) {
        this.receiver = receiver;
        this.employee = employee;
    }

    @Override
    public boolean execute() {
        // splits params
        String[] parts = params.split(" ");
        if (parts.length != 3) {
            System.out.println("Invalid payload format. Expected: <data1> <data2> <data3>");
            return false;
        }
        // updates params to desired TitleCase format
        String firstName = Helper.newtoTitleCase(parts[0]);
        String lastName = Helper.newtoTitleCase(parts[1]);
        String email = parts[2];
        // calls isValidEmail method to verify params email format
        if (!Helper.newisValidEmail(email)) {
            System.out.println("Invalid email format.");
            return false;
        }
        // initialize Employee object with formatted + verified inputs
        Employee emp = new Employee(firstName, lastName, email);
//        employees.add(emp);
        receiver.add(emp);
        return true;
    }

    @Override
    public void undo() {
        receiver.getEmployees().remove(employee); //remove the Employee object using the List .remove method
    }
}
