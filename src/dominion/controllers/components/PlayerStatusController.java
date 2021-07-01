package dominion.controllers.components;

import dominion.models.player.PlayerStatus;
import dominion.utils.UiLoader;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Label;

public class PlayerStatusController extends ComponentController {
    // Constructor
    public PlayerStatusController() {
        initialize();
    }

    // Variables
    private Label nameLabel;
    private Label scoreLabel;
    private Label deckLabel;
    private Label discardPileLabel;
    private Label handCardsLabel;

    // Functions
    private void initialize() {
        rootNode = UiLoader.loadFXML("resources/components/player_status.fxml");
        assert rootNode != null;

        nameLabel = (Label) rootNode.lookup("#name");
        deckLabel = (Label) rootNode.lookup("#deck");
        discardPileLabel = (Label) rootNode.lookup("#discard_pile");
        handCardsLabel = (Label) rootNode.lookup("#hand_cards");
        scoreLabel = (Label) rootNode.lookup("#score");
    }

    public void setDeckLabel(int numDeck) {
        deckLabel.setText("牌庫: " + String.valueOf(numDeck));
    }

    public void setDiscardPileLabel(int numDiscardPiles) {
        discardPileLabel.setText("棄牌堆: " + String.valueOf(numDiscardPiles));
    }

    public void setHandCardsLabel(int numHandCards) {
        handCardsLabel.setText("手牌: " + String.valueOf(numHandCards));
    }

    public void setScoreLabel(int score) {
        scoreLabel.setText(String.valueOf(score) + "分");
    }

    public void setNameLabel(String name) {
        nameLabel.setText(name);
    }
}
