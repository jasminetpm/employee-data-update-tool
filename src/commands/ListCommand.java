package commands;

import receiver.Receiver;

/**
 * ListCommand class implements Command interface.
 * This command displays the current list of employees
 * by calling receiver.list();
 */
public class ListCommand implements Command {

    /**
     * receiver object passed into the constructor from the client
     */
    private Receiver receiver;

    /**
     * public constructor for the class ListCommand
     *
     * @param receiver object to link to receiver class
     */
    public ListCommand(Receiver receiver) {
        this.receiver = receiver;
    }

    /**
     * executes list command by invoking receiver.list();
     *
     * @return boolean false so command is not pushed into history stack
     */
    @Override
    public boolean execute() {
        System.out.println("List");
        receiver.list();
        return false;
    }

    /**
     * empty body as list is read-only method that cannot be undone
     */
    @Override
    public void undo() {
        // no undo behaviour for read-only command
    }
}
