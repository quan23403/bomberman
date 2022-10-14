package uet.oop.bomberman.entities.fixed;

import javafx.scene.image.Image;
import uet.oop.bomberman.BombermanGame;
import uet.oop.bomberman.graphics.Sprite;

public class Brick extends Tile {

    public Brick(int x, int y, Image image) {
        super(x, y, image);
        setLayer(3);
        alive = true;
    }

    @Override
    public void update() {
        if(!isAlive()){
            if(animated < 45) {
                animated++;
                img = Sprite.movingSprite(Sprite.brick_exploded, Sprite.brick_exploded1
                        , Sprite.brick_exploded2, animated, 45).getFxImage();
            } else
                BombermanGame.stillObjects.remove(this);
        }
    }
}
