package dominion.controllers.components;

import dominion.models.game.cards.Card;
import dominion.models.game.GameScene;
import dominion.utils.Animator;


public class DiscardPileController extends ComponentController{
    // Constructor

    // Variables
    private final double cardScale = 0.6;
    private final double x = 95 - (CardController.width * (1 - cardScale)) / 2;
    private final double y = 670 - (CardController.height * (1 - cardScale)) / 2;

    // Functions
    public void addCard(Card card) {
        CardController cardController = card.getController();
        if (!GameScene.contains(cardController)) {
            // Add the card to game scene
            cardController.setScale(cardScale);
            cardController.setLayout(x, y);
            GameScene.add(cardController);
        } else {
            GameScene.setToTop(cardController);
            Animator.transitTo(cardController, x, y, cardScale);
        }
    }
}
