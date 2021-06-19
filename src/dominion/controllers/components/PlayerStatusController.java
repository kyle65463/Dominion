package dominion.controllers.components;

import dominion.models.game.PlayerStatus;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Label;

public class PlayerStatusController extends ComponentController{
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

    private void initialize() {
        try {
            rootNode = FXMLLoader.load(PlayerStatus.class.getClassLoader().getResource("resources/components/player_status.fxml"));
            nameLabel = (Label) rootNode.lookup("#name");
            deckLabel = (Label) rootNode.lookup("#deck");
            discardPileLabel = (Label) rootNode.lookup("#discard_pile");
            handCardsLabel = (Label) rootNode.lookup("#hand_cards");
            scoreLabel = (Label) rootNode.lookup("#score");

            setNameLabel("kyle15989");
            setDeckLabel(10);
            setDiscardPileLabel(5);
            setHandCardsLabel(5);
            setScoreLabel(3);
        } catch (Exception e) {

        }
    }
}
