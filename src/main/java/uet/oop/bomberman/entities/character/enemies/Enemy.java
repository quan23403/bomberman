package uet.oop.bomberman.entities.character.enemies;

import javafx.scene.image.Image;
import uet.oop.bomberman.entities.AnimatedEntity;

public abstract class Enemy extends AnimatedEntity {
    protected int startX;
    protected int startY;

    public Enemy(int x, int y, Image image) {
        super(x, y, image);
        setLayer(1);
        startX = x;
        startY = y;
    }

    public abstract void generateDirection();

    public abstract void restartEnemy();
}
