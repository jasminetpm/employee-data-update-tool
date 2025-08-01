public class AddCommand implements Command {
    //params
    private String params;

    //receiver
    private Receiver receiver;

    public AddCommand(Receiver receiver, String params) {
        this.receiver = receiver;
        this.params = params;
    }

    @Override
    public void execute() {

    }
}
