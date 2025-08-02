import java.util.Scanner;
import java.util.Stack;

public class Client {
    public static void main(String[] args) {

        Receiver dataStore = new Receiver();
        Stack<Command> history = new Stack<>();
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.print("> ");
            String input = scanner.nextLine().trim().toLowerCase();
            if (input.equals("exit")) break;

            Command cmd = null;

            // Parse input & construct Command object
            if (input.startsWith("add")) {
                // parse name/email
                Employee e = new Employee("John", "Doe", "test@example.com"); // example
                cmd = new AddCommand(dataStore, e);
            }

            // Wrap and execute
            Invoker invoker = new Invoker();
            invoker.setCommandsForExecution(new Command[]{cmd}); //Create an array with 1 element, {cmd}
            invoker.executeCommand(history);
        }

        scanner.close();

        // Create empty Stack<Command> history to store history
//        Stack<Command> history = new Stack();
//
        // loop to take payload
//        AddCommand addCommand = new AddCommand();
//        UpdateCommand updateCommand = new UpdateCommand();
//        DeleteCommand deleteCommand = new DeleteCommand();
//        ListCommand listCommand = new ListCommand();
//        UndoCommand undoCommand = new UndoCommand();
//        Command[] commands = {
//                addCommand,
//                updateCommand,
//                deleteCommand,
//                listCommand,
//                undoCommand
//        };

        // create invoker object
//        Invoker invoker = new Invoker();

        // set commands for invoker
//        invoker.setCommandsForExecution(commands);

        // execute the commands
//        invoker.executeCommand(Stack<Command> history) {
//            history.push(new Command());
//        }
    }
}
