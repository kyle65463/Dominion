package dominion.controllers.components;

import dominion.models.game.GameScene;
import javafx.scene.Node;

import java.io.Serializable;

public class ComponentController {
    // Constructor
    public ComponentController() {}
    public ComponentController(GameScene gameScene) {
        this.gameScene = gameScene;
    }

    // Variables
    protected GameScene gameScene;
    protected Node rootNode;

    // Functions
    public Node getRootNode() {
        return rootNode;
    }
}
