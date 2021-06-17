package application.connection;

import application.action.Action;
import application.action.NetworkAction;

import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

public class Server extends Connection {
    private int numConnection = 0;
    private int maxConnection = 3;
    private List<ActionSender> senders = new ArrayList<>();
    private List<ActionReceiver> receivers = new ArrayList<>();

    public void run() {
        try {
            ServerSocket server = new ServerSocket(9091);
            while (true) {
                numConnection = senders.size();
                if (numConnection >= maxConnection) {
                    server.close();
                }
                else if(server.isClosed()) {
                    server = new ServerSocket(9091);
                }

                actionCallback.send(new NetworkAction("connecting"));
                Socket client = server.accept();
                actionCallback.send(new NetworkAction("connected"));

                ActionSender sender = new ActionSender(client);
                ActionReceiver receiver = new ActionReceiver(client, (action) -> {
                    actionCallback.send(action);
                    sendClient(action);
                });
                receiver.start();

                senders.add(sender);
                receivers.add(receiver);

            }
        } catch (Exception e) {
            System.out.println(e);
            actionCallback.send(new NetworkAction("connection error"));
        }

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

    private void sendClient(Action action) {
        for (ActionSender sender : senders) {
            sender.send(action);
        }
    }

    public void send(Action action) {
        sendClient(action);
        actionCallback.send(action);
    }
}
