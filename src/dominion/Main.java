package dominion;

import dominion.controllers.scenes.GameController;
import dominion.controllers.scenes.MainController;
import dominion.models.User;
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
        double offsetX1 = 100;
        double offsetX2 = 720;
        double offsetY1 = 0;
        double offsetY2 = 500;

        newWindow(offsetX1, offsetY1, "kyle15989");
//        newWindow(offsetX2, offsetY1, "DomioDestroyer");
//        newWindow(offsetX1, offsetY2, "freeChina");
//        newWindow(offsetX2, offsetY2, "Ken30510");
    }

    private void newWindow(double x, double y, String name) throws Exception{
        Stage stage = new Stage();
        FXMLLoader loader = new FXMLLoader(getClass().getResource("../resources/scenes/main.fxml"));
        Parent root = loader.load();
        MainController controller = loader.getController();
        controller.setName(name);

//        User applicationUser = new User(0, "kyle15989");
//        List<User> users = new ArrayList<>();
//        users.add(applicationUser);
//        users.add(new User(1, "DomioDestroyer"));
//        users.add(new User(2, "freeChina"));
//        GameController controller = loader.getController();
//        controller.initialize(users, applicationUser);
        stage.setTitle("Dominion");
        stage.setScene(new Scene(root));
        stage.setResizable(false);
        stage.setX(x);
        stage.setY(y);
        stage.show();
    }


    public static void main(String[] args) {
        launch(args);
    }
}
