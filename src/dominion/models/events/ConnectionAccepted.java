package dominion.models.events;

import dominion.models.User;

import java.util.List;

public class ConnectionAccepted extends NetworkEventAction {
    // Constructor
    public ConnectionAccepted(User acceptedUser, List<User> users) {
        super("accepted");
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