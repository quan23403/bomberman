package uet.oop.bomberman;

import uet.oop.bomberman.entities.Entity;
import uet.oop.bomberman.entities.Message;
import uet.oop.bomberman.entities.bomb.Bomb;
import uet.oop.bomberman.entities.character.Bomber;
import uet.oop.bomberman.entities.character.Character;
import uet.oop.bomberman.exceptions.LoadLevelException;
import uet.oop.bomberman.graphics.IRender;
import uet.oop.bomberman.graphics.Screen;
import uet.oop.bomberman.input.Keyboard;
import uet.oop.bomberman.level.FileLevelLoader;
import uet.oop.bomberman.level.LevelLoader;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class Board implements IRender {
    protected LevelLoader _levelLoader;
    protected Game _game;
    protected Keyboard _input;
    protected Screen _screen;

    public Entity[] _entities;
    public List<Character> _characters = new ArrayList<>();
    public List<Bomb> _bombs = new ArrayList<>();
    public List<Message> _messages = new ArrayList<>();

    private int _screenToShow = -1;  // 1: Game Over, 2: Change Level, 3: Pause, 4: Win
    private int _time = Game.TIME;
    private int _points = Game.POINTS;

    /**
     * Constructor.
     */
    public Board() {
        _input = new Keyboard();
//        _screen = new Screen(_input, this);
//        _game = new Game(this, _screen);
//        _levelLoader = new LevelLoader(this, 1);
//        _entities = new Entity[_levelLoader.getWidth() * _levelLoader.getHeight()];
//        _levelLoader.createEntities();
    }

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

    public void nextLevel() {
        Game.setBombRadius(1);
        Game.setBombRate(1);
        Game.setBomberSpeed(1.0);
        loadLevel(_levelLoader.getLevel() + 1);
    }

    public void loadLevel(int level) {
        _time = Game.TIME;
        _screenToShow = 2;
        _game.resetScreenDelay();
        _game.pause();
        _characters.clear();
        _bombs.clear();
        _messages.clear();


        try {
            _levelLoader = new FileLevelLoader(this, level);
            _entities = new Entity[_levelLoader.getWidth() * _levelLoader.getHeight()];
            _levelLoader.createEntities();
        } catch (LoadLevelException e) {
            endGame();
        }
    }

    public void endGame() {
        _screenToShow = 1;
        _game.resetScreenDelay();
        _game.pause();
    }

    public void addCharacter(Character c) {
        _characters.add(c);
    }

    public void addEntity(int pos, Entity e) {
        _entities[pos] = e;
    }

    public List<Bomb> getBombs() {
        // if null, create new list
        if (_bombs == null) _bombs = new ArrayList<>();
        return _bombs;
    }

    public Keyboard getInput() {
        return _input;
    }

    /**
     * phat hien enemy.
     */
    public boolean detectNoEnemies() {
        int total = 0;
        for (int i = 0; i < _characters.size(); i++) {
            if (!(_characters.get(i) instanceof Bomber))
                ++total;
        }

        return total == 0;
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

    public void drawScreen(Graphics g) {
    }
}
