package uet.oop.bomberman.audio;

import uet.oop.bomberman.BombermanGame;
import uet.oop.bomberman.Menu.ViewManager;

import javax.sound.sampled.*;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import static uet.oop.bomberman.audio.MyAudioPlayer.Loopable.NONELOOP;

public class MyAudioPlayer implements Runnable {

    // Tên file các audio
    public static List<String> BACKGROUND_MUSIC = null;
    public static final String PLACE_BOMB = "place_bomb";
    public static final String POWER_UP = "power_up";
    public static final String EXPLOSION = "explosion";
    public static final String DEAD = "dead";
    public static final String ENEMY_DEAD = "dead2";

    private static boolean _muted = false;

    private Clip clip;

    public enum Loopable {
        NONELOOP,
        LOOP;
    }

    public void initList() {
        if(BACKGROUND_MUSIC == null) {
            BACKGROUND_MUSIC = new LinkedList<>();
            BACKGROUND_MUSIC.add("background_music");
            BACKGROUND_MUSIC.add("Can_t Be Touched - Body Head Bangerz");
            BACKGROUND_MUSIC.add("Naruto-Main-Theme");
            BACKGROUND_MUSIC.add("Fairy Tail");
            BACKGROUND_MUSIC.add("OnePice.wav");
        }
    }

    // Mặc định không phát lại
    private Loopable loopable = NONELOOP;

    public MyAudioPlayer() {
        String path = "/audio/" + "background_music" + ".wav";
        try {
            URL defaultSound = getClass().getResource(path);
            assert defaultSound != null;
            AudioInputStream sound = AudioSystem.getAudioInputStream(defaultSound);
            // load the sound into memory (a Clip)
            clip = AudioSystem.getClip();
            clip.open(sound);
        } catch (MalformedURLException e) {
            e.printStackTrace();
            throw new RuntimeException("Sound: Malformed URL: " + e);
        } catch (UnsupportedAudioFileException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
            throw new RuntimeException("Sound: Input/Output Error: " + e);
        } catch (LineUnavailableException e) {
            e.printStackTrace();
            throw new RuntimeException("Sound: Line Unavailable Exception Error: " + e);
        }
    }
    public MyAudioPlayer(String fileName) {
        initList();
        String path = "/audio/" + fileName + ".wav";
        try {
            URL defaultSound = getClass().getResource(path);
            assert defaultSound != null;
            AudioInputStream sound = AudioSystem.getAudioInputStream(defaultSound);
            // load the sound into memory (a Clip)
            clip = AudioSystem.getClip();
            clip.open(sound);
        } catch (MalformedURLException e) {
            e.printStackTrace();
            throw new RuntimeException("Sound: Malformed URL: " + e);
        } catch (UnsupportedAudioFileException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
            throw new RuntimeException("Sound: Input/Output Error: " + e);
        } catch (LineUnavailableException e) {
            e.printStackTrace();
            throw new RuntimeException("Sound: Line Unavailable Exception Error: " + e);
        }
    }

    public Loopable getLoopable() {
        return loopable;
    }

    public void setLoopable(Loopable loopable) {
        this.loopable = loopable;
    }

    public void play(){
        if (!_muted) {
            clip.setFramePosition(0);  // Chạy từ đầu
            clip.start();
        }
    }

    public void loop(){
        if (!_muted) {
            clip.loop(Clip.LOOP_CONTINUOUSLY);
        }
    }
    public void stop(){
        clip.stop();
    }

    @Override
    public void run() {
        switch (loopable) {
            case LOOP:
                this.loop();
                break;
            case NONELOOP:
                this.play();
                break;
        }
    }

    public static void mute() {
        _muted = !_muted;
        if (_muted) {
            ViewManager.musicPlayer.stop();
        } else {
            ViewManager.musicPlayer.loop();
        }
    }

    public static boolean isMuted() {
        return _muted;
    }
}
