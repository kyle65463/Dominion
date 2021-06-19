package dominion.models.events;

public class Message extends EventAction {
    public Message(String username, String str) {
    }

    public String getContent() { return content; }

    public String getUsername() {
        return username;
    }

    private String username;
    private String content;
}
