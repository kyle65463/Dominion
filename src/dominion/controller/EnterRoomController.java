package dominion.controller;

import dominion.connection.Client;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;

public class EnterRoomController extends ConnectToRoomController {

    @FXML
    public void initialize() {
        nameField.setText("client");
        ipField.setText("localhost");
        connection = new Client();
        connection.setIp(ipField.getText());
    }
}
