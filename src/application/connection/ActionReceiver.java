package application.connection;

import application.action.Action;

import java.io.ObjectInputStream;
import java.net.Socket;

public class ActionReceiver{
    // Initialization
    public ActionReceiver(Socket client, Callback setOutput) {
        this.client = client;
        this.setOutput = setOutput;
    }

    // Variables
    private Socket client;
    private Callback setOutput;
    private  Receive receive;

    // Functions
    public void start() {
        receive = new Receive(client, setOutput);
        Thread thread = new Thread(receive);
        thread.start();
    }

    public void setOutputCallback(Callback setOutput) {
        this.setOutput = setOutput;
        receive.setOutputCallback(setOutput);
    }
}

class Receive implements Runnable {
    Receive(Socket client, Callback setOutput) {
        this.client = client;
        this.setOutput = setOutput;
    }
    private Socket client;
    private Callback setOutput;

    public void setOutputCallback(Callback setOutput) {
        this.setOutput = setOutput;
    }

    @Override
    public void run() {
        try {
            ObjectInputStream inputStream = new ObjectInputStream(client.getInputStream());
            boolean flag = true;
            while (flag) {
                Action action = (Action) inputStream.readObject();
                setOutput.set(action);
            }
        }
        catch (Exception e) {
            System.out.println("Receiver Error");
            System.out.println(e);
        }
    }
}
