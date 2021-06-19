package dominion.models.game;

import dominion.controllers.components.ActionBarController;
import dominion.models.game.GameScene;

public class ActionBar implements HasUi {
    // Constructor
    public ActionBar() {
    }

    // Variables
    private boolean isEnableUi = false;
    private ActionBarController uiController;

    // Functions
    public void enableUi(GameScene gameScene) {
        this.uiController = new ActionBarController(gameScene);
        isEnableUi = true;
    }

    public void setNumCoins(int numCoins) {
        if(isEnableUi) {
            uiController.setNumCoins(numCoins);
        }
    }

    public void setNumActions(int numActions) {
        if(isEnableUi) {
            uiController.setNumActions(numActions);
        }
    }

    public void setNumPurchases(int numPurchases) {
        if(isEnableUi) {
            uiController.setNumPurchases(numPurchases);
        }
    }

    public void setStatus(String status) {
        if(isEnableUi) {
            uiController.setStatus(status);
        }
    }

    public void setButtonText(String text) {
        if(isEnableUi) {
            uiController.setButtonText(text);
        }
    }
}
