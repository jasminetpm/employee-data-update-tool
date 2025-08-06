package client;

import commands.*;
import invoker.Invoker;
import receiver.Receiver;

import java.util.Stack;

/**
 * Client class is the entry for our command-based application
 * and acts as the controller in the Command design pattern.
 * It creates stack to maintain command history, instantiates receiver
 * to handle business logic, invoker manages command executions.
 * Commands are instantiated and added to a Command[] array,
 * invoker sets this array and executes it
 */
public class Client {
    /**
     * Main initializes command system and executes some commands
     *
     * @param args command-line arguments are not used
     */
    public static void main(String[] args) {
        // history stack for successfully executed commands
        Stack<Command> history = new Stack<>();
        // Create receiver for business logic
        Receiver receiver = new Receiver();
        // Create invoker for command execution
        Invoker invoker = new Invoker();

        // Construct commands
        Command undoCommand = new UndoCommand(receiver, history);
        Command listCommand = new ListCommand(receiver);
        Command deleteCommand = new DeleteCommand(receiver, "1");

        // Test Commands
        Command command1 = new AddCommand(receiver, "Jane Doe email_109");
        Command command2 = new commands.AddCommand(receiver, "Hanna Moon tetter.tots@potatoesarelife.com");
        Command command3 = new commands.AddCommand(receiver, "foo bar foo.bar@dbs.com");
        Command command4 = new commands.AddCommand(receiver, "lorem ipsum lorem-ipsum.LI@db.com");
        Command command5 = new commands.AddCommand(receiver, "firstname lastname firstname.lastname@digipen.com");
        Command command6 = new commands.AddCommand(receiver, "firstname lastname firstname.lastname@digipen.com.sg");
        Command updateCommand1 = new UpdateCommand(receiver, "2 JANE DOE email_109");
        Command updateCommand2 = new UpdateCommand(receiver, "1 JENNY");
        Command[] cmdToExecute = {command1, command2, deleteCommand, undoCommand, listCommand};

        // executing commands via invoker
        invoker.setCommandsForExecution(cmdToExecute);
        invoker.executeCommand(history);

        // store updated employee ArrayList into the dataStore.txt at the end of program
//        receiver.storeToFile();
    }
}
