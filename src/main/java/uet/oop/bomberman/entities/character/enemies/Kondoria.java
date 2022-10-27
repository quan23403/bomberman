package uet.oop.bomberman.entities.character.enemies;

import javafx.scene.image.Image;
import uet.oop.bomberman.BombermanGame;
import uet.oop.bomberman.entities.character.Bomber;
import uet.oop.bomberman.entities.character.enemies.AI.AStar;
import uet.oop.bomberman.entities.character.enemies.AI.Point;

import uet.oop.bomberman.entities.character.enemies.Enemy;
import uet.oop.bomberman.graphics.Sprite;

import java.util.List;

public class Kondoria extends Enemy {

    private int direction;
    private int moveX = 0;
    private int moveY = 0;
    public Kondoria(int x, int y, Image image) {
        super(x, y, image);
        setLayer(1);
        setSpeed(2);
        generateDirection();
    }
    public void goLeft() {
        super.goLeft();
        img = Sprite.movingSprite(Sprite.kondoria_left1, Sprite.kondoria_left2, Sprite.kondoria_left3, img_No++, 18).getFxImage();
    }

    public void goRight() {
        super.goRight();
        img = Sprite.movingSprite(Sprite.kondoria_right1, Sprite.kondoria_right2, Sprite.kondoria_right3, img_No++, 18).getFxImage();
    }

    public void goUp() {
        super.goUp();
        img = Sprite.movingSprite(Sprite.kondoria_left1, Sprite.kondoria_left2, Sprite.kondoria_left3, img_No++, 18).getFxImage();
    }

    public void goDown() {
        super.goDown();
        img = Sprite.movingSprite(Sprite.kondoria_right1, Sprite.kondoria_right2, Sprite.kondoria_right3, img_No++, 18).getFxImage();
    }

    @Override
    public void stay() {
        super.stay();
        generateDirection();
    }

    @Override
    public void update() {
        if(isAlive()){
            generateDirection();
            if (direction == 0) goLeft();
            if (direction == 1) goRight();
            if (direction == 2) goUp();
            if (direction == 3) goDown();
            if(! BombermanGame.myBomber.isAlive()) {
                restartEnemy();
            }
        } else if(animated < 30) {
            super.stay();
            animated++;
            img = Sprite.kondoria_dead.getFxImage();
        } else
            BombermanGame.enemies.remove(this);
    }

    @Override
    public void generateDirection() {
        direction = -1;
        Bomber bomber = BombermanGame.myBomber;
        int j = this.getX() / Sprite.SCALED_SIZE;
        int i = this.getY() / Sprite.SCALED_SIZE;

        int jBomber = bomber.getX() / Sprite.SCALED_SIZE;
        int iBomber = bomber.getY() / Sprite.SCALED_SIZE;

        if ( (j * 32) == this.getX()  && (i * 32 ) == this.getY()) {
            moveX = 0;
            moveY = 0;
            if (j == jBomber) {
                moveX = 0;
            } else if (BombermanGame.isSolid[i][j-1] == 0 && jBomber < j) {
                moveX = - this.speed;
            } else if (BombermanGame.isSolid[i][j+1] == 0 && jBomber > j) {
                moveX = this.speed;
            }
            if(i == iBomber) {
                moveY = 0;
            } else if (BombermanGame.isSolid[i-1][j] == 0 && iBomber < i) {
                moveY = this.speed;
            } else if (BombermanGame.isSolid[i+1][j] == 0 && iBomber > i) {
                moveY =  - this.speed;
            }
            // Uu tien cai xa hon
            if(moveX != 0 && moveY != 0) {
                if(Math.abs(jBomber - j) < Math.abs(iBomber - i)) {
                    moveX = 0;
                } else {
                    moveY = 0;
                }
            }
        }
        if (moveX < 0) {
            direction = 0;
        } else if (moveX > 0) {
            direction = 1;
        } else if (moveY > 0) {
            direction = 2;
        } else if (moveY < 0) {
            direction = 3;
        }
    }

    @Override
    public void restartEnemy() {
        super.stay();
        this.x = startX * Sprite.SCALED_SIZE;
        this.y = startY * Sprite.SCALED_SIZE;
    }
}