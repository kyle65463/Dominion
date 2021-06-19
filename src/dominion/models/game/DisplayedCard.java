package dominion.models.game;

import dominion.controllers.components.DisplayedCardController;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.layout.Pane;

public class DisplayedCard {
    // Constructor
    public DisplayedCard() {
        this.uiController = new DisplayedCardController();
    }

    // Variables
    private DisplayedCardController uiController;

    // Functions
    public DisplayedCardController getController() {
        return uiController;
    }
}
