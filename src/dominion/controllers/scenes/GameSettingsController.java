package dominion.controllers.scenes;

import dominion.connections.Connection;
import dominion.models.User;
import dominion.models.cards.CardList;
import dominion.models.player.DisplayedCard;
import dominion.params.GameSettingsSceneParams;
import dominion.params.RoomSceneParams;
import dominion.params.SceneParams;
import dominion.utils.Navigator;
import javafx.fxml.FXML;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.*;
import javafx.stage.Stage;

import java.util.ArrayList;
import java.util.List;

public class GameSettingsController extends SceneController {
    // FXML
    @FXML
    private ScrollPane scrollPane;
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
    private List<Integer> allEnabledCardIds;

    // Functions
    public void initialize(Stage stage, SceneParams sceneParams) {
        // Unpack parameters
        GameSettingsSceneParams params = (GameSettingsSceneParams) sceneParams;
        this.stage = stage;
        this.applicationUser = params.applicationUser;
        this.users = params.users;
        this.connection = params.connection;
        this.basicCardIds = params.basicCardIds;
        this.allEnabledCardIds = params.allEnabledCardIds;
        allEnabledCardIds.clear();

        List<DisplayedCard> dominionCards = new ArrayList<>(CardList.getDominionCardList().stream().map(DisplayedCard::new).toList());
        dominionCards.sort((a, b) -> b.getCard().getNumCost() - a.getCard().getNumCost());
        int numCols = 5;
        int numRows = (int) Math.ceil(dominionCards.size() / (double) numCols);
        gridPane.getRowConstraints().clear();
        for (int i = 0; i < numRows; i++) {
            gridPane.getRowConstraints().add(new RowConstraints(70));
        }
        gridPane.getColumnConstraints().clear();
        for (int i = 0; i < numCols; i++) {
            gridPane.getColumnConstraints().add(new ColumnConstraints(84));
        }
        for (int i = 0; i < dominionCards.size(); i++) {
            DisplayedCard displayedCard = dominionCards.get(i);
            displayedCard.getController().setScale(0.7);
            gridPane.add(displayedCard.getController().getRootNode(), i % numCols, i / numCols);
            allEnabledCardIds.add(CardList.getCardId(displayedCard.getCard()));
        }
    }

    public void confirm() {
        navigateToRoomScene();
    }

    public void navigateToRoomScene() {
        RoomSceneParams parameters = new RoomSceneParams(
                applicationUser, users, connection,
                basicCardIds, allEnabledCardIds
        );
        Navigator.to(stage, "resources/scenes/room.fxml", parameters);
    }
}
