package dominion;

import dominion.controllers.scenes.GameController;
import dominion.controllers.scenes.MainController;
import dominion.models.User;
import dominion.models.cards.CardList;
import dominion.params.MainSceneParams;
import dominion.utils.Navigator;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.util.ArrayList;
import java.util.List;

public class Main extends Application {
    @Override
    public void start(Stage primaryStage) throws Exception {
        CardList.initialize();
        Stage stage = new Stage();
        stage.setTitle("Dominion");
        stage.setResizable(false);
        MainSceneParams params = new MainSceneParams("kyle15989", "localhost", "9999");
        Navigator.to(stage, "resources/scenes/main.fxml", params);
    }

    public static void main(String[] args) {
        launch(args);
    }
}
