package dominion.models.events.interactive;

import dominion.models.areas.LogBox;
import dominion.utils.VoicePlayer;

public class VoicesEvent extends InteractiveEvent {
    public VoicesEvent(int sel,String playername){selection = sel;name = playername;}
    private int selection;
    private String name;
    @Override
    public void perform() {
        VoicePlayer.playVoice(selection);
        if(selection == 0)
            LogBox.addMessage(name + " 發出了嘲諷");
        else
            LogBox.addMessage(name + " 發出了慘叫");
    }
}
