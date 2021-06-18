package dominion.component;

import javafx.scene.Node;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

import java.util.ArrayList;
import java.util.List;

public class HandCardsBox {
    public HandCardsBox(Node gameScene) {
        this.gameScene = (AnchorPane) gameScene;
    }
    private AnchorPane gameScene;
    private final double centerLayoutX = 560;
    private final double layoutY = 589;
    private double layoutX = 560;
    private final double padding = 12;
    private double layoutXOffset = 0;

    List<Card> cards = new ArrayList<>();

    public void addCard(Card card) {
        cards.add(card);
        if (cards.size() % 2 == 0 && cards.size() > 0){
            layoutX = centerLayoutX - (cards.size() / 2) * card.getWidth() - (cards.size() / 2) * padding + (1 / 2) * padding;
            for(Card c : cards) {
                c.setLayout(layoutX, layoutY);
                layoutX += padding + c.getWidth();
                if(!gameScene.getChildren().contains(c.getNode())) {
                    gameScene.getChildren().add(c.getNode());
                }
            }
        }
        else if (cards.size() % 2 == 1){
            layoutX = centerLayoutX - (cards.size() / 2) * card.getWidth() - (cards.size() / 2) * padding;
            for(Card c : cards) {
                c.setLayoutCenterX(layoutX, layoutY);
                layoutX += padding + c.getWidth();
                if(!gameScene.getChildren().contains(c.getNode())) {
                    gameScene.getChildren().add(c.getNode());
                }
            }
        }
    }

}
