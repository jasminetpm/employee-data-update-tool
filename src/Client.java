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
        Command command1 = new AddCommand(receiver, "Jane Doe simple@example.com");
        Command command2 = new AddCommand(receiver, "Hanna Moon tetter.tots@potatoesarelife.com");
        Command command3 = new AddCommand(receiver, "JINGHAN wu jinghan.wu@dbs.com");
        Command undoCommanda = new UndoCommand(receiver, history);
        Command listCommand = new ListCommand(receiver);

//        Command updateCommand = new UpdateCommand(receiver,"3 JANE DOE jane.doe@gmail.com");
        Command updateCommand = new UpdateCommand(receiver,"3 JANE");
//        Command command4 = new DeleteCommand(receiver,2);
//        Command[] cmdToExecute = new Command[] {command1, command2, command3, command4};

        Command[] cmdToExecute = new Command[] {command1, command2, command3,listCommand, updateCommand, listCommand};

        // Test 3
//        Command updateCommand = new UpdateCommand(receiver,"4 JANE DOE tots.tetter@gmail.com");
//        Command[] cmdToExecute = new Command[]{updateCommand};


        // executing commands via invoker
        invoker.setCommandsForExecution(cmdToExecute);
        invoker.executeCommand(history);


        // store updated employee ArrayList into the dataStore.txt at the end of program
//        receiver.storeToFile();

    }
}
