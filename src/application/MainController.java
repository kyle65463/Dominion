package application;

import application.util.Navigator;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;

public class MainController {
    // FXML Components
    @FXML
    private Button createRoomButton;
    @FXML
    private Button enterRoomButton;

    // Functions
    public void navigateToCreateRoomScene(ActionEvent event) {
        Navigator.to(event, "application/create_room.fxml");
    }

    public void navigateToEnterRoomScene(ActionEvent event) {
        Navigator.to(event, "application/enter_room.fxml");
    }
}
