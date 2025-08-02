import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

/**
 * Receiver is the data store that holds the actual employee records (in an ArrayList) and implements the logic
 */
public class Receiver {
    private List<Employee> employees = new ArrayList<>();

    public void add(Employee employee) {
        employees.add(employee);
    }

    /**
     *  add method takes in a String payload and
     *  1. splits into an array, verifying correct input (3 parts)
     *  2. applies TitleCase to firstName and lastName
     *  3. calls isValidEmail to verify <data3> with regex
     *  4. if no failure, initialize Employee object and add it arrayList
     *
     * @param payload String input in form "<data1> <data2> <data3>"
     * @return boolean true if success, false if any failure encountered
     */
    public boolean add(String payload) {
        // splits payload
        String[] parts = payload.split(" ");
        if (parts.length != 3) {
            System.out.println("Invalid payload format. Expected: <data1> <data2> <data3>");
            return false;
        }
        // updates payload to desired TitleCase format
        String firstName = toTitleCase(parts[0]);
        String lastName = toTitleCase(parts[1]);
        String email = parts[2];
        // calls isValidEmail method to verify payload email format
        if (!isValidEmail(email)) {
            System.out.println("Invalid email format.");
            return false;
        }
        // initialize Employee object with formatted + verified inputs
        Employee emp = new Employee(firstName, lastName, email);
        employees.add(emp);
        System.out.println("Added: " + emp);
        return true;
    }

    /**
     *  isValidEmail method verifies the format of String email (<data3> field)
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
     * @param index
     * @param deletedEmployee
     */
    public void add(int index, Employee deletedEmployee) {
        employees.add(deletedEmployee);
    }

    public void update(int index, Employee employee) {
        employees.set(index, employee);
    }

    /**
     * List .remove method removes the element at the specified index
     *      * and shifts any subsequent elements to the left (decreases their index by one).
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
}
