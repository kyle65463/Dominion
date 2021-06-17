package dominion.controller;

import dominion.connection.Client;
import javafx.fxml.FXML;

public class EnterRoomController extends ConnectToRoomController {
    @FXML
    public void initialize() {
        connection = new Client();
        nameField.setText("client");
    }
}
