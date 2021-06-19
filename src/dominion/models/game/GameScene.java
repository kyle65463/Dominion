package dominion.models.game;

import dominion.controllers.components.ComponentController;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.Node;
import javafx.scene.layout.AnchorPane;

import java.io.Serializable;

public class GameScene {
    public GameScene(AnchorPane rootNode) {
        this.rootNode = rootNode;
    }

    private AnchorPane rootNode;

    public boolean contains(ComponentController controller) {
        return rootNode.getChildren().contains(controller.getRootNode());
    }

    public void setToTop(ComponentController controller) {
        Node node = controller.getRootNode();
        if(contains(controller)) {
            ObservableList<Node> nodes = FXCollections.observableArrayList(rootNode.getChildren());
            nodes.remove(node);
            nodes.add(node);
            rootNode.getChildren().setAll(nodes);
        }
    }

    public void add(ComponentController controller) {
        Node node = controller.getRootNode();
        if(!contains(controller)) {
            rootNode.getChildren().add(node);
        }
    }

    public void delete(ComponentController controller) {
        Node node = controller.getRootNode();
        if(contains(controller)) {
            rootNode.getChildren().remove(node);
        }
    }
}
