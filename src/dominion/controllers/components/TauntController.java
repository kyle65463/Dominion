package dominion.controllers.components;
import dominion.models.game.cards.Card;
import dominion.models.game.cards.treasures.Treasure;
import dominion.utils.TauntVoice;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.event.ActionEvent;
import javafx.scene.control.Label;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import java.io.File;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class TauntController extends ComponentController{
    public TauntController(){initialize();}
    private Button tauntButton;

    private void initialize() {
        try {
            rootNode = (Pane) FXMLLoader.load(Card.class.getClassLoader().getResource("resources/components/taunt_button.fxml"));
            tauntButton = (Button) rootNode.lookup("#tauntButton");
            setLayout(10,400);
            TauntVoice.initialize();
            tauntButton.setOnMousePressed((e)->{
                TauntVoice.playTauntVoice();
                System.out.println("press");});
        } catch (Exception e) {

        }
    }
    public void setLayout(double x, double y) {
        rootNode.setTranslateX(x);
        rootNode.setTranslateY(y);
    }
}
