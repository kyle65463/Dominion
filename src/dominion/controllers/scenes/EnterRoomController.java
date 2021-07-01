package dominion.controllers.scenes;

import dominion.connections.Client;
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
        portField.setText(defaultPort);
    }
}
