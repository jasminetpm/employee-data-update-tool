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
//        Command command1 = new AddCommand(receiver, "Jane Doe email109");
//        Command command2 = new AddCommand(receiver, "Hanna Moon tetter.tots@potatoesarelife.com");
//        Command command3 = new AddCommand(receiver, "JINGHAN wu jinghan.wu@dbs.com");
//        Command command4 = new AddCommand(receiver, "JINGHAN wu jinghan.wu@dbs.com");
//        Command command5 = new AddCommand(receiver, "JINGHAN wu jinghan.wu@dbs.com");
//        Command command6 = new AddCommand(receiver, "JINGHAN wu jinghan.wu@dbs.com");
//        Command undoCommand = new UndoCommand(receiver, history);
        Command listCommand = new ListCommand(receiver);


        Command updateCommand = new UpdateCommand(receiver,"1.0 JANE DOE jane.doe@gmail.com");
//        Command updateCommand = new UpdateCommand(receiver,"0 JENNY");
        Command deleteCommand = new DeleteCommand(receiver,"1.0");
//        Command deleteCommand2 = new DeleteCommand(receiver,"3");

//        Command[] cmdToExecute = new Command[] {command1, command2, command3,deleteCommand,listCommand,updateCommand,undoCommand,deleteCommand2,listCommand};
//        Command[] cmdToExecute = new Command[] {command1, listCommand, listCommand, listCommand,undoCommand, listCommand, undoCommand};
        Command[] cmdToExecute = new Command[] {listCommand};
//        Command[] cmdToExecute = {command1,deleteCommand};

//        Command[] cmdToExecute = new Command[] {command1, command2, command3, updateCommand,listCommand, undoCommand, listCommand };
//        Command[] cmdToExecute = new Command[] {command1, command2, command3, listCommand, deleteCommand, listCommand, undoCommand, listCommand};

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
