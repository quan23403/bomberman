package uet.oop.bomberman.entities.bomb;

import javafx.scene.image.Image;
import uet.oop.bomberman.audio.MyAudioPlayer;
import uet.oop.bomberman.entities.AnimatedEntity;
import uet.oop.bomberman.entities.Entity;
import uet.oop.bomberman.entities.character.Bomber;
import uet.oop.bomberman.entities.character.enemies.Minvo;
import uet.oop.bomberman.graphics.Sprite;

import java.awt.*;

public class Bomb extends AnimatedEntity {
    private int timeCounter = 0;
    int radius;

    /**
     * Constructor.
     */
    public Bomb(int x, int y, Image img) {
        super(x, y, img);
        setLayer(2);
        this.radius = 1;
    }

    public Bomb(int xUnit, int yUnit, Image img, int radius) {
        super(xUnit, yUnit, img);
        setLayer(1);
        this.radius = radius;
    }

    @Override
    public void update() {
        if (timeCounter++ == 120) {
            explodeUpgrade();
        }
        img = Sprite.movingSprite(Sprite.bomb, Sprite.bomb_1, Sprite.bomb_2, timeCounter, 60).getFxImage();
    }

    public void explode() {
        Flame e = new Flame(x, y);
        e.render_explosion();
        alive = false;
    }

    public void explodeUpgrade() {
        Flame e = new Flame(x, y);
        e.setRadius(radius);
        e.render_explosion();
        //âm thanh bom nổ
        MyAudioPlayer explodesound = new MyAudioPlayer(MyAudioPlayer.EXPLOSION);
        explodesound.play();
        alive = false;
    }

    public boolean isAllowedToPassThrough(AnimatedEntity e) {
        Rectangle r1 = getBounds();
        Rectangle r2;
        if (e instanceof Bomber bomber) {
//            r2 = new Rectangle(bomber.getX() + 4, bomber.getY() + 4,
//                    Sprite.SCALED_SIZE * 3 / 4, Sprite.SCALED_SIZE * 3 / 4);
            return true;
        } else if (e instanceof Minvo minvo) {
//            r2 = new Rectangle(minvo.getX() + 4, minvo.getY() + 4,
//                    Sprite.SCALED_SIZE * 3 / 4, Sprite.SCALED_SIZE * 3 / 4);
            return true;
        }
        return false;
//        } else {
//            r2 = new Rectangle(e.getX(), e.getY(), Sprite.SCALED_SIZE, Sprite.SCALED_SIZE);
//        }
//        return r1.intersects(r2);//   }
    }
}
