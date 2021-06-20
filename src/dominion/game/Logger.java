package dominion.game;

import dominion.models.game.Player;
import dominion.models.game.cards.Card;
import dominion.models.game.cards.treasures.Treasure;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;

public class Logger {
    // Constructor
    public static void initialize(VBox box) {
        Logger.box = box;
    }

    // Variables
    private static VBox box;

    // Functions
    public static void addMessage(String message) {
        Label label = new Label();
        label.setText(message);
        label.setFont(new Font(16));
        box.getChildren().add(label);
    }

    public static void logDrawCard(Player player, int numDrew) {
        addMessage(player.getName() + " 抽了 " + String.valueOf(numDrew) + " 張牌");
    }

    public static void logPlayCard(Player player, Card card) {
        if(!(card instanceof Treasure)) {
            addMessage(player.getName() + " 打出了 " + card.getName());
        }
    }

    public static void logTrashCard(Player player, Card card) {
        addMessage(player.getName() + " 移除了 " + card.getName());
    }

    public static void logBuyCard(Player player, Card card) {
        addMessage(player.getName() + " 購買了 " + card.getName());
    }

//    public static void logSelectCard(Player player, Card card) {
//        addMessage(player.getName() + " 選擇了 " + card.getName());
//    }

//    public static void logDiscardCard(Player player, Card card) {
//        addMessage(player.getName() + " 丟棄了 " + card.getName());
//    }

    public static void logStartTurn(Player player) {
        addMessage(player.getName() + "  的回合");
    }

    public static void logEndTurn() {
        addMessage("結束回合");
        addMessage("");
    }
}
