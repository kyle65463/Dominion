package dominion.utils;

import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;

import java.io.File;
import java.util.ArrayList;

public class Voice {
    public static File directory;
    public static File[] files;
    public static ArrayList<File> taunts;
    public static ArrayList<File> shouts;
    public static ArrayList<File> soundEffects;
    public static Media media;
    public static MediaPlayer mediaPlayer;
    public static int tauntSize;
    public static int shoutSize;
    public static int soundEffectSize;
    public static int tauntidx = 0;
    public static int shoutidx = 0;
    public static int soundEffectidx = 0;

    public static void initalize(){
        try{
            taunts = new ArrayList<>();
            shouts = new ArrayList<>();
            soundEffects = new ArrayList<>();
            directory = new File("src/voice/taunt");
//            directory = new File("C:\\Users\\user\\Desktop\\voice\\taunt");
            files = directory.listFiles();
            if (files != null) {
                for (File file : files) {
                    taunts.add(file);
                }
            }
            tauntSize = files.length;
            directory = new File("src/voice/shout");
//            directory = new File("C:\\Users\\user\\Desktop\\voice\\shout");
            files = directory.listFiles();
            if (files != null) {
                for (File file : files) {
                    shouts.add(file);
                }
            }
            shoutSize = files.length;
            directory = new File("src/voice/soundEffect");
//            directory = new File("C:\\Users\\user\\Desktop\\voice\\soundEffect");
            files = directory.listFiles();
            if (files != null) {
                for (File file : files) {
                    soundEffects.add(file);
                }
            }
            soundEffectSize = files.length;

        }catch(Exception e){

        }
    }
    public static void playVoice(int sel){
        if(sel == 0){
            media = new Media(taunts.get(tauntidx).toURI().toString());
            tauntidx = (tauntidx + 1)%tauntSize;
        }else if(sel == 1){
            media = new Media(shouts.get(shoutidx).toURI().toString());
            shoutidx = (shoutidx + 1)%shoutSize;
        }else{
            media = new Media(soundEffects.get(soundEffectidx).toURI().toString());
            System.out.println("play");
        }
        mediaPlayer = new MediaPlayer(media);
        mediaPlayer.play();
    }
}
