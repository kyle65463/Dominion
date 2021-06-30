package dominion.models.player;

import dominion.controllers.components.ActionBarController;
import dominion.models.HasUi;
import javafx.event.EventHandler;

public class ActionBar implements HasUi {
    // Constructor
    public ActionBar() {
    }

    // Variables
    private boolean isEnableUi = false;
    private ActionBarController uiController;

    // Functions
    public void enableUi() {
        this.uiController = new ActionBarController();
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

    public void setRightButtonText(String text) {
        if(isEnableUi) {
            uiController.setRightButtonText(text);
        }
    }

    public void setLeftButtonText(String text) {
        if(isEnableUi) {
            uiController.setLeftButtonText(text);
        }
    }

    public void setRightButtonOnPressed(EventHandler eventHandler) {
        if(isEnableUi) {
            uiController.setRightButtonOnPressed(eventHandler);
        }
    }

    public void setLeftButtonOnPressed(EventHandler eventHandler) {
        if (isEnableUi) {
            uiController.setLeftButtonOnPressed(eventHandler);
        }
    }

    public void enableRightButton(boolean b) {
        if (isEnableUi) {
            uiController.enableRightButton(b);
        }
    }

    public void enableLeftButton(boolean b) {
        if (isEnableUi) {
            uiController.enableLeftButton(b);
        }
    }

    public String getStatus() {
        if(isEnableUi) {
            return uiController.getStatus();
        }
        return "";
    }

    public String getRightButtonText() {
        if(isEnableUi) {
            return uiController.getRightButtonText();
        }
        return "";
    }

    public EventHandler getRightButtonOnPressed() {
        if(isEnableUi) {
            return uiController.getRightButtonOnPressed();
        }
        return (e)->{};
    }
}
