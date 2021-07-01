package dominion.utils;

import dominion.controllers.scenes.SceneController;
import dominion.params.SceneParams;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Navigator {
    public static void to(Stage stage, String fxml, SceneParams parameters) {
        try {
            FXMLLoader loader = new FXMLLoader(Navigator.class.getClassLoader().getResource(fxml));
            Parent root = loader.load();
            SceneController controller = (SceneController)loader.getController();
            controller.initialize(stage, parameters);

            Scene scene = new Scene(root);
            if(fxml.contains("game.fxml")) {
                scene.getStylesheets().add(Navigator.class.getClassLoader().getResource("resources/styles/game.css").toExternalForm());
            }else if(fxml.contains("main.fxml")){
                scene.getStylesheets().add(Navigator.class.getClassLoader().getResource("resources/styles/main.css").toExternalForm());
            }
            stage.setScene(scene);
            stage.show();
        } catch (Exception e) {
            System.out.println("Navigator Error");
            System.out.println(e);
        }
    }
}
