package dominion.components;

import dominion.utils.Animator;

import java.util.ArrayList;
import java.util.List;

public class FieldCardsBox {
    // Constructor
    public FieldCardsBox(GameScene gameScene) {
        this.gameScene = gameScene;
    }

    // Variables
    private GameScene gameScene;
    private final double cardScale = 0.6;
    private final double baseX = 235 - (Card.originalWidth * (1 - cardScale)) / 2;
    private final double y = 390 - (Card.originalHeight * (1 - cardScale)) / 2;
    private double offsetX = 0;
    private List<Card> cards = new ArrayList<>();

    // Functions
    public List<Card> getCards() {
        return cards;
    }

    public void removeCards() {
        cards.clear();
    }

    public void addCard(Card card) {
        cards.add(card);
        if (!gameScene.contains(card.getNode())) {
            // Add the card to game scene
            card.setScale(cardScale);
            card.setLayout(baseX + offsetX, y);
            gameScene.add(card.getNode());
        } else {
            gameScene.setToTop(card.getNode());
            Animator.transitTo(card.getNode(), baseX + offsetX, y, cardScale);
        }
        offsetX = cards.size() * (card.getWidth() * 0.7);
    }
}
