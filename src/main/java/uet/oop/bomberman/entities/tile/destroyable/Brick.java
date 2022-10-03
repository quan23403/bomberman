package uet.oop.bomberman.entities.tile.destroyable;

import javafx.scene.image.Image;
import uet.oop.bomberman.graphics.Sprite;

public class Brick extends DestroyableTile {
    public Brick(int x, int y, Sprite sprite) {
        super(x, y, sprite);
    }

    public Brick(int x, int y, Image image) {
        super(x, y, image);
    }
}
