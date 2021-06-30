package dominion.models.player;

import dominion.controllers.components.PlayerStatusController;
import javafx.fxml.FXMLLoader;

import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;

import java.io.Serializable;

public class PlayerStatus{
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

    public void setName(String name) {
        uiController.setNameLabel(name);
    }

    public void setScore(int score) {
        uiController.setScoreLabel(score);
    }

    public void setNumDeckCards(int numDeckCards) {
        uiController.setDeckLabel(numDeckCards);
    }

    public void setNumDiscardPileCards(int numDiscardPileCards) {
        uiController.setDiscardPileLabel(numDiscardPileCards);
    }

    public void setNumHandCards(int numHandCards) {
        uiController.setHandCardsLabel(numHandCards);
    }
}
