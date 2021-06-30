package dominion.controllers.components;

import dominion.models.areas.GameScene;
import dominion.models.cards.Card;
import dominion.models.cards.CardStyles;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.layout.Pane;

public class LeaveController extends ComponentController{
    public LeaveController(){initialize();};
    private void initialize() {
        try {
            rootNode = (Pane) FXMLLoader.load(Card.class.getClassLoader().getResource("resources/components/leave_message.fxml"));
            rootNode.setStyle(CardStyles.white);
            setLayout(400,300);
        } catch (Exception e) {
        }
    }
    public void setLayout(double x, double y) {
        rootNode.setTranslateX(x);
        rootNode.setTranslateY(y);
    }
}
