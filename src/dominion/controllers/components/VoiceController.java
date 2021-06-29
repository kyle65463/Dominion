package dominion.controllers.components;

import dominion.game.GameManager;
import dominion.models.events.game.VoicesEvent;
import dominion.models.game.cards.Card;
import dominion.utils.Voice;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.layout.Pane;

public class VoiceController extends ComponentController {
    public VoiceController() {
        initialize();
    }

    private Button tauntButton;
    private Button shoutButton;
    private void initialize() {
        try {
            rootNode = (Pane) FXMLLoader.load(Card.class.getClassLoader().getResource("resources/components/voice_button.fxml"));
            tauntButton = (Button) rootNode.lookup("#tauntButton");
            shoutButton = (Button) rootNode.lookup("#shoutButton");
            setLayout(10, 440);
            Voice.initalize();
            tauntButton.setOnMousePressed((e) -> {
                GameManager.sendEvent(new VoicesEvent(0));
            });
            shoutButton.setOnMousePressed((e)->{
                GameManager.sendEvent(new VoicesEvent(1));
            });
        } catch (Exception e) {

        }
    }

    public void setLayout(double x, double y) {
        rootNode.setTranslateX(x);
        rootNode.setTranslateY(y);
    }
}
