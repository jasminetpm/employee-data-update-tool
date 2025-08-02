import java.util.ArrayList;
import java.util.List;

/**
 * Receiver is the data store that holds the actual employee records (in an ArrayList) and implements the logic
 */
public class Receiver {
    private List<Employee> employees = new ArrayList<>();

    public void add(Employee e) {
        employees.add(e);
    }

    public void update(int index, Employee e) {
        employees.set(index, e);
    }

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
