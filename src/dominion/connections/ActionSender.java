package dominion.connections;

import dominion.models.events.Event;

import java.io.ObjectOutputStream;
import java.net.Socket;

public class ActionSender {
    // Initialization
    public ActionSender() {}
    public ActionSender(Socket client) {
        try {
            outputStream = new ObjectOutputStream(client.getOutputStream());
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    // Variables
    private ObjectOutputStream outputStream;

    // Functions
    public void send(Event event) {
        try {
            outputStream.writeObject(event);
        } catch (Exception e) {
            System.out.println("Sender Error");
            System.out.println(e);
        }
    }
}
