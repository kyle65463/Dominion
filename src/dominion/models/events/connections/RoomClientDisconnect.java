package dominion.models.events.connections;

import dominion.connections.Connection;
import dominion.controllers.components.*;
import dominion.controllers.scenes.RoomController;
import dominion.models.User;

import java.net.ServerSocket;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

public class RoomClientDisconnect extends LeaveEvent{
    public RoomClientDisconnect(List<Integer> id){ids = id;}
    private List<Integer> ids;
    @Override
    public void perform(){
//        System.out.println("hello");
//        int idx = 0;
//        if(ids.size() > 0){
//            Collections.sort(ids);
//            for(int i = 0;i < UsrExistController.users.size();i++){
//                if(UsrExistController.users.get(i).getId() == ids.get(idx)){
//                   UsrExistController.users.get(i).leave();
//                   if(idx + 1 < ids.size())
//                       idx++;
//                }
//            }
//        }
//        LeaveController.setEvent(false);
//        ReturnRoomController.navigateToRoomScene();
        ServerSocket server = ReturnMainController.getServerSocket();
        try{
        server.close();
        }catch(Exception e){}
        ReturnMainController.returnMain();
    }
}