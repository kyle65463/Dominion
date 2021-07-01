package dominion.controllers.components.settings;


import dominion.controllers.components.ComponentController;
import dominion.models.areas.GameScene;
import dominion.utils.UiLoader;
import javafx.scene.control.Button;

public class SettingsButtonController extends ComponentController {
    // Constructor
    public SettingsButtonController() {
        initialize();
    }

    // Functions
    private void initialize() {
        rootNode = UiLoader.loadFXML("resources/components/setting_button.fxml");
        assert rootNode != null;

        Button settingButton = (Button) rootNode.lookup("#settingButton");
        settingButton.setOnMousePressed((e) -> {
            SettingsDialogController sc = new SettingsDialogController();
            GameScene.disable();
            GameScene.add(sc);
        });
        setLayout(10, 463);
    }

    public void setLayout(double x, double y) {
        rootNode.setTranslateX(x);
        rootNode.setTranslateY(y);
    }
}
