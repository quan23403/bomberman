package uet.oop.bomberman.entities;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import uet.oop.bomberman.graphics.IRender;
import uet.oop.bomberman.graphics.Sprite;

/**
 * Base class for all entities in the game
 */
public abstract class Entity implements IRender {
    //Tọa độ X tính từ góc trái trên trong Canvas
    protected int _x;

    //Tọa độ Y tính từ góc trái trên trong Canvas
    protected int _y;

    protected boolean isRemoved = false;

    // ảnh của entity
    protected Sprite sprite;
    protected Image img;

    // Khởi tạo đối tượng, chuyển từ tọa độ đơn vị sang tọa độ trong canvas
    public Entity( int xUnit, int yUnit, Image img) {
        _x = xUnit * Sprite.SCALED_SIZE;
        _y = yUnit * Sprite.SCALED_SIZE;
        this.img = img;
    }

    public Entity(int x, int y, Sprite sprite) {
        _x = x;
        _y = y;
        this.sprite = sprite;
    }

    public void render(GraphicsContext gc) {
        gc.drawImage(img, _x, _y);
    }

    public abstract void update();

    /**
     * Xử lý va chạm với Entity khác
     *
     * @param e
     * @return
     */
    public abstract boolean collide(Entity e);

    // getters & setters
    public int getX() {
        return _x;
    }

    public int getY() {
        return _y;
    }

    public void setX(int x) {
        _x = x;
    }

    public void setY(int y) {
        _y = y;
    }

    public Sprite getSprite() {
        return sprite;
    }

    public void setSprite(Sprite sprite) {
        this.sprite = sprite;
    }

    public int getXUnit() {
        return _x / Sprite.SCALED_SIZE;
    }

    public int getYUnit() {
        return _y / Sprite.SCALED_SIZE;
    }

    public boolean isRemoved() {
        return isRemoved;
    }

    public void remove() {
        isRemoved = true;
    }
}