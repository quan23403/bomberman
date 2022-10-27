package uet.oop.bomberman.entities.character.enemies;

import javafx.scene.image.Image;
import uet.oop.bomberman.BombermanGame;
import uet.oop.bomberman.graphics.Sprite;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Balloon extends Enemy {

    protected int direction;

    public Balloon(int x, int y, Image image) {
        super(x, y, image);
        setLayer(1);
        setSpeed(1);
        generateDirection();
        alive = true;
    }

    public void goLeft() {
        super.goLeft();
        img = Sprite.movingSprite(Sprite.balloom_left1, Sprite.balloom_left2, Sprite.balloom_left3, img_No++, 18).getFxImage();
    }

    public void goRight() {
        super.goRight();
        img = Sprite.movingSprite(Sprite.balloom_right1, Sprite.balloom_right2, Sprite.balloom_right3, img_No++, 18).getFxImage();
    }

    public void goUp() {
        super.goUp();
        img = Sprite.movingSprite(Sprite.balloom_left1, Sprite.balloom_left2, Sprite.balloom_left3, img_No++, 18).getFxImage();
    }

    public void goDown() {
        super.goDown();
        img = Sprite.movingSprite(Sprite.balloom_right1, Sprite.balloom_right2, Sprite.balloom_right3, img_No++, 18).getFxImage();
    }

    @Override
    public void generateDirection() {
        Random random = new Random();
        direction = random.nextInt(4);
    }

    @Override
    public void restartEnemy() {

    }

    @Override
    public void update() {
        if(isAlive()) {

            //System.out.println("Toa do ballon " + this.getX() + " " + this.getY());
            int j = this.getX() / Sprite.SCALED_SIZE;
            int i = this.getY() / Sprite.SCALED_SIZE;
            List<Integer> cnt = new ArrayList<>();
            if ( (j * 32) == this.getX()  && (i * 32 ) == this.getY()) {
                //System.out.println("ok");
                if (BombermanGame.isSolid[i][j-1] == 0) {
                    cnt.add(0);
                }
                if (BombermanGame.isSolid[i][j+1] == 0) {
                    cnt.add(1);
                }
                if (BombermanGame.isSolid[i-1][j] == 0) {
                    cnt.add(2);
                }
                if (BombermanGame.isSolid[i+1][j] == 0) {
                    cnt.add(3);
                }
            }

            if(cnt.size() > 0) {
                Random random = new Random();
                direction = cnt.get(random.nextInt(cnt.size()));
            }
            //stay();
            if (direction == 0) goLeft();
            if (direction == 1) goRight();
            if (direction == 2) goUp();
            if (direction == 3) goDown();

        } else if(animated < 30) {
            animated ++;
            img = Sprite.balloom_dead.getFxImage();
        }else
            BombermanGame.enemies.remove(this);
    }

    @Override
    public void stay() {
        super.stay();
        generateDirection();
    }
}
