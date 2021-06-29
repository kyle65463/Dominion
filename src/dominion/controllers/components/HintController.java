package dominion.controllers.components;

import dominion.models.game.GameScene;
import dominion.models.game.cards.Card;
import dominion.utils.CardStyles;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Label;
import javafx.scene.layout.Pane;

public class HintController extends ComponentController{
    public HintController() {
        initialize();
    }
    public void deleteOnScene() {
        GameScene.delete(this);
    }
    public void setLayout(double x, double y) {
        rootNode.setTranslateX(x);
        rootNode.setTranslateY(y);
    }
    private void initialize() {
        try {
            rootNode = (Pane) FXMLLoader.load(Card.class.getClassLoader().getResource("resources/components/hint.fxml"));
            rootNode.setStyle(CardStyles.white);
            setLayout(750,480);
        } catch (Exception e) {
        }
    }
}
