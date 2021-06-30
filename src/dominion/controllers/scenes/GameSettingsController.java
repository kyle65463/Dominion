package dominion.controllers.scenes;

import dominion.models.cards.CardList;
import dominion.models.player.DisplayedCard;
import dominion.params.GameSettingsSceneParams;
import dominion.params.SceneParams;
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

    // Functions
    public void initialize(Stage stage, SceneParams sceneParams) {
        GameSettingsSceneParams parameters = (GameSettingsSceneParams) sceneParams;
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
        }
    }
}
