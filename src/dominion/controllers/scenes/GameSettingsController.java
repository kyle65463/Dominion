package dominion.controllers.scenes;

import dominion.connections.Connection;
import dominion.models.User;
import dominion.models.areas.DisplayedCard;
import dominion.models.areas.GameScene;
import dominion.models.cards.CardFactory;
import dominion.params.GameSettingsSceneParams;
import dominion.params.RoomSceneParams;
import dominion.params.SceneParams;
import dominion.utils.Navigator;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.*;
import javafx.stage.Stage;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class GameSettingsController extends SceneController {
    // FXML
    @FXML
    private AnchorPane rootNode;
    @FXML
    private ScrollPane scrollPane;
    @FXML
    private GridPane gridPane;
    @FXML
    private VBox collectionList;
    @FXML
    private Label dominionTab;
    @FXML
    private Label intrigueTab;
    @FXML
    private Label seaSideTab;

    // Variables
    private Stage stage;
    private User applicationUser;
    private List<User> users;
    private Connection connection;
    private List<Integer> basicCardIds;
    private Set<Integer> allEnabledCardIds;

    private List<DisplayedCard> dominionCards;
    private List<DisplayedCard> intrigueCards;
    private List<DisplayedCard> seaSideCards;


    // Functions
    public void initialize(Stage stage, SceneParams sceneParams) {
        // Unpack parameters
        GameSettingsSceneParams params = (GameSettingsSceneParams) sceneParams;
        this.stage = stage;
        this.applicationUser = params.applicationUser;
        this.users = params.users;
        this.connection = params.connection;
        this.basicCardIds = params.basicCardIds;
        this.allEnabledCardIds = new HashSet<>(params.allEnabledCardIds);
        GameScene.initialize(rootNode);

        dominionCards = new ArrayList<>(CardFactory.getDominionCardList().stream().map(DisplayedCard::new).toList());
        dominionCards.sort((a, b) -> b.getCard().getNumCost() - a.getCard().getNumCost());

        intrigueCards = new ArrayList<>(CardFactory.getIntrigueCardList().stream().map(DisplayedCard::new).toList());
        intrigueCards.sort((a, b) -> b.getCard().getNumCost() - a.getCard().getNumCost());

        seaSideCards = new ArrayList<>(CardFactory.getSeaSideCardList().stream().map(DisplayedCard::new).toList());
        seaSideCards.sort((a, b) -> b.getCard().getNumCost() - a.getCard().getNumCost());

        String style = "-fx-text-fill: Sienna;";
        dominionTab.setStyle(style);
        intrigueTab.setStyle("");
        seaSideTab.setStyle("");
        
        dominionTab.setOnMouseClicked((e) -> {
            dominionTab.setStyle(style);
            intrigueTab.setStyle("");
            seaSideTab.setStyle("");
            displayDominionCards();
        });

        intrigueTab.setOnMouseClicked((e) -> {
            intrigueTab.setStyle(style);
            dominionTab.setStyle("");
            seaSideTab.setStyle("");
            displayIntrigueCards();
        });

        seaSideTab.setOnMouseClicked((e) -> {
            seaSideTab.setStyle(style);
            intrigueTab.setStyle("");
            dominionTab.setStyle("");
            displaySeaSideCards();
        });

        displayDominionCards();
    }

    private void displayDominionCards() {
        display(dominionCards);
    }

    private void displayIntrigueCards() {
        display(intrigueCards);
    }

    private void displaySeaSideCards() {
        display(seaSideCards);
    }

    private void display(List<DisplayedCard> cards) {
        int numCols = Math.min(5, cards.size());
        int numRows = (int) Math.ceil(cards.size() / (double) numCols);
        gridPane.getChildren().clear();
        gridPane.getRowConstraints().clear();
        for (int i = 0; i < numRows; i++) {
            gridPane.getRowConstraints().add(new RowConstraints(70));
        }
        gridPane.getColumnConstraints().clear();
        for (int i = 0; i < numCols; i++) {
            gridPane.getColumnConstraints().add(new ColumnConstraints(84));
        }
        for (int i = 0; i < cards.size(); i++) {
            DisplayedCard displayedCard = cards.get(i);
            Integer cardId = CardFactory.getCardId(displayedCard.getCard());
            if(!allEnabledCardIds.contains(cardId)){
                displayedCard.setDisable(true);
            }
            displayedCard.getController().setScale(0.7);
            displayedCard.setOnPressed((card) -> {
                boolean isDisable = !card.getDisable();
                System.out.println(card);
                if(isDisable) {
                    allEnabledCardIds.remove(cardId);
                }
                else{
                    allEnabledCardIds.add(cardId);
                }
                card.setDisable(isDisable);
            });
            gridPane.add(displayedCard.getController().getRootNode(), i % numCols, i / numCols);
        }
    }

    public void confirm() {
        navigateToRoomScene();
    }

    public void navigateToRoomScene() {
        RoomSceneParams parameters = new RoomSceneParams(
                applicationUser, users, connection,
                basicCardIds, new ArrayList<>(allEnabledCardIds)
        );
        Navigator.to(stage, "resources/scenes/room.fxml", parameters);
    }
}
