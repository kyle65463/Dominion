package dominion.controllers.components;

import dominion.models.areas.GameScene;
import dominion.models.cards.Card;
import dominion.models.cards.CardStyles;
import dominion.utils.UiLoader;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.layout.Pane;

public class LeaveDialogController extends ComponentController {
    // Constructor
    public LeaveDialogController() {
        initialize();
    }

    // Functions
    private void initialize() {
        rootNode = UiLoader.loadFXML("resources/components/leave_message.fxml");
        assert rootNode != null;

        rootNode.setStyle(CardStyles.white);
        setLayout(400, 300);
    }

    public void setLayout(double x, double y) {
        rootNode.setTranslateX(x);
        rootNode.setTranslateY(y);
    }
}
