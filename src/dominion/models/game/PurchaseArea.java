package dominion.models.game;

import dominion.controllers.components.DisplayedCardController;
import dominion.models.game.cards.Card;
import javafx.scene.layout.GridPane;

import java.util.List;

public abstract class PurchaseArea {
    // Constructor
    public PurchaseArea(GridPane gridPane, int numRows, int numCols){
        this.gridPane = gridPane;
        this.numRows = numRows;
        this.numCols = numCols;
    }

    // Variables
    private final int numRows;
    private final int numCols;
    protected List<DisplayedCard> displayedCards;
    protected GridPane gridPane;

    // Functions
    public void setDisplayedCards(List<DisplayedCard> displayedCards) {
        this.displayedCards = displayedCards;
        for (int i = 0; i < numRows; i++) {
            for (int j = 0; j < numCols; j++) {
                int index = i * numCols + j;
                if (index >= displayedCards.size()){
                    break;
                }
                DisplayedCard displayedCard = displayedCards.get(index);
                DisplayedCardController displayedCardController = displayedCard.getController();
                if(numCols == 2) {
                    displayedCardController.setScale(0.7);
                }
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
}
