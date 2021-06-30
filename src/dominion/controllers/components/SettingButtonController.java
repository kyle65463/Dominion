package dominion.controllers.components;


import dominion.models.game.GameScene;
import dominion.models.game.cards.Card;
import dominion.utils.Voice;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.layout.Pane;

public class SettingButtonController extends ComponentController{
    public SettingButtonController(){initialize();};
    private Button settingButton;

    private void initialize() {
        try {
            rootNode = (Pane) FXMLLoader.load(Card.class.getClassLoader().getResource("resources/components/setting_button.fxml"));
            settingButton = (Button) rootNode.lookup("#settingButton");
            settingButton.setOnMousePressed((e)->{
                SettingController sc = new SettingController();
                GameScene.disable();
                GameScene.add(sc);
            });
            setLayout(790,18);
        } catch (Exception e) {

        }
    }
    public void setLayout(double x, double y) {
        rootNode.setTranslateX(x);
        rootNode.setTranslateY(y);
    }
}
