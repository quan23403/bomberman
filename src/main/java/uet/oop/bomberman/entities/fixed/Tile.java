package uet.oop.bomberman.entities.fixed;

import javafx.scene.image.Image;
import uet.oop.bomberman.entities.Entity;

/**
 * Entity cố định, không di chuyển
 */
public abstract class Tile extends Entity {
    public Tile (int x, int y, Image img) {
        super(x, y, img);
    }
}
