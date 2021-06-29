package dominion.models.game;

import dominion.controllers.components.DisplayedCardController;
import dominion.models.game.DisplayedCard;
import dominion.models.game.cards.Card;
import javafx.scene.layout.GridPane;

import java.util.List;

public class MinorPurchaseArea {
    // Constructor
    public MinorPurchaseArea(GridPane gridPane) {
        this.gridPane = gridPane;
    }

    // Variables
    GridPane gridPane;
    List<DisplayedCard> displayedCards;

    // Functions
    public void setDisplayedCards(List<DisplayedCard> displayedCards) {
        this.displayedCards = displayedCards;
        for (int i = 0; i < 2; i++) {
            for (int j = 0; j < 4; j++) {
                int index = i * 4 + j;
                if (index >= displayedCards.size()){
                    break;
                }
                DisplayedCard displayedCard = displayedCards.get(index);
                DisplayedCardController displayedCardController = displayedCard.getController();
                displayedCardController.setScale(0.7);
                gridPane.add(displayedCardController.getRootNode(), i, j);
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

    public DisplayedCard getDisplayedCardByCard(Card card) {
        for (DisplayedCard displayedCard : displayedCards) {
            if (displayedCard.getCard().getName() == card.getName()) {
                return displayedCard;
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

    public boolean isGameOver() {
        return displayedCards.get(0).getNumRemain() == 0;
    }
}
