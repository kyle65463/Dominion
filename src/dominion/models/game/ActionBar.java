package dominion.models.game;

import dominion.controllers.components.ActionBarController;
import dominion.models.game.GameScene;
import javafx.event.EventHandler;

import java.io.Serializable;

public class ActionBar implements HasUi{
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

    public void setButtonOnPressed(EventHandler eventHandler) {
        if(isEnableUi) {
            uiController.setButtonOnPressed(eventHandler);
        }
    }

    public void setAutoTreasureOnPressed(EventHandler eventHandler) {
        if (isEnableUi) {
            uiController.setAutoTreasureOnPressed(eventHandler);
        }
    }

    public void setAutoTreasure(boolean b) {
        if (isEnableUi) {
            uiController.setAutoTreasure(b);
        }
    }

    public String getStatus() {
        if(isEnableUi) {
            return uiController.getStatus();
        }
        return "";
    }

    public String getButtonText() {
        if(isEnableUi) {
            return uiController.getButtonText();
        }
        return "";
    }

    public EventHandler getButtonOnPressed() {
        if(isEnableUi) {
            return uiController.getButtonOnPressed();
        }
        return (e)->{};
    }
}
