package dominion.controllers.scenes;

import dominion.connections.Server;
import javafx.fxml.FXML;

public class CreateRoomController extends ConnectToRoomController {
    @FXML
    public void initialize() {
        connection = new Server();
        nameField.setText("host");
        portField.setText("1234");
    }
}
