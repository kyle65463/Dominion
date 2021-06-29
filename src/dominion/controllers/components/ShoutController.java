package dominion.controllers.components;

import dominion.game.GameManager;
import dominion.models.events.game.ShoutEvent;
import dominion.models.events.game.TauntEvent;
import dominion.models.game.cards.Card;
import dominion.utils.ShoutVoice;
import dominion.utils.TauntVoice;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.layout.Pane;

public class ShoutController extends ComponentController{
    public ShoutController(){initialize();}
    private Button shoutButton;

    private void initialize() {
        try {
            rootNode = (Pane) FXMLLoader.load(Card.class.getClassLoader().getResource("resources/components/shout_button.fxml"));
            shoutButton = (Button) rootNode.lookup("#shoutButton");
            setLayout(10,500);
            ShoutVoice.initialize();
            shoutButton.setOnMousePressed((e)->{
                GameManager.sendVoiceEvent(new ShoutEvent(0));
                System.out.println("press");});
        } catch (Exception e) {

        }
    }
    public void setLayout(double x, double y) {
        rootNode.setTranslateX(x);
        rootNode.setTranslateY(y);
    }
}
