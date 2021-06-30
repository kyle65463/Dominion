package dominion.controllers.components;

import dominion.models.areas.GameScene;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.control.Label;

public class ActionBarController extends ComponentController{
    // Constructor
    public ActionBarController() {
        initialize();
    }

    // Variables
    private Button rightButton;
    private Button leftButton;
    private Label statusLabel;
    private Label purchasesLabel;
    private Label actionsLabel;
    private Label coinsLabel;
    private final double x = 235;
    private final double y = 520;

    // Functions
    public void setNumActions(int numActions) {
        actionsLabel.setText(String.valueOf(numActions) + " 行動");
    }

    public void setNumPurchases(int numPurchases) {
        purchasesLabel.setText(String.valueOf(numPurchases) + " 購買");
    }

    public void setNumCoins(int numCoins) {
        coinsLabel.setText(String.valueOf(numCoins));
    }

    public void setRightButtonText(String buttonText) {
        rightButton.setText(buttonText);
        rightButton.setVisible(!buttonText.isEmpty());
    }

    public void setLeftButtonText(String buttonText) {
        leftButton.setText(buttonText);
        leftButton.setVisible(!buttonText.isEmpty());
    }

    public void setStatus(String status) {
        statusLabel.setText(status);
    }

    public void setRightButtonOnPressed(EventHandler handler) {
        rightButton.setOnMousePressed(handler);
    }

    public void setLeftButtonOnPressed(EventHandler handler) { leftButton.setOnMousePressed(handler); }

    private void initialize() {
        try {
            rootNode = FXMLLoader.load(ActionBarController.class.getClassLoader().getResource("resources/components/action_bar.fxml"));
            actionsLabel = (Label) rootNode.lookup("#num_actions");
            purchasesLabel = (Label) rootNode.lookup("#num_purchases");
            coinsLabel = (Label) rootNode.lookup("#num_coins");
            statusLabel = (Label) rootNode.lookup("#status");
            rightButton = (Button) rootNode.lookup("#button");
            leftButton = (Button) rootNode.lookup("#autoTreasure");

            setNumActions(1);
            setNumPurchases(1);
            setNumCoins(3);
            setStatus("你可以購買卡片");
            setRightButtonText("結束購買");
            enableLeftButton(false);
            enableRightButton(true);

            rootNode.setLayoutX(x);
            rootNode.setLayoutY(y);
            GameScene.add(this);
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    public void enableLeftButton(boolean b) {
        leftButton.setDisable(!b);
        leftButton.setVisible(b);
    }

    public void enableRightButton(boolean b) {
        rightButton.setDisable(!b);
        rightButton.setVisible(b);
    }

    public String getStatus() {
        return statusLabel.getText();
    }

    public String getRightButtonText() {
        return rightButton.getText();
    }

    public EventHandler getRightButtonOnPressed() {
        return rightButton.getOnMousePressed();
    }
}