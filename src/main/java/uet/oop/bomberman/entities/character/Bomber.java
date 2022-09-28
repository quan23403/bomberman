package uet.oop.bomberman.entities.character;

import javafx.scene.SnapshotParameters;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.paint.Color;
import uet.oop.bomberman.Board;
import uet.oop.bomberman.entities.Entity;
import uet.oop.bomberman.entities.bomb.Bomb;
import uet.oop.bomberman.entities.tile.item.Item;
import uet.oop.bomberman.graphics.Screen;
import uet.oop.bomberman.graphics.Sprite;
import uet.oop.bomberman.input.Keyboard;

import java.util.ArrayList;
import java.util.List;

public class Bomber extends Character {
    private List<Bomb> _bombs;
    protected Keyboard _input;
    public static List<Item> _items = new ArrayList<Item>();

    /**
     * If this object < 0, then allow to put new bomb.
     * after put new bomb, this object = 0, and decrease 1 after each update.
     */
    protected int _timeBetweenPutBombs = 0;

    /**
     * Constructor.
     */
    public Bomber(int x, int y, Image img) {
        super( x, y, img);
        _bombs = new ArrayList<>();
        _bombs = _board.getBombs();
        _input = _board.getInput();

    }

    public Bomber(int x, int y, Board board) {
        super(x, y, board);
        _bombs = _board.getBombs();
        _input = _board.getInput();
        _sprite = Sprite.player_right;
    }
    @Override
    public void update() {

    }

    @Override
    public void render(Screen screen) {

    }

    @Override
    public boolean collide(Entity e) {
        return false;
    }

    @Override
    protected void calculateMove() {

    }

    @Override
    protected boolean canMove(double x, double y) {

        return false;
    }

    @Override
    protected void move(double xa, double ya) {

    }

    @Override
    public void kill() {

    }

    @Override
    protected void afterKill() {

    }
}