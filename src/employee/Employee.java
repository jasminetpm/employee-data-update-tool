package employee;

/**
 * The Employee class represents employees with fields:
 * first name, last name, email address.
 * This class is used throughout the command system to
 * store and manipulate employee records.
 */
public class Employee {
    /**
     * employee's first name
     */
    private String firstName;
    /**
     * employee's last name
     */
    private String lastName;
    /**
     * employee's email address
     */
    private String email;

    /**
     * Constructs new employee with specified params below
     *
     * @param firstName employee's first name
     * @param lastName  employee's last name
     * @param email     employee's email address
     */
    public Employee(String firstName, String lastName, String email) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
    }

    /**
     * Returns string representation of employee in format:
     * <firstName> <lastName> <email>
     *
     * @return formatted string representing the employee
     */
    public String toString() {
        return firstName + " " + lastName + " " + email;
    }

    /**
     * Returns employee's last name
     *
     * @return employee's last name
     */
    public String getLastName() {
        return lastName;
    }

    /**
     * Returns employee's email or data3 equivalent
     *
     * @return employee's email or data3 equivalent
     */
    public String getEmail() {
        return email;
    }
}