package dominion;

import dominion.models.cards.CardFactory;
import dominion.params.MainSceneParams;
import dominion.utils.Navigator;
import javafx.application.Application;
import javafx.stage.Stage;

public class Main extends Application {
    @Override
    public void start(Stage primaryStage) throws Exception {
        CardFactory.initialize();
        Stage stage = new Stage();
        stage.setTitle("Dominion");
        stage.setResizable(false);
        MainSceneParams params = new MainSceneParams("kyle15989", "localhost", "9999");
        Navigator.to(stage, "resources/scenes/main.fxml", params);
    }

    public static void main(String[] args) {
        launch(args);
        System.exit(0);
    }
}
