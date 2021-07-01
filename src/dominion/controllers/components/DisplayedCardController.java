package dominion.controllers.components;

import dominion.models.areas.DisplayedCard;
import dominion.models.cards.Card;
import dominion.models.cards.CardStyles;
import dominion.utils.UiLoader;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Label;
import javafx.scene.layout.StackPane;

public class DisplayedCardController extends ComponentController {
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
    private StackPane numRemainedBox;
    private boolean isDisable = false;

    // Functions
    public void setNumRemain(int numRemain) {
        numRemainedBox.setVisible(numRemain >= 0);
        numRemainLabel.setText(String.valueOf(numRemain));
    }

    public void setScale(double scale) {
        rootNode.setScaleX(scale);
        rootNode.setScaleY(scale);
        rootNode.setTranslateX(0 - (1 - scale) * 120 / 2);
    }

    public void setOnPressed(EventHandler eventHandler) {
        rootNode.setOnMouseClicked(eventHandler);
    }

    private void setStyle() {
        rootNode.setStyle(card.getStyle());
    }

    public void setHighlight() {
        rootNode.setStyle(card.getStyle() + CardStyles.highlight);
    }

    public void removeHighlight() {
        setStyle();
    }

    public void setDisable(boolean b) {
        isDisable = b;
        if (isDisable) {
            rootNode.setStyle("-fx-opacity: 0.4;" + card.getStyle());
        } else {
            setStyle();
        }
    }

    public boolean getDisable() {
        return isDisable;
    }

    private void setName(String name) {
        nameLabel.setText(name);
    }

    private void setNumCost(int numCost) {
        numCostLabel.setText(String.valueOf(numCost));
    }

    private void initialize() {
        rootNode = UiLoader.loadFXML("resources/components/displayed_card.fxml");
        assert rootNode != null;
        nameLabel = (Label) rootNode.lookup("#name");
        numRemainLabel = (Label) rootNode.lookup("#remain");
        numCostLabel = (Label) rootNode.lookup("#cost");
        numRemainedBox = (StackPane) rootNode.lookup("#num_remained_box");

        setName(card.getName());
        setNumCost(card.getNumCost());
        setNumRemain(0);
        setStyle();
    }
}
