package dominion.models;

import java.io.Serializable;

public class User implements Serializable {
    // Constructor
    public User(int id, String name) {
        this.name = name;
        this.id = id;
        exist = true;
    }

    // Variables
    private String name;
    private int id;
    private boolean exist;
    // Functions
    public String getName() {
        return name;
    }
    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
    public void setName(String name) {
        this.name = name;
    }
    public void leave(){exist = false;}
    public boolean isExist(){return exist;}
}
