package dominion.models.game;

import dominion.controllers.components.ActionBarController;
import dominion.models.game.GameScene;

public class ActionBar {
    // Constructor
    public ActionBar(GameScene gameScene) {
        this.uiController = new ActionBarController(gameScene);
    }

    // Variables
    private ActionBarController uiController;

    // Functions
    public void setNumCoins(int numCoins) {
        uiController.setNumCoins(numCoins);
    }

    public void setNumActions(int numActions) {
        uiController.setNumActions(numActions);
    }

    public void setNumPurchases(int numPurchases) {
        uiController.setNumPurchases(numPurchases);
    }
}
