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

    public static void initialize(GridPane gridPane, int numRows, int numCols) {
        gridPane = gridPane;
        numRows = numRows;
        numCols = numCols;
    }

    // Variables
    private static int numRows;
    private static int numCols;
    protected static List<DisplayedCard> displayedCards;
    protected static GridPane gridPane;

    // Functions
    public static void setDisplayedCards(List<DisplayedCard> displayedCards) {
        displayedCards = displayedCards;
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

    public static DisplayedCard getDisplayedCardById(int id) {
        for(DisplayedCard displayedCard : displayedCards){
            if(displayedCard.getId() == id){
                return  displayedCard;
            }
        }
        return null;
    }

    public static DisplayedCard getDisplayedCardByCard(Card card) {
        for (DisplayedCard displayedCard : displayedCards) {
            if (displayedCard.getCard().getName() == card.getName()) {
                return displayedCard;
            }
        }
        return null;
    }

    public static int getNumNoneRemained() {
        int numNoneRemained = 0;
        for(DisplayedCard displayedCard : displayedCards){
            if(displayedCard.getNumRemain() == 0){
                numNoneRemained++;
            }
        }
        return numNoneRemained;
    }
}
