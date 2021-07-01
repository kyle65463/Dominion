package dominion.controllers.scenes;

import dominion.connections.Host;
import dominion.params.SceneParams;
import javafx.stage.Stage;

public class CreateRoomController extends ConnectToRoomController {
    // Functions
    @Override
    public void initialize(Stage stage, SceneParams sceneParams) {
        super.initialize(stage, sceneParams);
        connection = new Host();
        nameField.setText(defaultName);
        portField.setText(defaultPort);
    }
}
