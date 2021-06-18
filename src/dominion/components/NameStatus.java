package dominion.components;

import javafx.fxml.FXMLLoader;

import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;

public class NameStatus {
    public NameStatus() {
        initialize();
    }


    private VBox vbox;
    private  Label nameLabel;
    private Label scoreLabel;
    private Label deckLabel;
    private Label discardPileLabel;
    private Label handCardsLabel;

    public Node getNode() {
        return vbox;
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

    private void initialize() {
        try {
            vbox = (VBox) FXMLLoader.load(NameStatus.class.getClassLoader().getResource("resources/component/name_status.fxml"));
            nameLabel = (Label) vbox.lookup("#name");
            deckLabel = (Label) vbox.lookup("#deck");
            discardPileLabel = (Label) vbox.lookup("#discard_pile");
            handCardsLabel = (Label) vbox.lookup("#hand_cards");
            scoreLabel = (Label) vbox.lookup("#score");

            setNameLabel("kyle15989");
            setDeckLabel(10);
            setDiscardPileLabel(5);
            setHandCardsLabel(5);
            setScoreLabel(3);
        } catch (Exception e) {

        }
    }
}
