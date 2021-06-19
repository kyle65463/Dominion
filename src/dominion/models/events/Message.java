package dominion.models.events;

public class Message extends EventAction {
    // Constructor
    public Message(String username, String content) {
        this.username = username;
        this.content = content;
    }

    // Variables
    private String username;
    private String content;

    // Functions
    public String getContent() { return content; }

    public String getUsername() {
        return username;
    }


}
