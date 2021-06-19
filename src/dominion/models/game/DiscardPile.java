package dominion.models.game;

import dominion.controllers.components.CardController;
import dominion.utils.Animator;

import java.util.ArrayList;
import java.util.List;

public class DiscardPile {
    // Constructor
    public DiscardPile(GameScene gameScene) {
        this.gameScene = gameScene;
    }

    // Variables
    private GameScene gameScene;
    private List<Card> cards = new ArrayList<>();
    private final double cardScale = 0.6;
    private final double x = 95 - (CardController.width * (1 - cardScale)) / 2;
    private final double y = 670 - (CardController.height * (1 - cardScale)) / 2;

    // Functions
    public List<Card> getCards() {
        return cards;
    }

    public void removeCards() {
        cards.clear();
    }

    public void addCard(Card card) {
        cards.add(card);
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
