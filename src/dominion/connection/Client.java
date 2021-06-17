package dominion.connection;

import dominion.model.User;
import dominion.model.action.Action;
import dominion.model.action.ConnectionRequest;
import dominion.model.action.NetworkAction;

import java.net.Socket;

public class Client extends Connection {
    private ActionSender sender = new ActionSender();
    private ActionReceiver receiver = new ActionReceiver();

    public void run() {
        try {
            actionCallback.send(new NetworkAction("connecting"));
            Socket client = new Socket(ip, 12478);
            if (client.isConnected()) {
                actionCallback.send(new NetworkAction("connected"));
            }

            sender = new ActionSender(client);
            receiver = new ActionReceiver(client, actionCallback);
            receiver.start();
            sender.send(new ConnectionRequest(new User(0, name)));
        } catch (Exception e) {
            System.out.println("Client error");
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
