package dominion.utils;

import dominion.Main;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;

import java.io.File;


import java.net.URL;
import java.util.ArrayList;

public class VoicePlayer {
    public static File file;
    public static URL url;
    public static ArrayList<String> taunts;
    public static ArrayList<String> shouts;
    public static ArrayList<String> soundEffects;
    public static Media media;
    public static MediaPlayer mediaPlayer;
    public static int tauntSize;
    public static int shoutSize;
    public static int soundEffectSize;
    public static int tauntIdx = 0;
    public static int shoutIdx = 0;
    public static double volume = 50;
    public static double effectVolume = 25;


    public static void initialize() {
        try {
            taunts = new ArrayList<>();
            shouts = new ArrayList<>();
            soundEffects = new ArrayList<>();
            shouts.add(Main.documentBase + "src/voice/shout/shout1.mp3");
            shouts.add(Main.documentBase + "src/voice/shout/shout2.mp3");
            shouts.add(Main.documentBase + "src/voice/shout/shout3.mp3");
            shoutSize = shouts.size();

            taunts.add(Main.documentBase + "src/voice/taunt/taunt1.mp3");
            taunts.add(Main.documentBase + "src/voice/taunt/taunt2.mp3");
            taunts.add(Main.documentBase + "src/voice/taunt/taunt3.mp3");
            tauntSize = taunts.size();

            soundEffects.add(Main.documentBase + "src/voice/soundEffect/soundEffect1.mp3");
            soundEffects.add(Main.documentBase + "src/voice/soundEffect/soundEffect2.mp3");
            soundEffects.add(Main.documentBase + "src/voice/soundEffect/soundEffect3.mp3");
            soundEffectSize = soundEffects.size();
        } catch (Exception e) {

        }
    }

    public static void playVoice(int sel) {
        if (sel == 0) {
            media = new Media(taunts.get(tauntIdx));
            tauntIdx = (tauntIdx + 1) % tauntSize;
        } else if (sel == 1) {
            media = new Media(shouts.get(shoutIdx));
            shoutIdx = (shoutIdx + 1) % shoutSize;
        } else {
            return;
        }
        mediaPlayer = new MediaPlayer(media);
        mediaPlayer.setVolume(volume * 0.01);
        mediaPlayer.play();
    }

    public static void playEffect(int idx) {
        if (idx >= soundEffectSize)
            return;
        media = new Media(soundEffects.get(idx));
        mediaPlayer = new MediaPlayer(media);
        mediaPlayer.setVolume(effectVolume * 0.01);
        mediaPlayer.play();
        if (idx == 2)
            System.out.println("playEffect");
    }

    public static void setVolume(double v, double vv) {
        volume = v;
        effectVolume = vv;
    }

    public static double getVolume() {
        return volume;
    }

    public static double getEffectVolume() {
        return effectVolume;
    }

}
