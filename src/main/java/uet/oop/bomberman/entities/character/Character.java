package uet.oop.bomberman.entities.character;

import javafx.scene.image.Image;
import uet.oop.bomberman.Board;
import uet.oop.bomberman.Game;
import uet.oop.bomberman.entities.AnimatedEntity;
import uet.oop.bomberman.graphics.Screen;

public abstract class Character extends AnimatedEntity {
    protected Board _board = new Board();
    protected int _direction = -1;
    protected boolean _alive = true;
    protected boolean _moving = false;
    public int _timeAfter = 40;

    /**
     * Constructor.
     */
    public Character(int xUnit, int yUnit, Image img) {
        super(xUnit, yUnit, img);
    }

    public Character(int x, int y, Board board) {
        super(x, y, null);
        _board = board;
    }

    @Override
    public void update() {

    }

    @Override
    public void render(Screen screen) {

    }

    /**
     * Tính toán hướng đi.
     */
    protected abstract void calculateMove();

    /**
     * Kiểm tra xem đối tượng có di chuyển tới vị trí đã tính toán hay không.
     */
    protected abstract boolean canMove(double x, double y);

    /**
     * Di chuyển đến vị trí đã tính toán.
     */
    protected abstract void move(double xa, double ya);

    /**
     * Được gọi khi đối tượng bị tiêu diệt.
     */
    public abstract void kill();

    /**
     * Xử lý hiệu ứng bị tiêu diệt.
     */
    protected abstract void afterKill();

    /**
     * Kiểm tra xem đối tượng có còn sống hay không.
     */
//    protected double getXMessage() {
//        return (_x * Game.SCALE) + (_sprite.SIZE / 2 * Game.SCALE);
//    }
//
//    protected double getYMessage() {
//        return (_y * Game.SCALE) - (_sprite.SIZE / 2 * Game.SCALE);
//    }
}
