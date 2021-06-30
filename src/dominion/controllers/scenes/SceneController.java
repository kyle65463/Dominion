package dominion.controllers.scenes;

import dominion.params.SceneParams;
import javafx.stage.Stage;

public abstract class SceneController {
    public abstract void initialize(Stage stage, SceneParams parameters);
}
