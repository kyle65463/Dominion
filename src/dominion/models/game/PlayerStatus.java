package dominion.models.game;

import dominion.controllers.components.PlayerStatusController;
import javafx.fxml.FXMLLoader;

import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;

public class PlayerStatus {
    // Constructor
    public PlayerStatus() {
        this.uiController = new PlayerStatusController();
    }

    // Variables
    private PlayerStatusController uiController;

    // Functions
    public PlayerStatusController getController() {
        return uiController;
    }
}
