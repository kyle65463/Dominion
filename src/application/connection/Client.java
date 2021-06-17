package application.connection;

import application.action.Action;
import application.action.NetworkAction;

import java.net.Socket;

public class Client extends Connection {
    private ActionSender sender = new ActionSender();
    private ActionReceiver receiver = new ActionReceiver();

    public void run() {
        try {
            actionCallback.send(new NetworkAction("connecting"));
            Socket client = new Socket("localhost", 9091);
            if (client.isConnected()) {
                actionCallback.send(new NetworkAction("connected"));
            }

            sender = new ActionSender(client);
            receiver = new ActionReceiver(client, actionCallback);
            receiver.start();
        } catch (Exception e) {
            System.out.println(e);
            actionCallback.send(new NetworkAction("connection error"));
        }
    }

    public void setActionCallback(ActionCallback callback) {
        this.actionCallback = callback;
        receiver.setActionCallback(callback);
    }

    public void send(Action action) {
        sender.send(action);
    }
}
