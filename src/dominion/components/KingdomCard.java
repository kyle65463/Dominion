package dominion.components;

import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.layout.Pane;

public class KingdomCard {
    public KingdomCard() {
        try {
            pane = (Pane) FXMLLoader.load(KingdomCard.class.getClassLoader().getResource("resources/component/kingdom_card_small.fxml"));

        } catch (Exception e) {

        }
    }

    private Pane pane;

    public void setScale(double scale) {
        pane.setScaleX(scale);
        pane.setScaleY(scale);
    }

    public void setPadding(double top, double right, double bottom, double left) {
        pane.setPadding(new Insets(top, right, bottom, left));
    }

    public Node getNode() {
        return pane;
    }
}
