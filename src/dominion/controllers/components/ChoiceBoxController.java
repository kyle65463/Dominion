package dominion.controllers.components;
import dominion.game.GameManager;
import dominion.models.events.game.VoicesEvent;
import dominion.models.game.Player;
import dominion.models.game.cards.Card;
import dominion.utils.Voice;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.layout.Pane;

import java.awt.*;
import java.net.URL;
import java.util.ResourceBundle;

public class ChoiceBoxController extends ComponentController{
    public ChoiceBoxController(){initialize();}
    private ChoiceBox<String> choiceBox;
    private String[] choices = {"選擇行動","嘲諷","慘叫"};
    private void initialize() {
        try {
            rootNode = (Pane) FXMLLoader.load(Card.class.getClassLoader().getResource("resources/components/select_button.fxml"));
            choiceBox = (ChoiceBox<String>) rootNode.lookup("#choiceBox");
            setLayout(10,500);
            Voice.initalize();
            choiceBox.getItems().addAll(choices);
            choiceBox.setValue("選擇行動");
            choiceBox.setOnAction(this::choiceActivate);
        } catch (Exception e) {

        }
    }
    public void setLayout(double x, double y) {
        rootNode.setTranslateX(x);
        rootNode.setTranslateY(y);
    }
    public void choiceActivate(ActionEvent event){
        String choice = choiceBox.getValue();
        if(choice == "嘲諷"){
            Player applicationPlayer = GameManager.getApplicationPlayer();
            String name = applicationPlayer.getName();
            GameManager.sendEvent(new VoicesEvent(0,name));
            choiceBox.setValue("選擇行動");
        }else if(choice == "慘叫"){
            Player applicationPlayer = GameManager.getApplicationPlayer();
            String name = applicationPlayer.getName();
            GameManager.sendEvent(new VoicesEvent(1,name));
            choiceBox.setValue("選擇行動");
        }
    }


}
