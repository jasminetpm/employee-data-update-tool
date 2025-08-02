import java.util.ArrayList;
import java.util.List;

/**
 * Receiver is the data store that holds the actual employee records (in an ArrayList) and implements the logic
 */
public class Receiver {
    private List<Employee> employees = new ArrayList<>();

    public void add(Employee employee) {
        employees.add(employee);
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
        if (employees.isEmpty()) {
            System.out.println("No employees.");
        } else {
            for (int i = 0; i < employees.size(); i++) {
                //format the list number, ensuring it has at least two digits, and pad with leading zeros if it has fewer than two digits"
                System.out.println(String.format("%02d. %s", i + 1, employees.get(i))); //retrieve element at specific index within employees ArrayList
            }
        }
    }

    public List<Employee> getEmployees() {
        return employees;
    }
}
