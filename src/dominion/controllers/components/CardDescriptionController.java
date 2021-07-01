package dominion.controllers.components;

import dominion.models.areas.GameScene;
import dominion.models.cards.Card;
import dominion.utils.UiLoader;
import javafx.scene.control.Label;

public class CardDescriptionController extends ComponentController {
    // Constructor
    public CardDescriptionController(Card card, boolean inSettings) {
        this.card = card;
        initialize(inSettings);
    }

    // Variables
    public static final double width = 240;
    public static final double height = 380;
    private final Card card;
    private Label nameLabel;
    private Label numCostLabel;
    private Label typesLabel;
    private Label descriptionLabel;

    // Functions
    private void initialize(boolean inSettings) {
        rootNode = UiLoader.loadFXML("resources/components/card_description.fxml");
        assert rootNode != null;

        nameLabel = (Label) rootNode.lookup("#name");
        typesLabel = (Label) rootNode.lookup("#types");
        numCostLabel = (Label) rootNode.lookup("#cost");
        descriptionLabel = (Label) rootNode.lookup("#description");
        setNameLabel(card.getName());
        setTypesLabel(card.getType());
        setNumCostLabel(card.getNumCost());
        setDescriptionLabel(card.getDescription());
        setStyle();
        if (inSettings) {
            setLayout(180, 20);
            rootNode.setScaleX(0.7);
            rootNode.setScaleY(0.7);
        } else {
            setLayout(400, 250);
        }
    }

    public void deleteOnScene() {
        GameScene.delete(this);
    }

    public void setNameLabel(String name) {
        nameLabel.setText(name);
    }

    public void setNumCostLabel(int numCost) {
        numCostLabel.setText(String.valueOf(numCost));
    }

    public void setTypesLabel(String types) {
        typesLabel.setText(types);
    }

    public void setDescriptionLabel(String description) {
        descriptionLabel.setText(description);
    }

    private void setStyle() {
        rootNode.setStyle(card.getStyle());
    }

    public void setLayout(double x, double y) {
        rootNode.setTranslateX(x);
        rootNode.setTranslateY(y);
    }
}