package dominion.models.events.game;


import dominion.utils.Voice;

public class VoicesEvent extends VoiceEvent{
    public VoicesEvent(int sel){selection = sel;}
    private int selection;
    @Override
    public void perform() {
        Voice.playVoice(selection);}
}
