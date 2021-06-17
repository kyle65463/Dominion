package dominion.model.action;

import dominion.model.User;

public class ConnectionRequest extends NetworkAction{
    // Constructor
    public ConnectionRequest(User requestedUser ) {
        super("request");
        this.requestedUser = requestedUser ;
    }

    // Variables
    private User requestedUser;

    // Functions
    public User getRequestedUser() {
        return requestedUser;
    }
}