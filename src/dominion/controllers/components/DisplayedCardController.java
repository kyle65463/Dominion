package dominion.controllers.components;

import dominion.models.game.DisplayedCard;
import javafx.fxml.FXMLLoader;

public class DisplayedCardController extends ComponentController{
    // Constructor
    public DisplayedCardController() {
        initialize();
    }

    // Functions
    public void setScale(double scale) {
        rootNode.setScaleX(scale);
        rootNode.setScaleY(scale);
    }

    private void initialize() {
        try {
            rootNode = FXMLLoader.load(DisplayedCard.class.getClassLoader().getResource("resources/components/displayed_card.fxml"));
        } catch (Exception e) {

        }
    }
}
