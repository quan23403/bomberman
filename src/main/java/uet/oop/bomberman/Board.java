package uet.oop.bomberman;

import uet.oop.bomberman.graphics.Screen;
import uet.oop.bomberman.entities.Entity;
import uet.oop.bomberman.entities.Message;
import uet.oop.bomberman.entities.bomb.Bomb;
import uet.oop.bomberman.graphics.IRender;
import uet.oop.bomberman.input.Keyboard;
import uet.oop.bomberman.level.LevelLoader;

import java.util.ArrayList;
import java.util.List;

public class Board implements IRender {
    protected LevelLoader _levelLoader;
    protected Game _game;
    protected Keyboard _input;
    protected Screen _screen;

    public Entity[] entities;
    public List<Character> _characters = new ArrayList<>();
    public List<Bomb> _bombs = new ArrayList<>();
    public List<Message> _messages = new ArrayList<>();

    private int _screenToShow = -1;  // 1: Game Over, 2: Change Level, 3: Pause, 4: Win
    private int _time = Game.TIME;
    private int _points = Game.POINTS;

    public Board(Game game, Keyboard input, Screen screen) {
        _game = game;
        _input = input;
        _screen = screen;
        loadLevel(1);   // start in level 1
    }
    @Override
    public void update() {

    }

    @Override
    public void render(Screen screen) {

    }

    public void loadLevel(int level) {
        _time = Game.TIME;
        _screenToShow = 2;
    }

    // getters and setters.
    public int getWidth() {
        return _levelLoader.getWidth();
    }

    public int getHeight() {
        return _levelLoader.getHeight();
    }

    public int getLevel() {
        return _levelLoader.getLevel();
    }

    public int getShow() {
        return _screenToShow;
    }

    public void setShow(int show) {
        _screenToShow = show;
    }

    public int getTime() {
        return _time;
    }

    public int getPoints() {
        return _points;
    }

    public void addPoints(int points) {
        _points += points;
    }

    public Game getGame() {
        return _game;
    }
}
