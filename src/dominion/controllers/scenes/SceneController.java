package dominion.controllers.scenes;

import dominion.controllers.Controller;
import dominion.params.SceneParams;
import javafx.stage.Stage;

/*
    Each scene has a SceneController.
    When navigating to other scenes, pass SceneParams into the Navigator,
    and the Navigator will call initialize of the target SceneController with SceneParams.
 */
public abstract class SceneController extends Controller {
    public abstract void initialize(Stage stage, SceneParams parameters);
}
