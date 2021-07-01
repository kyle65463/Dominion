package dominion.controllers.components.emoji;

import dominion.controllers.components.ComponentController;
import dominion.core.GameManager;
import dominion.models.events.interactive.EmojiEvent;
import dominion.models.events.interactive.VoicesEvent;
import dominion.models.player.Player;
import dominion.utils.UiLoader;
import dominion.utils.VoicePlayer;
import javafx.event.ActionEvent;
import javafx.scene.control.ChoiceBox;

public class ChoiceBoxController extends ComponentController {
    // Constructor
    public ChoiceBoxController() {
        initialize();
    }

    // Variables
    private ChoiceBox<String> choiceBox;
    private final String choiceText = "傳送表情/音效";
    private final String[] choices = {choiceText, "嘲諷", "慘叫", "憤怒"};

    // Functions
    private void initialize() {
        rootNode = UiLoader.loadFXML("resources/components/select_button.fxml");
        assert rootNode != null;

        choiceBox = (ChoiceBox<String>) rootNode.lookup("#choiceBox");
        setLayout(10, 500);
        VoicePlayer.initialize();
        choiceBox.getItems().addAll(choices);
        choiceBox.setValue(choiceText);
        choiceBox.setOnAction(this::choiceActivate);
    }

    public void setLayout(double x, double y) {
        rootNode.setTranslateX(x);
        rootNode.setTranslateY(y);
    }

    public void choiceActivate(ActionEvent event) {
        String choice = choiceBox.getValue();
        switch (choice) {
            case "嘲諷" -> {
                Player applicationPlayer = GameManager.getApplicationPlayer();
                String name = applicationPlayer.getName();
                GameManager.sendEvent(new VoicesEvent(0, name));
                choiceBox.setValue(choiceText);
            }
            case "慘叫" -> {
                Player applicationPlayer = GameManager.getApplicationPlayer();
                String name = applicationPlayer.getName();
                GameManager.sendEvent(new VoicesEvent(1, name));
                choiceBox.setValue(choiceText);
            }
            case "憤怒" -> {
                Player applicationPlayer = GameManager.getApplicationPlayer();
                String name = applicationPlayer.getName();
                GameManager.sendEvent(new EmojiEvent(name));
                choiceBox.setValue(choiceText);
            }
        }
    }
}
