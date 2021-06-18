package dominion.components;

import java.util.ArrayList;
import java.util.List;

public class DeckBox {
    // Constructor
    public DeckBox(GameScene gameScene) {
        this.gameScene = gameScene;
    }

    // Variables
    private GameScene gameScene;
    private List<Card> cards = new ArrayList<>();
    private final double cardScale = 0.6;
    private final double x = 15 - (Card.originalWidth * (1 - cardScale)) / 2;
    private final double y = 670 - (Card.originalHeight * (1 - cardScale)) / 2;

    // Functions
    public List<Card> getCards() {
        return cards;
    }

    public void removeCards() {
        cards.clear();
    }

    public void addCard(Card card) {
        cards.add(card);
        card.setScale(cardScale);
        card.setLayout(x, y);
        if (!gameScene.contains(card.getNode())) {
            // Add the card to game scene
            gameScene.add(card.getNode());
        }
    }
}
