//package dominion.controllers.components;
//
//import dominion.game.GameManager;
//import dominion.game.Logger;
//import dominion.models.events.game.VoicesEvent;
//import dominion.models.game.Player;
//import dominion.models.game.cards.Card;
//import dominion.utils.Voice;
//import javafx.fxml.FXMLLoader;
//import javafx.scene.control.Button;
//import javafx.scene.layout.Pane;
//
//public class VoiceController extends ComponentController {
//    public VoiceController() {
//        initialize();
//    }
//
//    private Button tauntButton;
//    private Button shoutButton;
//    private void initialize() {
//        try {
//            rootNode = (Pane) FXMLLoader.load(Card.class.getClassLoader().getResource("resources/components/voice_button.fxml"));
//            tauntButton = (Button) rootNode.lookup("#tauntButton");
//            shoutButton = (Button) rootNode.lookup("#shoutButton");
//            setLayout(10, 440);
//            Voice.initalize();
//            tauntButton.setOnMousePressed((e) -> {
//                Player applicationPlayer = GameManager.getApplicationPlayer();
//                String name = applicationPlayer.getName();
//                GameManager.sendEvent(new VoicesEvent(0,name));
//            });
//            shoutButton.setOnMousePressed((e)->{
//                Player applicationPlayer = GameManager.getApplicationPlayer();
//                String name = applicationPlayer.getName();
//                GameManager.sendEvent(new VoicesEvent(1,name));
//            });
//        } catch (Exception e) {
//
//        }
//    }
//
//    public void setLayout(double x, double y) {
//        rootNode.setTranslateX(x);
//        rootNode.setTranslateY(y);
//    }
//}
