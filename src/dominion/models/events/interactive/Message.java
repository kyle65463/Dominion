package dominion.models.events.interactive;

public class Message extends InteractiveEvent {
    // Constructor
    public Message(String username, String content) {
        this.username = username;
        this.content = content;
    }

    // Variables
    private final String username;
    private final String content;

    // Functions
    public String getContent() { return content; }

    public String getUsername() {
        return username;
    }

    @Override
    public void perform() {

    }
}
