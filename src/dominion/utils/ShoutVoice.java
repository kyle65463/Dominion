package dominion.utils;

import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;

import java.io.File;
import java.util.ArrayList;

public class ShoutVoice {
    public static File directory;
    public static File[] files;
    public static ArrayList<File> shouts;
    public static Media media;
    public static MediaPlayer mediaPlayer;
    public static int index = 0;

    public static void initialize() {
        try {
            shouts = new ArrayList<File>();
            directory = new File("src/voice/shout");
            files = directory.listFiles();
            if (files != null) {
                for (File file : files) {
                    shouts.add(file);
                }
            }
        } catch (Exception e) {
        }
    }

    public static void playShoutVoice() {
        media = new Media(shouts.get(index).toURI().toString());
        index = (index + 1) % 3;
        mediaPlayer = new MediaPlayer(media);
        mediaPlayer.play();
        System.out.println("play");
    }
}
