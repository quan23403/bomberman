package uet.oop.bomberman.entities;

import javafx.scene.image.Image;

public abstract class AnimatedEntity extends Entity{
    // Instance variables
    protected int animate = 0;
    protected final int MAX_ANIMATE = 7500;

    public AnimatedEntity(int xUnit, int yUnit, Image img) {
        super(xUnit, yUnit, img);
    }

    protected void animate() {
        animate = (animate + 1) % MAX_ANIMATE;
    }
}
