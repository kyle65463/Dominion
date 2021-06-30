package dominion.connections;

import dominion.models.User;
import dominion.models.events.Event;
import dominion.models.events.connections.ConnectionAccepted;
import dominion.models.events.connections.ConnectionRequest;

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
            ServerSocket server = new ServerSocket(Integer. valueOf(port));
            System.out.println(port);
            User user = new User(0, name);
            users.add(user);
            myEventHandler.handle(new ConnectionAccepted(user, users));
            while (true) {
                Socket client = server.accept();
                ActionSender sender = new ActionSender(client);
                ActionReceiver receiver = new ActionReceiver(client, (action) -> {
                    if (action instanceof ConnectionRequest) {
                        processConnectionRequest((ConnectionRequest) action);
                    }
                    else {
                        myEventHandler.handle(action);
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
        }

    }

    private void processConnectionRequest(ConnectionRequest request) {
        User requestedUser = request.getRequestedUser();
        int maxId = 0;
        for(User u : users){
            maxId = Math.max(u.getId(), maxId);
        }
        requestedUser.setId(maxId + 1);
        users.add(requestedUser);
        myEventHandler.handle(new ConnectionAccepted(requestedUser, users));
        sendClient(new ConnectionAccepted(requestedUser, users));
    }

    public void setEventHandler(MyEventHandler callback) {
        this.myEventHandler = callback;
        for (ActionReceiver receiver : receivers) {
            receiver.setActionCallback((action) -> {
                callback.handle(action);
                sendClient(action);
            });
        }
    }

    private void sendClient(Event event) {
        for (ActionSender sender : senders) {
            sender.send(event);
        }
    }

    public void send(Event event) {
        sendClient(event);
        myEventHandler.handle(event);
    }
}
