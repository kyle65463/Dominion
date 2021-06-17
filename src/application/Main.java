package application;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ListView;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("main.fxml"));
        primaryStage.setTitle("1");
        primaryStage.setScene(new Scene(root));
        primaryStage.setResizable(false);
        primaryStage.setX(100);
        primaryStage.setY(0);
        primaryStage.show();

        Stage secondStage = new Stage();
        Parent root2 = FXMLLoader.load(getClass().getResource("main.fxml"));
        secondStage.setTitle("2");
        secondStage.setScene(new Scene(root2));
        secondStage.setResizable(false);
        secondStage.setX(720);
        secondStage.setY(0);
        secondStage.show();


        Stage thirdStage = new Stage();
        Parent root3 = FXMLLoader.load(getClass().getResource("main.fxml"));
        thirdStage.setTitle("3");
        thirdStage.setScene(new Scene(root3));
        thirdStage.setResizable(false);
        thirdStage.setX(100);
        thirdStage.setY(500);
        thirdStage.show();

        Stage fourthStage = new Stage();
        Parent root4 = FXMLLoader.load(getClass().getResource("main.fxml"));
        fourthStage.setTitle("4");
        fourthStage.setScene(new Scene(root4));
        fourthStage.setResizable(false);
        fourthStage.setX(720);
        fourthStage.setY(500);
        fourthStage.show();
    }


    public static void main(String[] args) {
        launch(args);
    }
}
