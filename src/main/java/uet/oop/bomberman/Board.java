package uet.oop.bomberman;

import uet.oop.bomberman.entities.Entity;
import uet.oop.bomberman.entities.Message;
import uet.oop.bomberman.entities.bomb.Bomb;
import uet.oop.bomberman.entities.bomb.FlameSegment;
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
import java.util.Iterator;
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
        _screen = new Screen(35, 20);
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
        if( _game.isPaused() ) return;

        updateEntities();
        updateCharacters();
        updateBombs();
        updateMessages();
        detectEndGame();

        for (int i = 0; i < _characters.size(); i++) {
            Character a = _characters.get(i);
            if(a.isRemoved()) _characters.remove(i);
        }
    }

    @Override
    public void render(Screen screen) {
        if (_game.isPaused()) return;
        update();
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

    protected void detectEndGame() {
        if(_time <= 0)
            endGame();
    }

    public void endGame() {
        _screenToShow = 1;
        _game.resetScreenDelay();
        _game.pause();
    }


    protected void updateEntities() {
        if (_game.isPaused()) return;
        for (int i = 0; i < _entities.length; i++) {
            _entities[i].update();
        }
    }

    protected void updateCharacters() {
        if (_game.isPaused()) return;
        Iterator<Character> itr = _characters.iterator();

        while (itr.hasNext() && !_game.isPaused()) itr.next().update();
    }

    protected void updateBombs() {
        if (_game.isPaused()) return;
        Iterator<Bomb> itr = _bombs.iterator();

        while (itr.hasNext()) itr.next().update();
    }

    protected void updateMessages() {
        if (_game.isPaused()) return;
        Message m;
        int left;
        for (int i = 0; i < _messages.size(); i++) {
            m = _messages.get(i);
            left = m.getDuration();

            if (left > 0) m.setDuration(--left);
            else _messages.remove(i);
        }
    }

    public void addCharacter(Character c) {
        _characters.add(c);
    }

    public Entity getEntityAt(double x, double y) {
        return _entities[(int) x + (int) y * _levelLoader.getWidth()];
    }

    public void addEntity(int pos, Entity e) {
        _entities[pos] = e;
    }

    public Entity getEntity(double x, double y, Character m) {

        Entity res = null;

        res = getFlameSegmentAt((int) x, (int) y);
        if (res != null) return res;

        res = getBombAt(x, y);
        if (res != null) return res;

        res = getCharacterAtExcluding((int) x, (int) y, m);
        if (res != null) return res;

        res = getEntityAt((int) x, (int) y);

        return res;
    }

    public List<Bomb> getBombs() {
        // if null, create new list
        if (_bombs == null) _bombs = new ArrayList<>();
        return _bombs;
    }

    public Bomb getBombAt(double x, double y) {
        Iterator<Bomb> bs = _bombs.iterator();
        Bomb b;
        while (bs.hasNext()) {
            b = bs.next();
            if (b.getX() == (int) x && b.getY() == (int) y) return b;
        }
        return null;
    }

    public Bomber getBomber() {
        Iterator<Character> itr = _characters.iterator();

        Character cur;
        while (itr.hasNext()) {
            cur = itr.next();
            if (cur instanceof Bomber) return (Bomber) cur;
        }
        return null;
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
            if (!(_characters.get(i) instanceof Bomber)) ++total;
        }
        return total == 0;
    }

    public Character getCharacterAtExcluding(int x, int y, Character a) {
        Iterator<Character> itr = _characters.iterator();

        Character cur;
        while (itr.hasNext()) {
            cur = itr.next();
            if (cur == a) {
                continue;
            }

            if (cur.getXTile() == x && cur.getYTile() == y) {
                return cur;
            }
        }
        return null;
    }

    public FlameSegment getFlameSegmentAt(int x, int y) {
        Iterator<Bomb> bs = _bombs.iterator();
        Bomb b;
        while (bs.hasNext()) {
            b = bs.next();

            FlameSegment e = b.flameAt(x, y);
            if (e != null) {
                return e;
            }
        }

        return null;
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

    public void addBomb(Bomb e) {
        _bombs.add(e);
    }

    public Game getGame() {
        return _game;
    }

    public void drawScreen(Graphics g) {
    }


}
