package dominion.controllers.components;
import dominion.core.GameManager;
import dominion.models.cards.Card;
import dominion.models.events.game.EmojiEvent;
import dominion.models.events.game.VoicesEvent;
import dominion.models.player.Player;
import dominion.utils.VoicePlayer;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.ChoiceBox;
import javafx.scene.layout.Pane;

public class ChoiceBoxController extends ComponentController{
    public ChoiceBoxController(){initialize();}
    private ChoiceBox<String> choiceBox;
    private String[] choices = {"選擇行動","嘲諷","慘叫","憤怒"};
    private void initialize() {
        try {
            rootNode = (Pane) FXMLLoader.load(Card.class.getClassLoader().getResource("resources/components/select_button.fxml"));
            choiceBox = (ChoiceBox<String>) rootNode.lookup("#choiceBox");
            setLayout(10,500);
            VoicePlayer.initialize();
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
        }else if(choice == "憤怒"){
            Player applicationPlayer = GameManager.getApplicationPlayer();
            String name = applicationPlayer.getName();
            GameManager.sendEvent(new EmojiEvent(name));
            choiceBox.setValue("選擇行動");
        }
    }


}
