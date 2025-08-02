public class DeleteCommand implements Command {
    //params
    private String params;

    //receiver
    private Receiver receiver;

    public DeleteCommand(Receiver receiver, String params) {
        this.receiver = receiver;
        this.params = params;
    }

    @Override
    public void execute() {

    }

    @Override
    public void undo() {

    }
}
