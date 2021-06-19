package dominion.controllers.components;

import dominion.models.game.Card;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;

public class CardController extends ComponentController{
    // Constructor
    public CardController() {
        initialize();
    }

    // Variables
    public static final double width = 120;
    public static final double height = 190;
    private StackPane remainBox;
    private StackPane valueBox;
    private StackPane costBox;
    private Label nameLabel;
    private Label costLabel;
    private Label remainLabel;
    private Label typesLabel;
    private Label valueLabel;

    // Functions
    public void setTypesLabel(String type) {
        typesLabel.setText(type);
        String style = "-fx-border-color: black;-fx-border-width: 3;";
        if (type.contains("錢幣")) {
            style += "-fx-background-color: lemonChiffon";
        }
        else if (type.contains("分數"))
        {
            style += "-fx-background-color: paleGreen";
        }
        else if (type.contains("行動")){
            style += "-fx-background-color: white";
        }
        rootNode.setStyle(style);
    }

    public void setScale(double scale) {
        rootNode.setScaleX(scale);
        rootNode.setScaleY(scale);
    }

    public void setLayout(double x, double y) {
        rootNode.setTranslateX(x);
        rootNode.setTranslateY(y);
    }

    public void setLayoutCenterX(double x, double y) {
        rootNode.setTranslateX(x - width / 2);
        rootNode.setTranslateY(y);
    }

    public void setCostLabel(int score) {
        costLabel.setText(String.valueOf(score));
    }

    public void setValueLabel(int value) {
        valueLabel.setText(String.valueOf(value));
    }

    public void setNameLabel(String name) {
        nameLabel.setText(name);
    }

    public void setRemainLabel(int numRemained) {
        if(numRemained > 0) {
            remainBox.setVisible(true);
        }
        else {
            remainBox.setVisible(false);
        }
        remainLabel.setText(String.valueOf(numRemained));
    }

    public void flipToBack() {
        String style = "-fx-border-color: black;-fx-border-width: 3;-fx-background-color: tan";
        rootNode.setStyle(style);
        nameLabel.setVisible(false);
        valueBox.setVisible(false);
        costBox.setVisible(false);
        typesLabel.setVisible(false);
    }

    public void flipToFront() {
        nameLabel.setVisible(true);
        valueBox.setVisible(true);
        costBox.setVisible(true);
        typesLabel.setVisible(true);
        setTypesLabel(typesLabel.getText());
    }

    public void enableRemainBox(Boolean enable) {
        remainBox.setDisable(!enable);
        remainBox.setVisible(enable);
    }

    public void enableValueBox(Boolean enable) {
        valueBox.setDisable(!enable);
        valueBox.setVisible(enable);
    }

    private void initialize() {
        try {
            rootNode = (Pane) FXMLLoader.load(Card.class.getClassLoader().getResource("resources/components/card.fxml"));
            nameLabel = (Label) rootNode.lookup("#name");
            typesLabel = (Label) rootNode.lookup("#types");
            costLabel = (Label) rootNode.lookup("#cost");
            remainLabel = (Label) rootNode.lookup("#remain");
            valueLabel = (Label) rootNode.lookup("#value");
            remainBox = (StackPane) rootNode.lookup("#remain_box");
            valueBox = (StackPane) rootNode.lookup("#value_box");
            costBox = (StackPane) rootNode.lookup("#cost_box");

            setNameLabel("銅幣");
            setTypesLabel("錢幣卡");
            setRemainLabel(1);
            setCostLabel(3);
            setValueLabel(1);
        } catch (Exception e) {

        }
    }
}
