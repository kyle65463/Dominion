package dominion.connections;

import dominion.controllers.components.LeaveDialogController;
import dominion.models.areas.GameScene;
import dominion.models.events.Event;
import dominion.models.handlers.MyEventHandler;
import javafx.animation.PauseTransition;
import javafx.application.Platform;
import javafx.util.Duration;

import java.io.ObjectInputStream;
import java.net.Socket;

public class EventReceiver {
    // Constructors
    public EventReceiver() {
    }

    public EventReceiver(Socket client, MyEventHandler eventHandler) {
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

    class Receive implements Runnable {
        Receive() {
        }

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
                while (true) {
                    Object object = inputStream.readObject();
                    Event event = (Event) object;
                    eventHandler.handle(event);
                }
            } catch (Exception e) {
                System.err.println("EventReceiver Error");
                System.err.println(e);
                Platform.runLater(() -> {
                    GameScene.disable();
                    GameScene.add(new LeaveDialogController());
                    PauseTransition exit = new PauseTransition(Duration.seconds(2));
                    exit.setOnFinished(ee -> Platform.exit());
                    exit.play();
                });
            }
        }
    }
}
