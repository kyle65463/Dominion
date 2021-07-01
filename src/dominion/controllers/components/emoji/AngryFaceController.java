package dominion.controllers.components.emoji;

import dominion.controllers.components.ComponentController;
import dominion.models.cards.Card;
import dominion.models.cards.CardStyles;
import dominion.utils.UiLoader;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Label;
import javafx.scene.layout.Pane;

import java.awt.event.ActionEvent;

public class AngryFaceController extends ComponentController {
    // Constructor
    public AngryFaceController() {
        initialize();
    }

    // Variables
    private Label nameLabel;

    // Functions
    private void initialize() {
        rootNode = UiLoader.loadFXML("resources/components/angry_face.fxml");
        assert rootNode != null;

        nameLabel = (Label) rootNode.lookup("#nameLabel");
        nameLabel.setText("hello");
        rootNode.setStyle(CardStyles.white);
        setLayout(18, 370);
    }

    public void setLayout(double x, double y) {
        rootNode.setTranslateX(x);
        rootNode.setTranslateY(y);
    }

    public void setName(String name) {
        nameLabel.setText(name + "森氣惹!");
    }
}
