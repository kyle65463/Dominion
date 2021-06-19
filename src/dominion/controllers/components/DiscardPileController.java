package dominion.controllers.components;

import dominion.models.game.Card;
import dominion.models.game.GameScene;
import dominion.utils.Animator;


public class DiscardPileController extends ComponentController{
    // Constructor
    public DiscardPileController(GameScene gameScene) {
        super(gameScene);
    }

    // Variables
    private final double cardScale = 0.6;
    private final double x = 95 - (CardController.width * (1 - cardScale)) / 2;
    private final double y = 670 - (CardController.height * (1 - cardScale)) / 2;

    // Functions
    public void addCard(Card card) {
        CardController cardController = card.getController();
        if (!gameScene.contains(cardController)) {
            // Add the card to game scene
            cardController.setScale(cardScale);
            cardController.setLayout(x, y);
            gameScene.add(cardController);
        } else {
            gameScene.setToTop(cardController);
            Animator.transitTo(cardController, x, y, cardScale);
        }
    }
}
