package dominion.controllers.scenes;

import dominion.utils.Navigator;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Button;

public class MainController {
    // FXML Components
    @FXML
    private Button createRoomButton;
    @FXML
    private Button enterRoomButton;

    // Variables
    private String name;
    private String port;

    // Functions
    public void setName(String name) {
        this.name = name;
    }

    public void navigateToCreateRoomScene(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getClassLoader().getResource("resources/scenes/create_room.fxml"));
            Parent root = loader.load();
            CreateRoomController createRoomController = loader.getController();
            createRoomController.setName(name);
            createRoomController.setPort(port);
            Navigator.to(event, root);
        }
        catch (Exception e) {
            System.out.println(e);
        }
    }

    public void navigateToEnterRoomScene(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getClassLoader().getResource("resources/scenes/enter_room.fxml"));
            Parent root = loader.load();
            EnterRoomController enterRoomController = loader.getController();
            enterRoomController.setName(name);
            enterRoomController.setPort(port);
            Navigator.to(event, root);
        }
        catch (Exception e) {
            System.out.println(e);
        }
    }
}
