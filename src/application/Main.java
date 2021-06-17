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
    public void start(Stage primaryStage) throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("main.fxml"));
        primaryStage.setTitle("1");
        primaryStage.setScene(new Scene(root));
        primaryStage.setResizable(false);
        primaryStage.show();


        Stage secondStage = new Stage();
        Parent root2 = FXMLLoader.load(getClass().getResource("main.fxml"));
        secondStage.setTitle("2");
        secondStage.setScene(new Scene(root2));
        secondStage.setResizable(false);
        secondStage.show();
    }


    public static void main(String[] args) {
        launch(args);
    }
}
