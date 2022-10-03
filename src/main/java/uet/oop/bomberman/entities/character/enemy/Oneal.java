package uet.oop.bomberman.entities.character.enemy;

import javafx.scene.image.Image;
import uet.oop.bomberman.entities.Entity;
import uet.oop.bomberman.entities.character.Character;

public class Oneal extends Character {
    public Oneal(int x, int y, Image image) {
        super(x, y, image);
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

    @Override
    public boolean collide(Entity e) {
        return false;
    }
}
