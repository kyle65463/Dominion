package dominion.component;

import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;

public class ActionBar {
    public ActionBar() {
        initialize();
    }

    private Pane pane;
    private int numActions;
    private int numPurchases;
    private int numCoins;
    private String status;
    private String buttonText;
    private Button button;
    private Label statusLabel;
    private Label purchasesLabel;
    private Label actionsLabel;
    private Label coinsLabel;


    public Node getNode() {
        return pane;
    }

    public void setNumActions(int numActions) {
        this.numActions = numActions;
        actionsLabel.setText(String.valueOf(numActions) + "行動");
    }

    public void setNumPurchases(int numPurchases) {
        this.numPurchases = numPurchases;
        purchasesLabel.setText(String.valueOf(numPurchases) + "購買");
    }

    public void setNumCoins(int numCoins) {
        this.numCoins = numCoins;
        coinsLabel.setText(String.valueOf(numCoins));
    }

    public void setButtonText(String buttonText) {
        this.buttonText = buttonText;
        button.setText(buttonText);
    }

    public void setStatus(String status) {
        this.status = status;
        statusLabel.setText(status);
    }

    public void setActionButtonOnPressed(EventHandler handler) {
        button.setOnMousePressed(handler);
    }

    private void initialize() {
        try {
            pane = (Pane) FXMLLoader.load(KingdomCard.class.getClassLoader().getResource("resources/component/action_bar.fxml"));
            for (Node node : pane.getChildren()) {
                if (node instanceof Label) {
                    statusLabel = (Label) node;
                } else if (node instanceof HBox) {
                    for (Node child : ((HBox) node).getChildren()) {
                        if (child instanceof Label) {
                            if (actionsLabel == null) {
                                actionsLabel = (Label) child;
                            } else if (purchasesLabel == null) {
                                purchasesLabel = (Label) child;
                            }
                        } else if (child instanceof StackPane) {
                            for (Node c : ((StackPane) child).getChildren()) {
                                if (c instanceof Label) {
                                    coinsLabel = (Label) c;
                                }
                            }
                        }
                    }
                } else if (node instanceof Button) {
                    button = (Button) node;
                }
            }

            button.setOnMousePressed(
                    e -> {
//                        System.out.println(e);
                    }
            );
            setNumActions(1);
            setNumPurchases(1);
            setNumCoins(3);
            setStatus("你可以購買卡片");
            setButtonText("結束購買");
        } catch (Exception e) {

        }
    }
}
