package uet.oop.bomberman.sound;

import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import java.net.URL;
import javax.sound.sampled.AudioInputStream;

public class Sound {
    public static void play(String sound) {
        try {
            URL url = Sound.class.getResource("/sound/" + sound + ".wav");
            AudioInputStream audioIn = AudioSystem.getAudioInputStream(url);
            Clip clip = AudioSystem.getClip();
            clip.open(audioIn);
            clip.start();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public static void play(){
        try {
            URL url = Sound.class.getResource("/sound/soundtrack.wav");
            AudioInputStream audioIn = AudioSystem.getAudioInputStream(url);
            Clip clip = AudioSystem.getClip();
            clip.open(audioIn);
            clip.start();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public static void stop(String sound){
        try {
            URL url = Sound.class.getResource("/sound/" + sound + ".wav");
            AudioInputStream audioIn = AudioSystem.getAudioInputStream(url);
            Clip clip = AudioSystem.getClip();
            clip.open(audioIn);
            clip.stop();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}