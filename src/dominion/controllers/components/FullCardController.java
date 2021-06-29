package dominion.controllers.components;

import dominion.models.game.GameScene;
import dominion.models.game.cards.Card;
import dominion.models.game.cards.treasures.Treasure;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Label;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;

public class FullCardController extends ComponentController{

    public FullCardController(Card card) {
        this.card = card;
        initialize();
    }

    public static final double width = 240;
    public static final double height = 380;

    private Card card;
    private Label nameLabel;
    private Label numCostLabel;
    private Label typesLabel;
    private Label descriptionLabel;

    public void deleteOnScene() {
        GameScene.delete(this);
    }

//    public void setOnPressed(EventHandler eventHandler) {
//        rootNode.setOnMouseClicked(eventHandler);
//    }

    public void setNameLabel(String name) {
        nameLabel.setText(name);
    }
    public void setNumCostLabel(int numCost) {
        numCostLabel.setText(String.valueOf(numCost));
    }
    public void setTypesLabel(String types) {
        typesLabel.setText(types);
    }
    public void setDescriptionLabel(String description){ descriptionLabel.setText(description);}
    public String returnNameLabel(){return nameLabel.getText();}
    public HintController hintController;

    private void initialize() {
        try {
            rootNode = (Pane) FXMLLoader.load(Card.class.getClassLoader().getResource("resources/components/full_card.fxml"));
            nameLabel = (Label) rootNode.lookup("#name");
            typesLabel = (Label) rootNode.lookup("#types");
            numCostLabel = (Label) rootNode.lookup("#cost");
            descriptionLabel = (Label) rootNode.lookup("#description");
            setNameLabel(card.getName());
            setTypesLabel(card.getType());
            setNumCostLabel(card.getNumCost());
            setDescriptionLabel(card.getDescription());
            setStyle();
            setLayout(400,250);
            hintController = new HintController();
        } catch (Exception e) {

        }
    }
    private void setStyle() {
        rootNode.setStyle(card.getStyle());
    }

    public void setLayout(double x, double y) {
        rootNode.setTranslateX(x);
        rootNode.setTranslateY(y);
    }


}
