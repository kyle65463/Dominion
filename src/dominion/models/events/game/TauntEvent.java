package dominion.models.events.game;


import dominion.utils.TauntVoice;

public class TauntEvent extends GameEvent {
    public TauntEvent(int playerId){
        super(playerId);
    }
    @Override
    public void perform() {
        TauntVoice.playTauntVoice();
    }
}
