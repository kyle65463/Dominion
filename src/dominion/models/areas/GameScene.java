package dominion.models.areas;

import dominion.controllers.components.ComponentController;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.scene.Node;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;

public class GameScene {
    public static void initialize(AnchorPane rootNode) {
        GameScene.rootNode = rootNode;
        GameScene.disableMask = (Pane) rootNode.lookup("#disable_mask");
        disableMask.setVisible(false);
    }

    // Variables
    private static AnchorPane rootNode;
    private static Pane disableMask;
    public static boolean isDisplayingDescription = false;

    public static void disable() {
        setToTop(disableMask);
        disableMask.setVisible(true);
    }

    public static void enable() {
        disableMask.setVisible(false);
    }

    public static boolean contains(Node node) {
        return rootNode.getChildren().contains(node);
    }

    public static boolean contains(ComponentController controller) {
        return rootNode.getChildren().contains(controller.getRootNode());
    }

    public static void setToTop(Node node) {
        if (contains(node)) {
            ObservableList<Node> nodes = FXCollections.observableArrayList(rootNode.getChildren());
            nodes.remove(node);
            nodes.add(node);
            rootNode.getChildren().setAll(nodes);
        }
    }

    public static void setToTop(ComponentController controller) {
        Node node = controller.getRootNode();
        setToTop(node);
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
        disableMask.setOnMouseClicked(eventHandler);
    }

}
