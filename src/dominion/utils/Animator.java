package dominion.utils;

import dominion.controllers.components.ComponentController;
import javafx.animation.ScaleTransition;
import javafx.animation.TranslateTransition;
import javafx.scene.Node;
import javafx.util.Duration;


public class Animator {
    // Variables
    private static double defaultScale = 1;
    private static Duration defaultDuration = Duration.millis(200);

    // Functions
    public static void transitTo(ComponentController controller, double x, double y) {
        transitTo(controller, x, y, defaultScale, defaultDuration);
    }

    public static void transitTo(ComponentController controller, double x, double y, Duration duration) {
        transitTo(controller, x, y, defaultScale, duration);
    }

    public static void transitTo(ComponentController controller, double x, double y, double scale) {
        transitTo(controller, x, y, scale, defaultDuration);
    }

    public static void transitTo(ComponentController controller, double x, double y, double scale, Duration duration) {
        Node node = controller.getRootNode();
        // Scale transition
        ScaleTransition scaleTransition = new ScaleTransition();
        scaleTransition.setNode(node);
        scaleTransition.setToX(scale);
        scaleTransition.setToY(scale);
        scaleTransition.setDuration(duration);

        // Translate transition
        TranslateTransition translateTransition = new TranslateTransition();
        translateTransition.setNode(node);
        translateTransition.setToX(x);
        translateTransition.setToY(y);
        translateTransition.setDuration(duration);

        // Play animations
        scaleTransition.play();
        translateTransition.play();
    }
}
