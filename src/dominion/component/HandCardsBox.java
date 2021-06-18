package dominion.component;

import dominion.util.Animator;

import java.util.ArrayList;
import java.util.List;

public class HandCardsBox {
    // Constructor
    public HandCardsBox(GameScene gameScene) {
        this.gameScene = gameScene;
    }

    // Variables
    private GameScene gameScene;
    private List<Card> cards = new ArrayList<>();
    private final double centerX = 560;
    private final double y = 589;
    private final double padding = 12;

    // Functions
    public List<Card> getCards() {
        return getCards();
    }

    public void removeCards() {
        cards.clear();
    }

    public void addCard(Card card) {
        cards.add(card);
        rearrange();
    }

    public void removeCard(Card card) {
        cards.remove(card);
        rearrange();
    }

    private void rearrange() {
        double x = calculateFirstX();
        for (Card card : cards) {
            if (!gameScene.contains(card.getNode())) {
                card.setLayout(x, y);
                gameScene.add(card.getNode());
            } else {
                gameScene.setToTop(card.getNode());
                Animator.transitTo(card.getNode(), x, y);
            }
            x += padding + card.getWidth();
        }
    }

    private double calculateFirstX() {
        double x = centerX - (cards.size() / 2) * Card.originalWidth - (cards.size() / 2) * padding;
        if (cards.size() % 2 == 0) {
            x += padding / 2;
        } else {
            x -= Card.originalWidth / 2;
        }
        return x;
    }
}
