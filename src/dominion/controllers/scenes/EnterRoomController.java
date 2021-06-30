package dominion.controllers.scenes;

import dominion.connections.Client;
import dominion.connections.Server;
import dominion.params.ConnectToRoomSceneParams;
import dominion.params.SceneParams;
import javafx.stage.Stage;

public class EnterRoomController extends ConnectToRoomController {
    // Functions
    @Override
    public void initialize(Stage stage, SceneParams sceneParams) {
        super.initialize(stage, sceneParams);
        connection = new Client();
        nameField.setText(defaultName);
        ipField.setText(defaultIp);
        portField.setText("1234");
    }
}
