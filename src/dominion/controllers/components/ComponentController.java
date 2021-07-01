package dominion.controllers.components;

import dominion.controllers.Controller;
import javafx.scene.Node;

/*
    A component corresponds to a FXML file for a piece of UI.
    The ComponentController controls the component's UI,
    separating the UI and logic.
 */
public abstract class ComponentController extends Controller {
    // Variables
    protected Node rootNode;

    // Functions
    public Node getRootNode() {
        return rootNode;
    }
}
