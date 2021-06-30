package dominion.models.areas;

import javafx.scene.control.Label;
import javafx.scene.layout.Pane;

public class WinnerDialog {
    public static void initialize(Pane winnerBox, Label winnerLabel) {
        WinnerDialog.winnerBox = winnerBox;
        WinnerDialog.winnerLabel = winnerLabel;
    }

    private static Pane winnerBox;
    private static Label winnerLabel;

    public static void setWinner(String name) {
        winnerBox.setVisible(true);
        winnerLabel.setText(name);
    }
}
