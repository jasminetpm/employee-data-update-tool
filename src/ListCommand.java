public class ListCommand implements Command {
    //params
    private String params;

    //receiver
    private Receiver receiver;

    public ListCommand(Receiver receiver, String params) {
        this.receiver = receiver;
        this.params = params;
    }

    @Override
    public void execute() {

    }
}
