package dominion.components;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.Node;
import javafx.scene.layout.AnchorPane;

public class GameScene {
    public GameScene(AnchorPane rootNode) {
        this.rootNode = rootNode;
    }

    private AnchorPane rootNode;

    public boolean contains(Node node) {
        return rootNode.getChildren().contains(node);
    }

    public void setToTop(Node node) {
        if(contains(node)) {
            ObservableList<Node> nodes = FXCollections.observableArrayList(rootNode.getChildren());
            nodes.remove(node);
            nodes.add(node);
            rootNode.getChildren().setAll(nodes);
        }
    }

    public void add(Node node) {
        if(!contains(node)) {
            rootNode.getChildren().add(node);
        }
    }
}
