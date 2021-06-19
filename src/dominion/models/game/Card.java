package dominion.models.game;

import dominion.controllers.components.CardController;
import javafx.fxml.FXMLLoader;

import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;

public class Card {
    // Constructor
    public Card() {
        this.uiController = new CardController();
    }

    // Variables
    private CardController uiController;

    // Function
    public CardController getController() {
        return uiController;
    }
}
