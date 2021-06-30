package dominion.controllers.components;

import dominion.connections.Connection;
import dominion.connections.Server;
import dominion.models.User;

public class CheckLeaveUserController {
    private static boolean server = false;
    public static void setBoolean(){server = true;}
    public static boolean isServer(){return server;}
}
