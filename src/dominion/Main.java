package dominion;

import dominion.controller.MainController;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception {
        double offsetX1 = 100;
        double offsetX2 = 720;
        double offsetY1 = 0;
        double offsetY2 = 500;

        newWindow(offsetX1, offsetY1, "kyle15989");
        newWindow(offsetX2, offsetY1, "DomioDestroyer");
        newWindow(offsetX1, offsetY2, "freeChina");
        newWindow(offsetX2, offsetY2, "Ken30510");
    }

    private void newWindow(double x, double y, String name) throws Exception{
        Stage stage = new Stage();
        FXMLLoader loader = new FXMLLoader(getClass().getResource("../resources/view/main.fxml"));
        Parent root = loader.load();
        MainController controller = loader.getController();
        controller.setName(name);
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
