package application.connection;

import application.action.Action;

import java.io.ObjectOutputStream;
import java.net.Socket;

public class Client extends Connection {
    private ActionSender sender;
    private ActionReceiver receiver;

    public void run() {
        try {
            setStatus.set(new Action("", "Connecting"));
            Socket client = new Socket("localhost", 9091);
            setStatus.set(new Action("", "Connected"));

            sender = new ActionSender(client);
            receiver = new ActionReceiver(client, setOutput);
            receiver.start();
        } catch (Exception e) {
            System.out.println(e);
            setStatus.set(new Action("", "Error"));
        }
    }

    public void setOutputCallback(Callback setOutput) {
        this.setOutput = setOutput;
        receiver.setOutputCallback(setOutput);
    }

    public void send(Action action) {
        sender.send(action);
    }
}
