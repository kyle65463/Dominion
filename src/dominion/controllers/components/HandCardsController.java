package dominion.controllers.components;

import dominion.models.game.cards.Card;
import dominion.models.game.GameScene;
import dominion.utils.Animator;
import javafx.event.EventHandler;
import javafx.util.Duration;
import javafx.util.Pair;

import java.util.ArrayList;
import java.util.List;

public class HandCardsController extends ComponentController{
    // Constructor

    // Variables
    private final double centerX = 560;
    private final double y = 589;
    private final double paddingX = 12;

    // Functions
    public void arrangeCardsPos(List<Card> cards) {
        List<CardInfo> cardsInfo = new ArrayList<>();
        for (Card card : cards) {
            boolean found = false;
            for(CardInfo cardInfo : cardsInfo){
                if (card.getName().equals(cardInfo.name)) {
                    found = true;
                    break;
                }
            }
            if(!found) {
                cardsInfo.add(new CardInfo(card.getName()));
            }
        }

        double x = calculateFirstX(cardsInfo.size());
        for(CardInfo cardInfo : cardsInfo) {
            cardInfo.pos = x;
            x += paddingX + CardController.width;
        }

        for (Card card : cards) {
            for(CardInfo cardInfo : cardsInfo){
                if(card.getName().equals(cardInfo.name)) {
                    CardController cardController = card.getController();
                    card.setNumRemain(cardInfo.numRemained);
                    cardInfo.numRemained++;
                    if (!GameScene.contains(cardController)) {
                        cardController.setLayout(cardInfo.pos, y);
                        GameScene.add(cardController);
                    } else {
                        GameScene.setToTop(cardController);
                        Animator.transitTo(cardController, cardInfo.pos, y);
                    }
                }
            }
        }
    }

    private double calculateFirstX(int numCards) {
        double x = centerX - (numCards / 2) *  CardController.width - (numCards / 2) * paddingX;
        if (numCards % 2 == 0) {
            x += paddingX / 2;
        } else {
            x -=  CardController.width / 2;
        }
        return x;
    }
}

class CardInfo {
    CardInfo(String name) {
        this.name = name;
        this.numRemained = 1;
    }

    public String name;
    public double pos;
    public int numRemained;
}
