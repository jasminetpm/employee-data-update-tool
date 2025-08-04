import java.util.Scanner;
import java.util.Stack;

public class Client {
    public static void main(String[] args) {
        // history
        Stack<Command> history = new Stack<>();

        // Create Receiver
        Receiver receiver = new Receiver();

        // Create Invoker
        Invoker invoker = new Invoker();

        // Test 1
        Employee employee1 = new Employee("Jane",  "Doe", "simple@example.com");
        Command command1 = new AddCommand(receiver, employee1);
        Employee employee2 = new Employee("Hanna", "Moon", "tetter.tots@potatoesarelife.com");
        Command command2 = new AddCommand(receiver, employee2);
        Employee employee3 = new Employee("MAAA", "MooAAAn", "tetter.tots@potatoesarelife.com");
        Command command3 = new AddCommand(receiver, employee3);

        Command command4 = new DeleteCommand(receiver,2);
//        Command command4 = new ListCommand(receiver);
//        Command[] cmdToExecute = new Command[] {command1, command2, command3, command4};

        // Test 2
//        Command command3 = new DeleteCommand(receiver,1);
        Command listCommand = new ListCommand(receiver);
        Command undoCommand = new UndoCommand(receiver,history);
//        System.out.println(history);
        Command[] cmdToExecute = new Command[] {command1, command2, command3, undoCommand, listCommand};

        // Test 3
//        Command updateCommand = new UpdateCommand(receiver,"4 JANE DOE tots.tetter@gmail.com");
//        Command[] cmdToExecute = new Command[]{updateCommand};

        // Test 4: example add with String data as payload
//        Command cmd1 = new AddCommand(receiver, "may tan may.tan@example.com");
//        Command cmd2 = new ListCommand(receiver);
//        Command[] cmdToExecute = {cmd1, cmd2};

        // executing commands via invoker
        invoker.setCommandsForExecution(cmdToExecute);
        invoker.executeCommand(history);


        // store updated employee ArrayList into the dataStore.txt at the end of program
//        receiver.storeToFile();

    }
}
