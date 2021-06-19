package dominion.controllers.components;

import dominion.models.game.DisplayedCard;
import dominion.models.game.cards.Card;
import dominion.models.game.cards.curses.Curses;
import dominion.models.game.cards.treasures.Treasure;
import dominion.models.game.cards.victories.Victory;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Label;
import javafx.scene.layout.Pane;

public class DisplayedCardController extends ComponentController{
    // Constructor
    public DisplayedCardController(Card card) {
        this.card = card;
        initialize();
    }

    // Variables
    private Card card;
    private Label nameLabel;
    private Label numRemainLabel;
    private Label numCostLabel;

    // Functions
    public void setNumRemain(int numRemain) {
        numRemainLabel.setText(String.valueOf(numRemain));
    }

    public void setScale(double scale) {
        rootNode.setScaleX(scale);
        rootNode.setScaleY(scale);
        rootNode.setTranslateX(0 - (1 - scale) * 120 / 2);
    }

    private void setStyle() {
        rootNode.setStyle(card.getStyle());
    }

    private void setName(String name) {
        nameLabel.setText(name);
    }

    private void setNumCost(int numCost) {
        numCostLabel.setText(String.valueOf(numCost));
    }

    private void initialize() {
        try {
            rootNode = FXMLLoader.load(DisplayedCard.class.getClassLoader().getResource("resources/components/displayed_card.fxml"));
            nameLabel = (Label) rootNode.lookup("#name");
            numRemainLabel = (Label) rootNode.lookup("#remain");
            numCostLabel = (Label) rootNode.lookup("#cost");
            setName(card.getName());
            setNumCost(card.getNumCost());
            setNumRemain(0);
            setStyle();
        } catch (Exception e) {

        }
    }
}
