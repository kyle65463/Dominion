package dominion.models.events.game;

import dominion.utils.ShoutVoice;


public class ShoutEvent extends GameEvent{
    public ShoutEvent(int playerId){super(playerId);}
    @Override
    public void perform() {
        ShoutVoice.playShoutVoice();
    }
}
