import java.util.Scanner;
import java.util.Stack;

public class Client {
    public static void main(String[] args) {

        // Test

        // Create Receiver
        Receiver receiver = new Receiver();

        // Create Invoker
        Invoker invoker = new Invoker();

        Employee employee1 = new Employee("John",  "Doe", "simple@example.com");
        Command command1 = new AddCommand(receiver, employee1);
        // example add with String data as payload
//        Command addCommand = new AddCommand(receiver, "data1 data2 data3");
        Employee employee2 = new Employee("Hanna", "Moon", "tetter.tots@potatoesarelife.com");
        Command command2 = new AddCommand(receiver, employee2);
        Command command3 = new DeleteCommand(receiver,2);
        Command command4 = new ListCommand(receiver);

        Command[] cmdToExecute = new Command[] {command1, command2, command3, command4};

        invoker.setCommandsForExecution(cmdToExecute);
        Stack<Command> history = new Stack<>();
        invoker.executeCommand(history);

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
