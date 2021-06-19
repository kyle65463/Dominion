package dominion.connections;

import dominion.models.User;
import dominion.models.events.EventAction;
import dominion.models.events.ConnectionAccepted;
import dominion.models.events.ConnectionRequest;
import dominion.models.events.NetworkEventAction;

import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

public class Server extends Connection {
    private int numConnection = 0;
    private int maxConnection = 3;
    private List<ActionSender> senders = new ArrayList<>();
    private List<ActionReceiver> receivers = new ArrayList<>();
    private List<User> users = new ArrayList<>();

    public void run() {
        try {
            ServerSocket server = new ServerSocket(12478);
            User user = new User(0, name);
            users.add(user);
            actionCallback.send(new ConnectionAccepted(user, users));
            while (true) {
                Socket client = server.accept();
                ActionSender sender = new ActionSender(client);
                ActionReceiver receiver = new ActionReceiver(client, (action) -> {
                    if (action instanceof ConnectionRequest) {
                        processConnectionRequest((ConnectionRequest) action);
                    }
                    else {
                        actionCallback.send(action);
                        sendClient(action);
                    }
                });
                receiver.start();

                senders.add(sender);
                receivers.add(receiver);

            }
        } catch (Exception e) {
            System.out.println("Server error");
            System.out.println(e);
            actionCallback.send(new NetworkEventAction("connection error"));
        }

    }

    private void processConnectionRequest(ConnectionRequest request) {
        User requestedUser = request.getRequestedUser();
        requestedUser.setId(1);
        users.add(requestedUser);
        actionCallback.send(new ConnectionAccepted(requestedUser, users));
        sendClient(new ConnectionAccepted(requestedUser, users));
    }

    public void setActionCallback(ActionCallback callback) {
        this.actionCallback = callback;
        for (ActionReceiver receiver : receivers) {
            receiver.setActionCallback((action) -> {
                callback.send(action);
                sendClient(action);
            });
        }
    }

    private void sendClient(EventAction eventAction) {
        for (ActionSender sender : senders) {
            sender.send(eventAction);
        }
    }

    public void send(EventAction eventAction) {
        sendClient(eventAction);
        actionCallback.send(eventAction);
    }
}
