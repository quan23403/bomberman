package uet.oop.bomberman.entities.tile;

import javafx.scene.image.Image;
import uet.oop.bomberman.entities.Entity;
import uet.oop.bomberman.graphics.Screen;
import uet.oop.bomberman.graphics.Sprite;

/**
 * Entity cố định, không di chuyển
 */
public abstract class Tile extends Entity {
    public Tile (int x, int y, Sprite sprite) {
        super(x, y, sprite);
    }

    public Tile (int x, int y, Image img) {
        super(x, y, img);
    }

    /**
     * Mặc định không cho phép đối tượng đi qua
     * @param e Entity va chạm
     * @return true nếu có va chạm, false nếu không
     */

    @Override
    public boolean collide(Entity e) {
        return false;
    }

    @Override
    public void update() {

    }

    @Override
    public void render(Screen screen) {
        screen.renderEntity(_x, _y, this);
    }
}
