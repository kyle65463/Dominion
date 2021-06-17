package application.connection;

import application.action.Action;

import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class Server extends Connection {
    private ActionSender sender;
    private ActionReceiver receiver;

    public void run() {
        try {
            ServerSocket server = new ServerSocket(9091);
            setStatus.set(new Action("", "Connecting"));
            Socket client = server.accept();
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
        receiver.setOutputCallback((action) -> {
            setOutput.set(action);
            sendClient(action);
        });
    }

    private void sendClient(Action action) {

        sender.send(action);
    }

    public void send(Action action) {
        sendClient(action);
        setOutput.set(action);
    }
}
