package dominion.controllers.components;

import dominion.models.User;

import java.util.ArrayList;
import java.util.List;

public class UsrExistController {
    public static List<User> users = new ArrayList<>();
    public static void setUsers(List<User> us){users = us;}
}
