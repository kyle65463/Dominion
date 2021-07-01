package dominion.controllers.scenes;

import dominion.connections.Connection;
import dominion.models.User;
import dominion.models.areas.DisplayedCard;
import dominion.models.areas.GameScene;
import dominion.models.cards.Card;
import dominion.models.cards.CardFactory;
import dominion.models.expansions.*;
import dominion.params.GameSettingsSceneParams;
import dominion.params.RoomSceneParams;
import dominion.params.SceneParams;
import dominion.utils.Navigator;
import javafx.fxml.FXML;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.layout.*;
import javafx.scene.shape.Line;
import javafx.scene.text.Font;
import javafx.stage.Stage;

import java.util.*;

public class GameSettingsController extends SceneController {
    // FXML
    @FXML
    private AnchorPane rootNode;
    @FXML
    private GridPane gridPane;
    @FXML
    private VBox collectionList;

    // Variables
    private Stage stage;
    private User applicationUser;
    private List<User> users;
    private Connection connection;
    private List<Integer> basicCardIds;
    private Set<Integer> allEnabledCardIds;
    private final Map<Class<? extends Expansion>, List<DisplayedCard>> expansionDisplayedCardMap = new HashMap<>();
    private final List<Label> expansionTabs = new ArrayList<>();
    private final String tabSelectedStyle = "-fx-text-fill: Sienna;";
    private final List<DisplayedCard> displayedCards = new ArrayList<>();

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

        // Set up variables
        List<Class<? extends Expansion>> expansions = CardFactory.getExpansions();
        for (Class<? extends Expansion> expansion : expansions) {
            List<Card> expansionCards = CardFactory.getCardsOfExpansion(expansion);
            expansionDisplayedCardMap.put(expansion, new ArrayList<>(expansionCards.stream().map(DisplayedCard::new).toList()));
            addNewTab(expansion);
        }

        // Remove last divider line
        collectionList.getChildren().remove(collectionList.getChildren().size() - 1);

        // Display default expansion
        Class<? extends Expansion> defaultExpansion = CardFactory.getDefaultExpansion();
        displayExpansionCards(defaultExpansion);
        if(expansionTabs.size() > 0) {
            expansionTabs.get(0).setStyle(tabSelectedStyle);
        }
    }

    private void displayExpansionCards(Class<? extends Expansion> expansion) {
        List<DisplayedCard> cards = expansionDisplayedCardMap.get(expansion);
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
        displayedCards.clear();
        for (int i = 0; i < cards.size(); i++) {
            DisplayedCard displayedCard = cards.get(i);
            Integer cardId = CardFactory.getCardId(displayedCard.getCard());
            if (!allEnabledCardIds.contains(cardId)) {
                displayedCard.setDisable(true);
            }
            displayedCard.getController().setScale(0.7);
            displayedCard.setOnPressed((card) -> {
                boolean isDisable = !card.getDisable();
                System.out.println(card);
                if (isDisable) {
                    allEnabledCardIds.remove(cardId);
                } else {
                    allEnabledCardIds.add(cardId);
                }
                card.setDisable(isDisable);
            });
            gridPane.add(displayedCard.getController().getRootNode(), i % numCols, i / numCols);
            displayedCards.add(displayedCard);
        }
    }

    private void addNewTab(Class<? extends Expansion> expansion) {
        if (expansion == Basic.class)
            return;
        String name = CardFactory.getNameOfExpansion(expansion);
        Label tab = new Label(name);
        tab.setFont(new Font(16));
        tab.setAlignment(Pos.CENTER);
        tab.setPrefHeight(40);
        tab.setPrefWidth(80);
        tab.setOnMouseClicked((e) -> {
            for (Label expansionTab : expansionTabs) {
                expansionTab.setStyle("");
            }
            tab.setStyle(tabSelectedStyle);
            displayExpansionCards(expansion);
        });
        expansionTabs.add(tab);
        collectionList.getChildren().add(tab);

        // Divider line
        Line line = new Line(-47.5, 35, 31.5, 35);
        collectionList.getChildren().add(line);
    }

    public void confirm() {
        navigateToRoomScene();
    }

    public void selectAll() {
        for(DisplayedCard displayedCard : displayedCards){
            if(displayedCard.getDisable()){
                displayedCard.fireOnPressed();
            }
        }
    }

    public void clearAll(){
        for(DisplayedCard displayedCard : displayedCards){
            if(!displayedCard.getDisable()){
                displayedCard.fireOnPressed();
            }
        }
    }

    public void navigateToRoomScene() {
        RoomSceneParams parameters = new RoomSceneParams(
                applicationUser, users, connection,
                basicCardIds, new ArrayList<>(allEnabledCardIds)
        );
        Navigator.to(stage, "resources/scenes/room.fxml", parameters);
    }
}
