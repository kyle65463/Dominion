package dominion.models.events.game;


import dominion.game.GameManager;
import dominion.game.Logger;
import dominion.models.game.Player;
import dominion.utils.Voice;

public class VoicesEvent extends InterActiveEvent{
    public VoicesEvent(int sel,String playername){selection = sel;name = playername;}
    private int selection;
    private String name;
    @Override
    public void perform() {
        Voice.playVoice(selection);
        if(selection == 0)
            Logger.addMessage(name + " 發出了嘲諷");
        else
            Logger.addMessage(name + " 發出了慘叫");
    }
}
