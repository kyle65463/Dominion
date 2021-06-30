package dominion.connections;

import dominion.models.User;
import dominion.models.events.Event;
import dominion.models.events.connections.ConnectionRequest;

import java.net.Socket;

public class Client extends Connection {
    private ActionSender sender = new ActionSender();
    private ActionReceiver receiver = new ActionReceiver();

    public void run() {
        try {
            Socket client = new Socket(ip, Integer.valueOf(port));
            sender = new ActionSender(client);
            receiver = new ActionReceiver(client, myEventHandler);
            receiver.start();
            sender.send(new ConnectionRequest(new User(0, name)));
        } catch (Exception e) {
            System.out.println("Client error");
            System.out.println(e);
        }
    }

    public void setEventHandler(MyEventHandler callback) {
        this.myEventHandler = callback;
        receiver.setActionCallback(callback);
    }

    public void send(Event event) {
        sender.send(event);
    }
}
