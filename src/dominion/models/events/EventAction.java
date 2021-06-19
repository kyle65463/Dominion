package dominion.models.events;

import java.io.Serializable;

public abstract class EventAction implements Serializable{
    public EventAction(String username, String str) {
        this.username = username;
        this.content = str;
    }

    public String getContent() { return content; }

    public String getUsername() {
        return username;
    }

    private String username;
    private String content;
}
