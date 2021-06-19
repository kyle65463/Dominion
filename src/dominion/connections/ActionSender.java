package dominion.connections;

import dominion.models.events.EventAction;

import java.io.ObjectOutputStream;
import java.net.Socket;

//public class ActionSender {
//    // Initialization
//    public static void init(Socket client) {
//        try {
//            ActionSender.outputStream = new ObjectOutputStream(client.getOutputStream());
//        } catch (Exception e) {
//            System.out.println(e);
//        }
//    }
//
//    // Variables
//    private static ObjectOutputStream outputStream;
//
//    // Functions
//    public static void send(Action action) {
//        try {
//            System.out.println("???????");
//            outputStream.writeObject(action);
//        } catch (Exception e) {
//            System.out.println(e);
//        }
//    }
//}

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
    public void send(EventAction eventAction) {
        try {
            outputStream.writeObject(eventAction);
        } catch (Exception e) {
            System.out.println("Sender Error");
            System.out.println(e);
        }
    }
}
