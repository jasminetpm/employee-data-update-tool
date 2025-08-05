package commands;
import exceptions.CommandException;

public interface Command {

    public boolean execute() throws CommandException;

    public void undo();

}
