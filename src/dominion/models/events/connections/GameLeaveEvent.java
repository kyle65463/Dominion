package dominion.models.events.connections;

public class GameLeaveEvent extends LeaveEvent{
    public GameLeaveEvent(){};
    @Override
    public void perform() {
        System.out.println("game event!!!");
    }
}
