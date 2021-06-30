package dominion.controllers.components;

import dominion.models.areas.GameScene;
import dominion.models.cards.Card;
import dominion.models.cards.CardStyles;
import dominion.utils.VoicePlayer;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.control.Slider;
import javafx.scene.layout.Pane;

public class SettingController extends  ComponentController{
    public SettingController(){initialize();}
    private Button confirmButton;
    private Slider volumeController;
    private Slider effectController;

    private void initialize() {
        try {
            rootNode = (Pane) FXMLLoader.load(Card.class.getClassLoader().getResource("resources/components/setting_interface.fxml"));
            confirmButton = (Button) rootNode.lookup("#confirmButton");
            volumeController = (Slider) rootNode.lookup("#volumeController");
            effectController = (Slider) rootNode.lookup("#effectController");
            volumeController.setValue(VoicePlayer.getVolume());
            effectController.setValue(VoicePlayer.getEffectVolume());
            setLayout(280,200);
            rootNode.setStyle(CardStyles.white);
            confirmButton.setOnMousePressed((e)->{
                GameScene.delete(this);
                VoicePlayer.setVolume(volumeController.getValue(),effectController.getValue());
                GameScene.enable();
            });

        } catch (Exception e) {

        }
    }
    public void setLayout(double x, double y) {
        rootNode.setTranslateX(x);
        rootNode.setTranslateY(y);
    }

}
