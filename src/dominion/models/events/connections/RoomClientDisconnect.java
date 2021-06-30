package dominion.models.events.connections;

import dominion.connections.Connection;
import dominion.controllers.components.CheckLeaveUserController;
import dominion.controllers.components.LeaveController;
import dominion.controllers.components.ReturnRoomController;
import dominion.controllers.components.UsrExistController;
import dominion.models.User;

public class RoomClientDisconnect extends LeaveEvent{
    public RoomClientDisconnect(int i){id = i;}
    private int id;
    @Override
    public void perform(){
        LeaveController.setEvent(true);
        for(int i = 0;i < UsrExistController.users.size();i++){
            if(UsrExistController.users.get(i).getId() == id){
                UsrExistController.users.get(i).leave();
            }
        }
        LeaveController.setEvent(false);
        ReturnRoomController.navigateToRoomScene();
    }
}