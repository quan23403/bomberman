package uet.oop.bomberman.entities.character.enemies;

import javafx.scene.image.Image;
import uet.oop.bomberman.BombermanGame;
import uet.oop.bomberman.graphics.Sprite;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Doll extends Enemy {
    private int direction;

    public Doll(int x, int y, Image image) {
        super(x, y, image);
        setLayer(1);
        setSpeed(2);
        generateDirection();
        alive = true;
    }

    @Override
    public void update() {
        if(isAlive()) {
            int j = (int) (this.getX() / Sprite.SCALED_SIZE) ;
            int i = (int) (this.getY() / Sprite.SCALED_SIZE) ;
            List<Integer> cnt = new ArrayList<Integer>();
            if ( (j * 32) == this.getX()  && (i * 32 ) == this.getY()) {
                //System.out.println("ok");
                if (BombermanGame.isSolid[i][j-1] == 0) {
                    cnt.add(0);
                }
                if (BombermanGame.isSolid[i][j+1] == 0) {
                    cnt.add(1);
                }
            }

            if(cnt.size() > 0) {
                Random random = new Random();
                direction = cnt.get(random.nextInt(cnt.size()));
            }
            if (direction == 0) goLeft();
            if (direction == 1) goRight();
        } else if(animated < 30){
            animated ++;
            img = Sprite.doll_dead.getFxImage();
        }else
            BombermanGame.enemies.remove(this);
    }

    public void goLeft() {
        super.goLeft();
        img = Sprite.movingSprite(Sprite.doll_left1, Sprite.doll_left2, Sprite.doll_left3, img_No++, 18).getFxImage();
    }

    public void goRight() {
        super.goRight();
        img = Sprite.movingSprite(Sprite.doll_right1, Sprite.doll_right2, Sprite.doll_right3, img_No++, 18).getFxImage();
    }

    @Override
    public void stay() {
        super.stay();
        generateDirection();
    }

    @Override
    public void generateDirection() {
        if(direction == 1)
            direction = 0;
        else
            direction = 1;
    }

    @Override
    public void restartEnemy() {

    }
}
