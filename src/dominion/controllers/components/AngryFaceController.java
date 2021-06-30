package dominion.controllers.components;

import dominion.models.game.GameScene;
import dominion.models.game.cards.Card;
import dominion.utils.CardStyles;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Label;
import javafx.scene.layout.Pane;

import java.awt.event.ActionEvent;

public class AngryFaceController extends ComponentController{
    public AngryFaceController(){initialize();};
    private Label nameLabel;
    private void initialize() {
        try {
            rootNode = (Pane) FXMLLoader.load(Card.class.getClassLoader().getResource("resources/components/angry_face.fxml"));
            nameLabel = (Label) rootNode.lookup("#nameLabel");
            nameLabel.setText("hello");
            rootNode.setStyle(CardStyles.white);
            setLayout(18,370);
        } catch (Exception e) {
        }
    }
    public void setLayout(double x, double y) {
        rootNode.setTranslateX(x);
        rootNode.setTranslateY(y);
    }
    public void setName(String name){nameLabel.setText(name + "森氣惹!");}
}
