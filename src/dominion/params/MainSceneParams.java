package dominion.params;

public class MainSceneParams extends SceneParams {
    // Constructor
    public MainSceneParams(String defaultName, String defaultIp, String defaultPort){
        this.defaultName = defaultName;
        this.defaultPort = defaultPort;
        this.defaultIp = defaultIp;

    }

    // Variables
    public final String defaultName;
    public final String defaultPort;
    public final String defaultIp;

}
