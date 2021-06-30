package dominion.connections;

import dominion.controllers.components.*;
import dominion.models.User;
import dominion.models.areas.GameScene;
import dominion.models.events.Event;
import javafx.animation.PauseTransition;
import javafx.application.Platform;
import javafx.util.Duration;

import java.io.ObjectInputStream;
import java.net.ServerSocket;
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
                    if(LeaveController.getEvent()){
                        if(CheckLeaveUserController.isServer()){
                            ServerSocket server = ReturnMainController.getServerSocket();
                            try{
                                server.close();
                            }catch(Exception e){}
                            LeaveController.setEvent(false);
                            ReturnMainController.returnMain();
                        }
                    }else{
                        GameScene.disable();
                        GameScene.add(new GameLeaveController());
                        PauseTransition exit = new PauseTransition(Duration.seconds(2));
                        exit.setOnFinished(ee->{
                            ServerSocket server = ReturnMainController.getServerSocket();
                            try{
                                server.close();
                            }catch(Exception e){}
                            LeaveController.setEvent(false);
                            Platform.exit();
//                            ReturnMainController.returnMain();
                        });
                        exit.play();
                    }
                }
            };
            Platform.runLater(updater);
        }
    }
}
