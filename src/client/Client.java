package client;

import commands.*;
import invoker.Invoker;
import receiver.Receiver;

import java.util.Stack;

public class Client {
    public static void main(String[] args) {
        // history
        Stack<Command> history = new Stack<>();

        // Create receiver.Receiver
        Receiver receiver = new Receiver();

        // Create invoker.Invoker
        Invoker invoker = new Invoker();


        // Test 1
        Command command1 = new AddCommand(receiver, "Jane Doe hello@gmail.com");
//        commands.Command command2 = new commands.AddCommand(receiver, "Hanna Moon tetter.tots@potatoesarelife.com");
//        commands.Command command3 = new commands.AddCommand(receiver, "JINGHAN wu jinghan.wu@dbs.com");
//        commands.Command command4 = new commands.AddCommand(receiver, "JINGHAN wu jinghan.wu@dbs.com");
//        commands.Command command5 = new commands.AddCommand(receiver, "JINGHAN wu jinghan.wu@dbs.com");
//        commands.Command command6 = new commands.AddCommand(receiver, "JINGHAN wu jinghan.wu@dbs.com");

        Command undoCommand = new UndoCommand(receiver, history);
        Command listCommand = new ListCommand(receiver);
        Command deleteCommand = new DeleteCommand(receiver,"1");


//        commands.Command updateCommand = new commands.UpdateCommand(receiver,"c JANE DOE email109");
        Command updateCommand = new UpdateCommand(receiver,"1 JENNY");
//        commands.Command deleteCommand2 = new commands.DeleteCommand(receiver,"3");

//        commands.Command[] cmdToExecute = new commands.Command[] {command1, command2, command3,deleteCommand,listCommand,updateCommand,undoCommand,deleteCommand2,listCommand};
//        commands.Command[] cmdToExecute = new commands.Command[] {command1, listCommand, listCommand, listCommand,undoCommand, listCommand, undoCommand};
//        commands.Command[] cmdToExecute = new commands.Command[] {command1};//,updateCommand,listCommand};
        Command[] cmdToExecute = {command1,updateCommand,listCommand};


//        commands.Command[] cmdToExecute = new commands.Command[] {command1, command2, command3, updateCommand,listCommand, undoCommand, listCommand };
//        commands.Command[] cmdToExecute = new commands.Command[] {command1, command2, command3, listCommand, deleteCommand, listCommand, undoCommand, listCommand};

        // Test 3
//        commands.Command updateCommand = new commands.UpdateCommand(receiver,"4 JANE DOE tots.tetter@gmail.com");
//        commands.Command[] cmdToExecute = new commands.Command[]{updateCommand};


        // executing commands via invoker
        invoker.setCommandsForExecution(cmdToExecute);
        invoker.executeCommand(history);


        // store updated employee ArrayList into the dataStore.txt at the end of program
//        receiver.storeToFile();

    }
}
