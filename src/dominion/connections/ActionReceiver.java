package dominion.connections;

import dominion.models.action.Action;

import java.io.ObjectInputStream;
import java.net.Socket;

public class ActionReceiver{
    // Constructors
    public ActionReceiver() { }
    public ActionReceiver(Socket client, ActionCallback setOutput) {
        this.client = client;
        this.setOutput = setOutput;
    }

    // Variables
    private Socket client;
    private ActionCallback setOutput;
    private Receive receive = new Receive();

    // Functions
    public void start() {
        receive = new Receive(client, setOutput);
        Thread thread = new Thread(receive);
        thread.start();
    }

    public void setActionCallback(ActionCallback setOutput) {
        this.setOutput = setOutput;
        receive.setOutputCallback(setOutput);
    }
}

class Receive implements Runnable {
    Receive() {}
    Receive(Socket client, ActionCallback setOutput) {
        this.client = client;
        this.setOutput = setOutput;
    }
    private Socket client;
    private ActionCallback setOutput;

    public void setOutputCallback(ActionCallback setOutput) {
        this.setOutput = setOutput;
    }

    @Override
    public void run() {
        try {
            ObjectInputStream inputStream = new ObjectInputStream(client.getInputStream());
            boolean flag = true;
            while (flag) {
                Action action = (Action) inputStream.readObject();
                setOutput.send(action);
            }
        }
        catch (Exception e) {
            System.out.println("Receiver Error");
            System.out.println(e);
        }
    }
}
