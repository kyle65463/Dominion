package dominion.connections;

import dominion.models.User;
import dominion.models.events.EventAction;
import dominion.models.events.ConnectionRequest;
import dominion.models.events.NetworkEventAction;

import java.net.Socket;

public class Client extends Connection {
    private ActionSender sender = new ActionSender();
    private ActionReceiver receiver = new ActionReceiver();

    public void run() {
        try {
            actionCallback.send(new NetworkEventAction("connecting"));
            Socket client = new Socket(ip, 12478);
            if (client.isConnected()) {
                actionCallback.send(new NetworkEventAction("connected"));
            }

            sender = new ActionSender(client);
            receiver = new ActionReceiver(client, actionCallback);
            receiver.start();
            sender.send(new ConnectionRequest(new User(0, name)));
        } catch (Exception e) {
            System.out.println("Client error");
            System.out.println(e);
            actionCallback.send(new NetworkEventAction("connection error"));
        }
    }

    public void setActionCallback(ActionCallback callback) {
        this.actionCallback = callback;
        receiver.setActionCallback(callback);
    }

    public void send(EventAction eventAction) {
        sender.send(eventAction);
    }
}
