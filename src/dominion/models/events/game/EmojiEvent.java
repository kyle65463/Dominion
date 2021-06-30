package dominion.models.events.game;

import dominion.controllers.components.AngryFaceController;
import dominion.game.Logger;
import dominion.models.game.GameScene;
import javafx.animation.PauseTransition;
import javafx.util.Duration;

import java.util.Timer;
import java.util.TimerTask;
public class EmojiEvent extends InterActiveEvent{
    public EmojiEvent(String playerName){name = playerName;}
    private String name;
    @Override
    public void perform() {
        AngryFaceController afc = new AngryFaceController();
        afc.setName(name);
        GameScene.add(afc);
        Logger.addMessage(name + " 生氣了");
        PauseTransition deleteEmoji = new PauseTransition(Duration.seconds(2));
        deleteEmoji.setOnFinished(e->GameScene.delete(afc));
        deleteEmoji.play();
    }
}