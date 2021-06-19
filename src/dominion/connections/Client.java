package dominion.connections;

import dominion.models.User;
import dominion.models.events.EventAction;
import dominion.models.events.connections.ConnectionRequest;

import java.net.Socket;

public class Client extends Connection {
    private ActionSender sender = new ActionSender();
    private ActionReceiver receiver = new ActionReceiver();

    public void run() {
        try {
            Socket client = new Socket(ip, 12478);
            sender = new ActionSender(client);
            receiver = new ActionReceiver(client, actionCallback);
            receiver.start();
            sender.send(new ConnectionRequest(new User(0, name)));
        } catch (Exception e) {
            System.out.println("Client error");
            System.out.println(e);
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
