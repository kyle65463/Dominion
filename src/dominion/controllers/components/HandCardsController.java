package dominion.controllers.components;

import dominion.models.game.cards.Card;
import dominion.models.game.GameScene;
import dominion.utils.Animator;
import javafx.util.Duration;

import java.util.List;

public class HandCardsController extends ComponentController{
    // Constructor
    public HandCardsController(GameScene gameScene) {
        super(gameScene);
    }

    // Variables
    private final double centerX = 560;
    private final double y = 589;
    private final double paddingX = 12;

    // Functions
    public void arrangeCardsPos(List<Card> cards) {
        double x = calculateFirstX(cards);
        for (Card card : cards) {
            CardController cardController = card.getController();
            if (!gameScene.contains(cardController)) {
                cardController.setLayout(x, y);
                gameScene.add(cardController);
            } else {
                gameScene.setToTop(cardController);
                Animator.transitTo(cardController, x, y);
            }
            x += paddingX + CardController.width;
        }
    }

    private double calculateFirstX(List<Card> cards) {
        double x = centerX - (cards.size() / 2) *  CardController.width - (cards.size() / 2) * paddingX;
        if (cards.size() % 2 == 0) {
            x += paddingX / 2;
        } else {
            x -=  CardController.width / 2;
        }
        return x;
    }
}
