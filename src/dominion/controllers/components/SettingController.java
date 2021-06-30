package dominion.controllers.components;

import dominion.models.game.GameScene;
import dominion.models.game.cards.Card;
import dominion.utils.CardStyles;
import dominion.utils.Voice;
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
            volumeController.setValue(Voice.getVolume());
            effectController.setValue(Voice.getEffectVolume());
            setLayout(280,200);
            rootNode.setStyle(CardStyles.white);
            confirmButton.setOnMousePressed((e)->{
                GameScene.delete(this);
                Voice.setVolume(volumeController.getValue(),effectController.getValue());
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
