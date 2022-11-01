package uet.oop.bomberman.entities;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import uet.oop.bomberman.graphics.Sprite;

import java.awt.*;

/**
 * Base class for all entities in the game
 */
public abstract class Entity {
    //Tọa độ X tính từ góc trái trên trong Canvas
    protected int x;

    //Tọa độ Y tính từ góc trái trên trong Canvas
    protected int y;

    // ảnh của entity
    protected Image img;
    protected int animated = 0;
    protected int layer;
    protected boolean alive;
    protected Rectangle hitbox;
    public Entity() {

    }
    // Khởi tạo đối tượng, chuyển từ tọa độ đơn vị sang tọa độ trong canvas
    public Entity(int xUnit, int yUnit, Image img) {
        x = xUnit * Sprite.SCALED_SIZE;
        y = yUnit * Sprite.SCALED_SIZE;
//        x = xUnit;
//        y = yUnit;
        this.img = img;
        initHitbox();
    }

    public Entity(int x, int y) {
        this.x = x;
        this.y = y;
        initHitbox();
    }

    private void initHitbox() {
        hitbox = new Rectangle(x, y, Sprite.SCALED_SIZE - 1, Sprite.SCALED_SIZE - 1);
    }

    public Rectangle getHitbox() {
        return  hitbox;
    }
    public void render(GraphicsContext gc) {
        gc.drawImage(img, x, y);
    }

    public abstract void update();

    // getters & setters
    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public int getLayer() {
        return layer;
    }

    public void setLayer(int layer) {
        this.layer = layer;
    }

    public void setAlive(boolean alive) {
        this.alive = alive;
    }

    public boolean isAlive() {
        return alive;
    }
}