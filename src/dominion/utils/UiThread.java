package dominion.utils;

import dominion.models.handlers.UiTask;
import javafx.application.Platform;

public class UiThread {
    // Functions
    public static void run(UiTask task) {
        Platform.runLater(()->{
            task.run();
        });
    }
}
