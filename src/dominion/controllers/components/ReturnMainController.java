package dominion.controllers.components;

import dominion.params.MainSceneParams;
import dominion.utils.Navigator;
import javafx.stage.Stage;

import java.net.ServerSocket;

public class ReturnMainController {
    public static void setStage(Stage stage){
        ReturnMainController.stage = stage;
    }
    public static void setServerSocket(ServerSocket server){
        ReturnMainController.server = server;
    }
    public static ServerSocket getServerSocket(){return server;}
    private static Stage stage;
    private static ServerSocket server;
    public static void returnMain(){
        MainSceneParams params = new MainSceneParams("kyle15989", "localhost", "9999");
        Navigator.to(stage, "resources/scenes/main.fxml", params);
    }
}
