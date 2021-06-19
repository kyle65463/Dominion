package dominion.models.game;

import dominion.controllers.components.DisplayedCardController;
import dominion.models.game.DisplayedCard;
import javafx.scene.layout.GridPane;

import java.util.List;

public class MinorPurchaseArea {
    // Constructor
    public MinorPurchaseArea(GridPane gridPane) {
        this.gridPane = gridPane;
    }

    // Variables
    GridPane gridPane;

    // Functions
    public void setDisplayedCards(List<DisplayedCard> displayedCards) {
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 2; j++) {
                int index = i * 2 + j;
                if (index >= displayedCards.size()){
                    break;
                }
                DisplayedCard displayedCard = displayedCards.get(index);
                DisplayedCardController displayedCardController = displayedCard.getController();
                displayedCardController.setScale(0.7);
                gridPane.add(displayedCardController.getRootNode(), j, i);
            }
        }
    }
}
