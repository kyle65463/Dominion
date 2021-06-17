package dominion.controller;

import dominion.connection.Server;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;

public class CreateRoomController extends ConnectToRoomController {
    @FXML
    TextField ipField;
    private String name = "client123";

    @FXML
    public void initialize() {
        connection = new Server();
        nameField.setText("host");
        ipField.setText("localhost");
    }
}
