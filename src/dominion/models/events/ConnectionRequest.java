package dominion.models.events;

import dominion.models.User;

public class ConnectionRequest extends NetworkEventAction {
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