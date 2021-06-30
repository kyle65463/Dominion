package dominion.connections;

import dominion.controllers.components.LeaveController;
import dominion.controllers.components.ReturnRoomController;
import dominion.models.areas.GameScene;
import dominion.models.events.Event;
import javafx.animation.PauseTransition;
import javafx.application.Platform;
import javafx.util.Duration;

import java.io.ObjectInputStream;
import java.net.Socket;

public class ActionReceiver{
    // Constructors
    public ActionReceiver() { }
    public ActionReceiver(Socket client, MyEventHandler eventHandler) {
        this.client = client;
        this.eventHandler = eventHandler;
    }

    // Variables
    private Socket client;
    private MyEventHandler eventHandler;
    private Receive receive = new Receive();

    // Functions
    public void start() {
        receive = new Receive(client, eventHandler);
        Thread thread = new Thread(receive);
        thread.start();
    }

    public void setActionCallback(MyEventHandler setOutput) {
        this.eventHandler = setOutput;
        receive.setOutputCallback(setOutput);
    }
}

class Receive implements Runnable {
    Receive() {}
    Receive(Socket client, MyEventHandler eventHandler) {
        this.client = client;
        this.eventHandler = eventHandler;
    }
    private Socket client;
    private MyEventHandler eventHandler;

    public void setOutputCallback(MyEventHandler eventHandler) {
        this.eventHandler = eventHandler;
    }

    @Override
    public void run() {
        try {
            ObjectInputStream inputStream = new ObjectInputStream(client.getInputStream());
            boolean flag = true;
            while (flag) {
                Object object = inputStream.readObject();
                Event event = (Event) object;
                eventHandler.handle(event);
            }
        }
        catch (Exception e) {
            System.out.println("Receiver Error");
            System.out.println(e);
            Runnable updater = new Runnable() {
                @Override
                public void run() {
                    GameScene.disable();
                    GameScene.add(new LeaveController());
                    PauseTransition exit = new PauseTransition(Duration.seconds(3));
                    exit.setOnFinished(ee->Platform.exit());
                    exit.play();
                }
            };
            Platform.runLater(updater);
        }
    }
}
