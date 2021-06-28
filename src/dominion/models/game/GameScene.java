package dominion.models.game;

import dominion.controllers.components.ComponentController;
import dominion.models.game.cards.Card;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.scene.Node;
import javafx.scene.layout.AnchorPane;

import java.io.Serializable;

public class GameScene {
    public static void initialize(AnchorPane rootNode) {
        GameScene.rootNode = rootNode;
    }

    private static AnchorPane rootNode;
    public static boolean ifDisplay = false;
    public static void disable() {
        rootNode.setDisable(true);
    }

    public static boolean contains(ComponentController controller) {
        return rootNode.getChildren().contains(controller.getRootNode());
    }

    public static void setToTop(ComponentController controller) {
        Node node = controller.getRootNode();
        if (contains(controller)) {
            ObservableList<Node> nodes = FXCollections.observableArrayList(rootNode.getChildren());
            nodes.remove(node);
            nodes.add(node);
            rootNode.getChildren().setAll(nodes);
        }
    }

    public static void add(ComponentController controller) {
        Node node = controller.getRootNode();
        if (!contains(controller)) {
            rootNode.getChildren().add(node);
        }
    }

    public static void delete(ComponentController controller) {
        Node node = controller.getRootNode();
        if (contains(controller)) {
            rootNode.getChildren().remove(node);
        }
    }

    public static void setOnPressed(EventHandler eventHandler){
        rootNode.setOnMouseClicked(eventHandler);
    }

}
