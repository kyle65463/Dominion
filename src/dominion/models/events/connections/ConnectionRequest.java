package dominion.models.events.connections;

import dominion.models.User;

public class ConnectionRequest extends ConnectionEvent {
    // Constructor
    public ConnectionRequest(User requestedUser) {
        this.requestedUser = requestedUser ;
    }

    // Variables
    private User requestedUser;

    // Functions
    public User getRequestedUser() {
        return requestedUser;
    }
}