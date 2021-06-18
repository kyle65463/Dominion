package dominion.component;

import com.sun.jdi.Value;
import javafx.fxml.FXMLLoader;

import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;

public class Card {
    public Card() {
        initialize();
    }

    protected static final double originalWidth = 120;
    protected static final double originalHeight = 190;
    private double width = 120;
    private double height = 190;
    private Pane node;
    private StackPane remainBox;
    private StackPane valueBox;
    private Label nameLabel;
    private Label costLabel;
    private Label remainLabel;
    private Label typesLabel;
    private Label valueLabel;

    public Node getNode() {
        return node;
    }

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
        node.setStyle(style);
    }

    public double getWidth() {
        return width;
    }

    public double getHeight() {
        return width;
    }

    public void setScale(double scale) {
        node.setScaleX(scale);
        node.setScaleY(scale);
        width = originalWidth * scale;
        height = originalHeight * scale;
    }

    public void setLayout(double x, double y) {
        node.setTranslateX(x);
        node.setTranslateY(y);
    }

    public void setLayoutCenterX(double x, double y) {
        node.setTranslateX(x - width / 2);
        node.setTranslateY(y);
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
        remainLabel.setText(String.valueOf(numRemained));
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
            node = (Pane) FXMLLoader.load(Card.class.getClassLoader().getResource("resources/component/card.fxml"));
            nameLabel = (Label) node.lookup("#name");
            typesLabel = (Label) node.lookup("#types");
            costLabel = (Label) node.lookup("#cost");
            remainLabel = (Label) node.lookup("#remain");
            valueLabel = (Label) node.lookup("#value");
            remainBox = (StackPane) node.lookup("#remain_box");
            valueBox = (StackPane) node.lookup("#value_box");


            setNameLabel("銅幣");
            setTypesLabel("錢幣卡");
            setRemainLabel(1);
            setCostLabel(3);
            setValueLabel(1);
        } catch (Exception e) {

        }
    }
}
