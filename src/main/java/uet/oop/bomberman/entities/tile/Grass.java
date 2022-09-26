package uet.oop.bomberman.entities.tile;

import javafx.scene.image.Image;
import uet.oop.bomberman.entities.Entity;
import uet.oop.bomberman.graphics.Screen;

public class Grass extends Entity {

    public Grass(int x, int y, Image img) {
        super(x, y, img);
    }

    @Override
    public void update() {

    }

    @Override
    public void render(Screen screen) {

    }

    /**
     * Grass không có gì để render
     * @param e
     * @return
     */
    @Override
    public boolean collide(Entity e) {
        return true;
    }
}