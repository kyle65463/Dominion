package dominion.models.events.game;

import dominion.controllers.components.emoji.AngryFaceController;
import dominion.models.areas.GameScene;
import dominion.models.areas.LogBox;
import javafx.animation.PauseTransition;
import javafx.util.Duration;

public class EmojiEvent extends InterActiveEvent{
    public EmojiEvent(String playerName){name = playerName;}
    private String name;
    @Override
    public void perform() {
        AngryFaceController afc = new AngryFaceController();
        afc.setName(name);
        GameScene.add(afc);
        LogBox.addMessage(name + " 生氣了");
        PauseTransition deleteEmoji = new PauseTransition(Duration.seconds(2));
        deleteEmoji.setOnFinished(e->GameScene.delete(afc));
        deleteEmoji.play();
    }
}
