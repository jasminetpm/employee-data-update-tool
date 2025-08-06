package commands;

import exceptions.CommandException;

/**
 * Command interface defines contract for executable operations within command system.
 * execute performs command's primary action
 * undo reverses the effect of the most recently executed command
 */
public interface Command {

    /**
     * Executes the command's primary action,
     * such as adding/deleting/updating/etc.
     *
     * @return true/false based on execution's success
     * @throws CommandException if execution fails due to invalid
     *                          input/errors/business rule violations
     */
    public boolean execute() throws CommandException;


    /**
     * reverses most recently executed command, restoring the system
     * to its prior state
     */
    public void undo();

}
