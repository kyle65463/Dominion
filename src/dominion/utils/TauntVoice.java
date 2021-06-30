package dominion.utils;

import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;

import java.io.File;
import java.util.ArrayList;

public class TauntVoice {
    public static File directory;
    public static File[] files;
    public static ArrayList<File> taunts;
    public static Media media;
    public static MediaPlayer mediaPlayer;
    public static int index = 0;

    public static void initialize() {
        try {
            taunts = new ArrayList<File>();
            directory = new File("src/voice/taunt");
            files = directory.listFiles();
            if (files != null) {
                for (File file : files) {
                    taunts.add(file);
                }
            }
        } catch (Exception e) {
        }
    }

    public static void playTauntVoice() {
        media = new Media(taunts.get(index).toURI().toString());
        index = (index + 1) % 3;
        mediaPlayer = new MediaPlayer(media);
        mediaPlayer.play();
        System.out.println("play");
    }
}
