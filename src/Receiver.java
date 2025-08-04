import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

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

    /**
     * method to add an employee to the employees arraylist with .add()
     *
     * @param employee takes in employee object to be added to the arraylist
     */
    public void add(Employee employee) {
        employees.add(employee);
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
        employees.add(index, deletedEmployee);
    }

    /**
     * update method taking index and employee object
     * and updates (sets) the employee arraylist
     * @param index index of employee to update
     * @param updatedEmployee employee object we will use for overwriting
     */
    public void update(int index, Employee updatedEmployee) {
        employees.set(index, updatedEmployee);
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
