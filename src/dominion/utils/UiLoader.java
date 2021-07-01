package dominion.utils;

import dominion.controllers.components.ActionBarController;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;

import java.util.Objects;

public class UiLoader {
    public static Node loadFXML(String fxml){
        try {
            return FXMLLoader.load(Objects.requireNonNull(UiLoader.class.getClassLoader().getResource(fxml)));
        }
        catch (Exception e){
            System.err.println("UiLoader error");
            System.err.println(e);
        }
        return null;
    }
}
