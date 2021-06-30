package dominion.utils;

import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;

import java.io.File;


import java.net.URL;
import java.util.ArrayList;

public class VoicePlayer {
    public static File file;
    public static URL url;
    public static ArrayList<File> taunts;
    public static ArrayList<File> shouts;
    public static ArrayList<File> soundEffects;
    public static Media media;
    public static MediaPlayer mediaPlayer;
    public static int tauntSize;
    public static int shoutSize;
    public static int soundEffectSize;
    public static int tauntIdx = 0;
    public static int shoutIdx = 0;
    public static double volume = 50;
    public static double effectVolume = 25;


    public static void initialize(){
        try{
            taunts = new ArrayList<>();
            shouts = new ArrayList<>();
            soundEffects = new ArrayList<>();
            url = VoicePlayer.class.getClassLoader().getResource("voice/shout/shout1.mp3");
            file = new File(url.getPath());
            shouts.add(file);
            url = VoicePlayer.class.getClassLoader().getResource("voice/shout/shout2.mp3");
            file = new File(url.getPath());
            shouts.add(file);
            url = VoicePlayer.class.getClassLoader().getResource("voice/shout/shout3.mp3");
            file = new File(url.getPath());
            shouts.add(file);
            shoutSize = shouts.size();
            url = VoicePlayer.class.getClassLoader().getResource("voice/taunt/taunt1.mp3");
            file = new File(url.getPath());
            taunts.add(file);
            url = VoicePlayer.class.getClassLoader().getResource("voice/taunt/taunt2.mp3");
            file = new File(url.getPath());
            taunts.add(file);
            url = VoicePlayer.class.getClassLoader().getResource("voice/taunt/taunt3.mp3");
            file = new File(url.getPath());
            taunts.add(file);
            tauntSize = taunts.size();
            url = VoicePlayer.class.getClassLoader().getResource("voice/soundEffect/soundEffect1.mp3");
            file = new File(url.getPath());
            soundEffects.add(file);
            url = VoicePlayer.class.getClassLoader().getResource("voice/soundEffect/soundEffect2.mp3");
            file = new File(url.getPath());
            soundEffects.add(file);
            url = VoicePlayer.class.getClassLoader().getResource("voice/soundEffect/soundEffect3.mp3");
            file = new File(url.getPath());
            soundEffects.add(file);

            soundEffectSize = soundEffects.size();
        }catch(Exception e){

        }
    }
    public static void playVoice(int sel){
        if(sel == 0){
            media = new Media(taunts.get(tauntIdx).toURI().toString());
            tauntIdx = (tauntIdx + 1)%tauntSize;
        }else if(sel == 1){
            media = new Media(shouts.get(shoutIdx).toURI().toString());
            shoutIdx = (shoutIdx + 1)%shoutSize;
        }else{
            return;
        }
        mediaPlayer = new MediaPlayer(media);
        mediaPlayer.setVolume(volume * 0.01);
        mediaPlayer.play();
        System.out.println("playVoice");
    }
    public static void playEffect(int idx){
        if(idx >= soundEffectSize)
            return;
        media = new Media(soundEffects.get(idx).toURI().toString());
        mediaPlayer = new MediaPlayer(media);
        mediaPlayer.setVolume(effectVolume * 0.01);
        mediaPlayer.play();
        if(idx == 2)
            System.out.println("playEffect");
    }
    public static void setVolume(double v,double vv){volume = v;effectVolume = vv;}
    public static double getVolume(){return volume;}
    public static double getEffectVolume(){return effectVolume;}

}
