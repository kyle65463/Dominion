package dominion.controllers.components;

import dominion.game.GameManager;
import dominion.models.events.game.TauntEvent;
import dominion.models.game.cards.Card;
import dominion.utils.TauntVoice;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.layout.Pane;

public class TauntController extends ComponentController {
    public TauntController() {
        initialize();
    }

    private Button tauntButton;

    private void initialize() {
        try {
            rootNode = (Pane) FXMLLoader.load(Card.class.getClassLoader().getResource("resources/components/taunt_button.fxml"));
            tauntButton = (Button) rootNode.lookup("#tauntButton");
            setLayout(10, 440);
            TauntVoice.initialize();
            tauntButton.setOnMousePressed((e) -> {
                GameManager.sendVoiceEvent(new TauntEvent(0));
            });
        } catch (Exception e) {

        }
    }

    public void setLayout(double x, double y) {
        rootNode.setTranslateX(x);
        rootNode.setTranslateY(y);
    }
}
