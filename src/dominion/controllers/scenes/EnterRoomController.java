package dominion.controllers.scenes;

import dominion.connections.Client;
import javafx.fxml.FXML;

public class EnterRoomController extends ConnectToRoomController {

    @FXML
    public void initialize() {
        nameField.setText("client");
        ipField.setText("localhost");
        portField.setText("1234");
        connection = new Client();
        connection.setIp(ipField.getText());
        connection.setPort(portField.getText());
    }
}
