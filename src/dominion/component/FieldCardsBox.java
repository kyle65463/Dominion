package dominion.component;

import javafx.animation.ScaleTransition;
import javafx.animation.TranslateTransition;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.Node;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.util.Duration;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class FieldCardsBox {
    public FieldCardsBox(Node gameScene) {
        this.gameScene = (AnchorPane) gameScene;
    }
    private AnchorPane gameScene;
    private double scale = 0.6;
    private final double baseLayoutX = 235 - (Card.originalWidth * (1 - scale)) / 2;
    private final double baseLayoutY = 390 - (Card.originalHeight * (1 - scale)) / 2;
    private double layoutXOffset = 0;


    List<Card> cards = new ArrayList<>();

    public void addCard(Card card) {
        cards.add(card);
        if(!gameScene.getChildren().contains(card.getNode())) {
            card.setScale(scale);
            card.setLayout(baseLayoutX + layoutXOffset, baseLayoutY);
            gameScene.getChildren().add(card.getNode());
        }
        else{
            rearrange();
            ScaleTransition scaleTransition = new ScaleTransition();
            scaleTransition.setNode(card.getNode());
            scaleTransition.setToX(scale);
            scaleTransition.setToY(scale);
            scaleTransition.setDuration(Duration.millis(3000));

            TranslateTransition translateTransition = new TranslateTransition();
            translateTransition.setNode(card.getNode());
            translateTransition.setToX(baseLayoutX + layoutXOffset);
            translateTransition.setToY(baseLayoutY);
            translateTransition.setDuration(Duration.millis(3000));

            translateTransition.play();
            scaleTransition.play();
        }
        layoutXOffset = cards.size() * (card.getWidth() * 0.7);
    }

    private void rearrange() {
        ObservableList<Node> nodes = FXCollections.observableArrayList(gameScene.getChildren());
        List<Integer> indices = new ArrayList<>();
        for (Card card : cards) {
            int i = nodes.indexOf(card.getNode());
            if(i > 0) {
                indices.add(i);
            }
            else{
                System.out.println("Field card error");
            }
        }
        Collections.sort(indices);
        for (int i = 0; i < indices.size(); i++) {
            System.out.println(indices.get(i));
            nodes.set(indices.get(i), cards.get(i).getNode());
        }
        gameScene.getChildren().setAll(nodes);
    }

}
