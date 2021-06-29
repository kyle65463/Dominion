package dominion.controllers.components;

import dominion.models.game.cards.Card;
import dominion.models.game.GameScene;
import dominion.utils.Animator;

public class FieldCardsController extends ComponentController{
    // Constructor


    // Variables
    private final double cardScale = 0.6;
    private final double baseX = 235 - (CardController.width * (1 - cardScale)) / 2;
    private final double y = 390 - (CardController.height * (1 - cardScale)) / 2;

    // Functions
    public void addCard(Card card, int numCards) {
        CardController cardController = card.getController();
        double offsetX = (numCards - 1) * (CardController.width * cardScale * 0.7);
        if (!GameScene.contains(cardController)) {
            // Add the card to game scene
            cardController.setScale(cardScale);
            cardController.setLayout(baseX + offsetX, y);
            GameScene.add(cardController);
        } else {
            GameScene.setToTop(cardController);
            Animator.transitTo(cardController, baseX + offsetX, y, cardScale);
        }
    }
}
