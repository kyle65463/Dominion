package dominion.controllers.components.settings;

import dominion.controllers.components.ComponentController;
import dominion.models.areas.GameScene;
import dominion.models.cards.Card;
import dominion.models.cards.CardStyles;
import dominion.utils.UiLoader;
import dominion.utils.VoicePlayer;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.control.Slider;
import javafx.scene.layout.Pane;

public class SettingsDialogController extends ComponentController {
    // Constructor
    public SettingsDialogController() {
        initialize();
    }

    // Variables
    private Slider volumeController;
    private Slider effectController;

    // Functions
    private void initialize() {
        rootNode = UiLoader.loadFXML("resources/components/setting_interface.fxml");
        assert rootNode != null;

        // Variables
        Button confirmButton = (Button) rootNode.lookup("#confirmButton");
        volumeController = (Slider) rootNode.lookup("#volumeController");
        effectController = (Slider) rootNode.lookup("#effectController");
        volumeController.setValue(VoicePlayer.getVolume());
        effectController.setValue(VoicePlayer.getEffectVolume());
        setLayout(280, 200);
        rootNode.setStyle(CardStyles.white);
        confirmButton.setOnMousePressed((e) -> {
            GameScene.delete(this);
            VoicePlayer.setVolume(volumeController.getValue(), effectController.getValue());
            GameScene.enable();
        });
    }

    public void setLayout(double x, double y) {
        rootNode.setTranslateX(x);
        rootNode.setTranslateY(y);
    }
}
