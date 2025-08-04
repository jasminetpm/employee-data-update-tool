import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Stack;
import java.util.regex.Pattern;

/**
 * Receiver is the data store that holds the actual employee records (in an ArrayList) and implements the logic
 */
public class Receiver {
    private List<Employee> employees;

    public Receiver() {
        // check files if dataStore.txt exists
        // if yes, load employees from there
        // if no, create new employees ArrayList
        this.employees = initializeEmployees();
    }

    /**
     *  initializeEmployees checks if dataStore.txt exists
     *  if it doesn't, it creates empty employees ArrayList and returns
     *  if it does, it loads data into employees ArrayList and returns
     * @return returns empty ArrayList<Employee> or with contents from dataStore if applicable
     */
    public static ArrayList<Employee> initializeEmployees() {
        // initialize empty employees ArrayList
        ArrayList<Employee> employees = new ArrayList<>();
        Path path = Paths.get("src/dataStore.txt");
        // if the dataStore file does not exist (i.e. no file to load)
        // return the empty ArrayList
        if (!Files.exists(path)) {
            return employees;
        }

        try {
            for (String line : Files.readAllLines(path)) {
                String[] parts = line.split(" ");
                employees.add(new Employee(parts[0], parts[1], parts[2]));
            }
        } catch (IOException e) {
            System.err.println("Failed to read file: " + e.getMessage());
        }

        return employees;
    }


    public void add(Employee employee) {
        employees.add(employee);
    }

    /**
     * add method takes in a String payload and
     * 1. splits into an array, verifying correct input (3 parts)
     * 2. applies TitleCase to firstName and lastName
     * 3. calls isValidEmail to verify <data3> with regex
     * 4. if no failure, initialize Employee object and add it arrayList
     *
     * @param params String input in form "<data1> <data2> <data3>"
     * @return boolean true if success, false if any failure encountered
     */
    public boolean add(String params) {
        // splits params
        String[] parts = params.split(" ");
        if (parts.length != 3) {
            System.out.println("Invalid payload format. Expected: <data1> <data2> <data3>");
            return false;
        }
        // updates params to desired TitleCase format
        String firstName = toTitleCase(parts[0]);
        String lastName = toTitleCase(parts[1]);
        String email = parts[2];
        // calls isValidEmail method to verify params email format
        if (!isValidEmail(email)) {
            System.out.println("Invalid email format.");
            return false;
        }
        // initialize Employee object with formatted + verified inputs
        Employee emp = new Employee(firstName, lastName, email);
        employees.add(emp);
        return true;
    }

    /**
     * isValidEmail method verifies the format of String email (<data3> field)
     *
     * @param email String email <data3> split from the payload
     * @return boolean true/false if email passes regex checks
     */
    public boolean isValidEmail(String email) {
        if (email == null || email.isEmpty()) return false;

        // Non-email pattern: only letters, digits, underscores
        String nonEmailRegex = "^[A-Za-z0-9_]+$";

        // Email pattern based on spec
        String emailRegex = "^(?![._-])" +                            // Local part must not start with . _ -
                "(?!.*[.-]{2})" +                         // No consecutive . or -
                "[A-Za-z0-9._-]+" +                       // Local part characters
                "(?<![._-])@" +                           // Local part must not end with . _ -
                "(?![.-])" +                              // Domain must not start with . or -
                "(?!.*[.-]{2})" +                         // No consecutive . or - in domain
                "[A-Za-z0-9.-]+" +                        // Domain characters
                "(?<![.-])\\." +                          // Domain must not end with . or -
                "[a-z]{2,3}$";                            // Extension: 2–3 lowercase letters

        return Pattern.matches(nonEmailRegex, email) || Pattern.matches(emailRegex, email);
    }


    /**
     * toTitleCase takes in a String input and formats it into TitleCase
     *
     * @param input String to be TitleCased
     * @return String that has been formatted into TitleCase
     */
    private String toTitleCase(String input) {
        if (input == null || input.isEmpty()) return input;
        return input.substring(0, 1).toUpperCase() + input.substring(1).toLowerCase();
    }


    /**
     * Overloaded method to undo a delete command and add the deleted employee back.
     * Inserts the specified employee at the specified index within the list.
     * Existing elements at or after that index are shifted to the right
     * (their indices are incremented) to accommodate the new element
     *
     * @param index
     * @param deletedEmployee
     */
    public void add(int index, Employee deletedEmployee) {
        employees.add(deletedEmployee);
    }

//    public void update(int index, Employee employee) {
//        employees.set(index, employee);
//    }

    /**
     * update method taking index and updates according to params
     * checks index for employee and retrieves if it exists
     * verifies params format for index and data
     * retrieves employee object from list and updates it
     * ensuring titlecase and email format
     *
     * @param params payload String in form of <index> <data>...
     * @return boolean true/false based on operation pass/fail
     */
    public boolean update(String params, Employee updatedEmployee) {
        String[] parts = params.split(" ");
        if (parts.length < 2 || parts.length > 4) {
            System.out.println("Invalid payload format. Expected: <index> <data1> <data2> <data3>");
            return false;
        }

        int index;
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
        Employee emp = employees.get(index);

        // Update fields based on how many parts are present
        // update only firstName
        if (parts.length >= 2) {
            emp.setFirstName(toTitleCase(parts[1]));
        }
        // update firstName + secondName
        if (parts.length >= 3) {
            emp.setLastName(toTitleCase(parts[2]));
        }
        // update all 3 data fields
        if (parts.length == 4) {
            String data3 = parts[3];
            if (!isValidEmail(data3)) {
                System.out.println("Invalid format for <data3>.");
                return false;
            }
            emp.setEmail(data3);
        }

        System.out.println("Updated: " + emp);
        return true;
    }


    /**
     * List .remove method removes the element at the specified index
     * * and shifts any subsequent elements to the left (decreases their index by one).
     *
     * @param index
     */
    public void delete(int index) {
        employees.remove(index);
    }

    public void list() {
        for (int i = 0; i < employees.size(); i++) {
            //format the list number, ensuring it has at least two digits, and pad with leading zeros if it has fewer than two digits"
            System.out.println(String.format("%02d. %s", i + 1, employees.get(i))); //retrieve element at specific index within employees ArrayList
        }
    }

    public List<Employee> getEmployees() {
        return employees;
    }

    /**
     * storeToFile() takes in employees ArrayList and overwrites the
     * dataStore.txt at the end of Client.java's execution
     */
    public void storeToFile() {
        Path filePath = Paths.get("src/dataStore.txt");

        try (BufferedWriter writer = Files.newBufferedWriter(filePath)) {
            for (Employee employee : employees) {
                writer.write(employee.toString());
                writer.newLine();
            }
            System.out.println("Updated employee list saved to " + filePath.toAbsolutePath());

        } catch (IOException e) {
            System.err.println("Error: Failed to write file: " + e.getMessage());
        }
    }
}
