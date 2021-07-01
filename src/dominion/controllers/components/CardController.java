package dominion.controllers.components;

import dominion.models.cards.Card;
import dominion.models.cards.treasures.Treasure;
import dominion.models.areas.GameScene;
import dominion.models.cards.CardStyles;
import dominion.utils.UiLoader;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Label;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;


public class CardController extends ComponentController {
    // Constructor
    public CardController(Card card) {
        this.card = card;
        initialize();
    }

    // Variables
    public static final double width = 120;
    public static final double height = 190;
    private final Card card;
    private StackPane numRemainBox;
    private StackPane numSelectedBox;
    private StackPane valueBox;
    private StackPane costBox;
    private Label nameLabel;
    private Label numCostLabel;
    private Label numRemainLabel;
    private Label numSelectedLabel;
    private Label typesLabel;
    private Label numValueLabel;

    // Functions
    private void initialize() {
        rootNode = UiLoader.loadFXML("resources/components/card.fxml");
        assert rootNode != null;

        nameLabel = (Label) rootNode.lookup("#name");
        typesLabel = (Label) rootNode.lookup("#types");
        numCostLabel = (Label) rootNode.lookup("#cost");
        numRemainLabel = (Label) rootNode.lookup("#remain");
        numSelectedLabel = (Label) rootNode.lookup("#num_selected");
        numValueLabel = (Label) rootNode.lookup("#value");
        numRemainBox = (StackPane) rootNode.lookup("#remain_box");
        numSelectedBox = (StackPane) rootNode.lookup("#num_selected_box");
        valueBox = (StackPane) rootNode.lookup("#value_box");
        costBox = (StackPane) rootNode.lookup("#cost_box");

        setNameLabel(card.getName());
        setTypesLabel(card.getType());
        setNumRemainLabel(0);
        setNumCostLabel(card.getNumCost());
        setNumSelected(0);
        if (card instanceof Treasure) {
            setNumValueLabel(((Treasure) card).getNumValue());
        } else {
            setNumValueLabel(0);
        }
        setStyle();
    }

    public void deleteOnScene() {
        GameScene.delete(this);
    }

    private void setStyle() {
        rootNode.setStyle(card.getStyle());
    }

    public void setTypesLabel(String type) {
        typesLabel.setText(type);
    }

    public void setScale(double scale) {
        rootNode.setScaleX(scale);
        rootNode.setScaleY(scale);
    }

    public void setLayout(double x, double y) {
        rootNode.setTranslateX(x);
        rootNode.setTranslateY(y);
    }

    public void setHighlight() {
        rootNode.setStyle(card.getStyle() + CardStyles.highlight);
    }

    public void setNumSelected(int numSelected) {
        numSelectedBox.setVisible(numSelected > 0);
        numSelectedLabel.setText(String.valueOf(numSelected));
    }

    public int getNumSelected() {
        return Integer.parseInt(numSelectedLabel.getText());
    }

    public void removeHighlight() {
        setStyle();
        setNumSelected(0);
    }

    public void setOnPressed(EventHandler eventHandler) {
        rootNode.setOnMouseClicked(eventHandler);
    }

    public void setNumCostLabel(int score) {
        numCostLabel.setText(String.valueOf(score));
    }

    public void setNumValueLabel(int value) {
        enableValueBox(value > 0);
        numValueLabel.setText(String.valueOf(value));
    }

    public void setNameLabel(String name) {
        nameLabel.setText(name);
    }

    public void setNumRemainLabel(int numRemain) {
        enableRemainBox(numRemain > 1);
        numRemainLabel.setText(String.valueOf(numRemain));
    }

    public void flipToBack() {
        rootNode.setStyle(CardStyles.brown);
        nameLabel.setVisible(false);
        valueBox.setVisible(false);
        costBox.setVisible(false);
        typesLabel.setVisible(false);
    }

    public void flipToFront() {
        setStyle();
        nameLabel.setVisible(true);
        valueBox.setVisible(true);
        costBox.setVisible(true);
        typesLabel.setVisible(true);
    }

    public void enableRemainBox(Boolean enable) {
        numRemainBox.setDisable(!enable);
        numRemainBox.setVisible(enable);
    }

    public void enableValueBox(Boolean enable) {
        valueBox.setDisable(!enable);
        valueBox.setVisible(enable);
    }
}