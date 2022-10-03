package uet.oop.bomberman.entities;

import javafx.scene.image.Image;
import uet.oop.bomberman.graphics.Sprite;

public abstract class AnimatedEntity extends Entity {
    // Instance variables
    protected int _animate = 0;
    protected final int MAX_ANIMATE = 7500;

    public AnimatedEntity(int xUnit, int yUnit, Image img) {
        super(xUnit, yUnit, img);
    }

//    public AnimatedEntity(int xUnit, int yUnit, Sprite sprite) {
//        super(xUnit, yUnit, sprite);
//    }

    protected void animate() {
        _animate = (_animate + 1) % MAX_ANIMATE;
    }
}
