package application.util;

import application.RoomController;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Navigator {
    public static void to(ActionEvent event, String fxml) {
        try {
            Parent root = FXMLLoader.load(Navigator.class.getClassLoader().getResource(fxml));
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.setScene(new Scene(root));
            stage.show();
        } catch (Exception ee) {
            System.out.println("Navigator Error");
            System.out.println(ee);
        }
    }

    public static void to(ActionEvent event, Parent root) {
        try {
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.setScene(new Scene(root));
            stage.show();
        } catch (Exception ee) {
            System.out.println("Navigator Error");
            System.out.println(ee);
        }
    }
}
