package dominion.controllers.components;

import dominion.models.game.cards.Card;
import dominion.models.game.GameScene;

public class DeckController extends ComponentController{
    // Constructor
    public DeckController(GameScene gameScene) {
        super(gameScene);
    }

    // Variables
    private final double cardScale = 0.6;
    private final double x = 15 - (CardController.width * (1 - cardScale)) / 2;
    private final double y = 670 - (CardController.height * (1 - cardScale)) / 2;

    // Functions
    public void addCard(Card card) {
        CardController cardController = card.getController();
        cardController.setScale(cardScale);
        cardController.setLayout(x, y);
        if (!gameScene.contains(cardController)) {
            // Add the card to game scene
            gameScene.add(cardController);
        }
    }
}
