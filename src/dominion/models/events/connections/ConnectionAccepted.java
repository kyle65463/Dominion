package dominion.models.events.connections;

import dominion.models.User;

import java.util.List;

public class ConnectionAccepted extends ConnectionEvent {
    // Constructor
    public ConnectionAccepted(User acceptedUser, List<User> users) {
        this.acceptedUser = acceptedUser;
        this.users = users;
    }

    // Variables
    private User acceptedUser;
    private List<User> users;

    // Functions
    public User getAcceptedUser() {
        return acceptedUser;
    }
    public List<User> getUsers() {
        return users;
    }
}
