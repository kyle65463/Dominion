package dominion.controllers.scenes;

import dominion.params.ConnectToRoomSceneParams;
import dominion.params.MainSceneParams;
import dominion.params.SceneParams;
import dominion.utils.Navigator;
import javafx.event.ActionEvent;
import javafx.stage.Stage;

public class MainController extends SceneController{
    // Variables
    private Stage stage;
    private String defaultName;
    private String defaultIp;
    private String defaultPort;

    // Functions
    public void initialize(Stage stage, SceneParams sceneParams) {
        // Unpack parameters
        MainSceneParams params = (MainSceneParams) sceneParams;
        this.stage = stage;
        this.defaultName = params.defaultName;
        this.defaultPort = params.defaultPort;
        this.defaultIp = params.defaultIp;
    }

    public void navigateToCreateRoomScene(ActionEvent e) {
        ConnectToRoomSceneParams params = new ConnectToRoomSceneParams(defaultName, defaultIp, defaultPort);
        Navigator.to(stage, "resources/scenes/create_room.fxml", params);
    }

    public void navigateToEnterRoomScene(ActionEvent event) {
        ConnectToRoomSceneParams params = new ConnectToRoomSceneParams(defaultName, defaultIp, defaultPort);
        Navigator.to(stage, "resources/scenes/enter_room.fxml", params);
    }
}
