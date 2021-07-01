package dominion.controllers.cardcontainer;

import dominion.controllers.components.CardController;
import dominion.models.cards.Card;
import dominion.models.areas.GameScene;
import dominion.utils.Animator;


public class DiscardPileController extends CardContainerController {
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
