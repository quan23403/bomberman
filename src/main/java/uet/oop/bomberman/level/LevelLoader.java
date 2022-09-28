package uet.oop.bomberman.level;

import uet.oop.bomberman.Board;
import uet.oop.bomberman.exceptions.LoadLevelException;

public abstract class LevelLoader {
    protected int _width = 20, _height = 20;    // default level size for testing.
    protected int _level = 1;
    protected Board _board;

    /**
     * Constructor.
     * @param level
     */
    public LevelLoader(Board board, int level) throws LoadLevelException {
        _board = board;

        loadLevel(level);
    }

    /**
     * Load level from file.
     * @param level
     * @throws LoadLevelException
     */
    public abstract void loadLevel(int level) throws LoadLevelException;

    /**
     * create new entities.
     * @return
     */
    public abstract void createEntities();

    // getters.
    public int getWidth() {
        return _width;
    }

    public int getHeight() {
        return _height;
    }

    public int getLevel() {
        return _level;
    }
}
