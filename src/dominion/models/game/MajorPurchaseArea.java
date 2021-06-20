package dominion.models.game;

import dominion.controllers.components.DisplayedCardController;
import dominion.models.game.DisplayedCard;
import javafx.scene.layout.GridPane;

import java.util.List;

public class MajorPurchaseArea {
    // Constructor
    public MajorPurchaseArea(GridPane gridPane) {
        this.gridPane = gridPane;
    }

    // Variables
    GridPane gridPane;
    List<DisplayedCard> displayedCards;

    // Functions
    public void setDisplayedCards(List<DisplayedCard> displayedCards) {
        this.displayedCards = displayedCards;
        for (int i = 0; i < 2; i++) {
            for (int j = 0; j < 5; j++) {
                int index = i * 5 + j;
                if (index >= displayedCards.size()){
                    break;
                }
                DisplayedCard displayedCard = displayedCards.get(index);
                DisplayedCardController displayedCardController = displayedCard.getController();
                gridPane.add(displayedCardController.getRootNode(), j, i);
            }
        }
    }

    public DisplayedCard getDisplayedCardById(int id) {
        for(DisplayedCard displayedCard : displayedCards){
            if(displayedCard.getId() == id){
                return  displayedCard;
            }
        }
        return null;
    }

    public int getNumNoneRemained() {
        int numNoneRemained = 0;
        for(DisplayedCard displayedCard : displayedCards){
            if(displayedCard.getNumRemain() == 0){
                numNoneRemained++;
            }
        }
        return numNoneRemained;
    }
}
