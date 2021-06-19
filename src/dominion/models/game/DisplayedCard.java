package dominion.models.game;

import dominion.controllers.components.DisplayedCardController;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.layout.Pane;

public class DisplayedCard implements HasUi {
    // Constructor
    public DisplayedCard() {
        // Impossible with no ui
        enableUi();
    }

    // Variables
    private boolean isEnableUi;
    private DisplayedCardController uiController;

    // Functions
    public void enableUi() {
        this.uiController =  new DisplayedCardController();
        isEnableUi = true;
    }

    public DisplayedCardController getController() {
        return uiController;
    }
}
