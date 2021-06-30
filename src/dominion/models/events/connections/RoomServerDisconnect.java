package dominion.models.events.connections;

import dominion.connections.Connection;
import dominion.controllers.components.CheckLeaveUserController;
import dominion.controllers.components.LeaveController;
import dominion.models.User;

public class RoomServerDisconnect extends LeaveEvent{
    public RoomServerDisconnect(){}
    @Override
    public void perform(){
        LeaveController.setEvent(true);
        CheckLeaveUserController.setBoolean();
    }
}
