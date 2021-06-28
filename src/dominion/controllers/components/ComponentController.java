package dominion.controllers.components;

import dominion.models.game.GameScene;
import javafx.scene.Node;

import java.io.Serializable;

public class ComponentController {
    // Constructor
    public ComponentController() {}

    // Variables
    protected Node rootNode;

    // Functions
    public Node getRootNode() {
        return rootNode;
    }
}
