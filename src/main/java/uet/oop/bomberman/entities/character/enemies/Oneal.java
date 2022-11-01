package uet.oop.bomberman.entities.character.enemies;

import javafx.scene.image.Image;
import uet.oop.bomberman.BombermanGame;
import uet.oop.bomberman.entities.character.Bomber;
import uet.oop.bomberman.graphics.Sprite;

public class Oneal extends Enemy {
    private int direction = -1;
    private int moveX = 0;
    private int moveY = 0;
    public Oneal(int x, int y, Image image) {
        super(x, y, image);
        setLayer(1);
        setSpeed(2);
        generateDirection();
    }

    public void goLeft() {
        /*
        System.out.println("L" + " " + this.getX() + " " + this.getY()  + " " + direction);
        System.out.println(BombermanGame.isSolid[(int) (this.getY() / Sprite.SCALED_SIZE)][(int) (this.getX() / Sprite.SCALED_SIZE) - 1]);
        */
        super.goLeft();
        img = Sprite.movingSprite(Sprite.oneal_left1, Sprite.oneal_left2, Sprite.oneal_left3, img_No++, 18).getFxImage();
    }

    public void goRight() {
        //System.out.println("R" + " " + this.getX() + " " + this.getY());
        super.goRight();
        img = Sprite.movingSprite(Sprite.oneal_right1, Sprite.oneal_right2, Sprite.oneal_right3, img_No++, 28).getFxImage();
    }

    public void goUp() {
        //System.out.println("U" + " " + this.getX() + " " + this.getY());
        super.goUp();
        img = Sprite.movingSprite(Sprite.oneal_left1, Sprite.oneal_left2, Sprite.oneal_left3, img_No++, 18).getFxImage();
    }

    public void goDown() {
        //System.out.println("D" + " " + this.getX() + " " + this.getY());
        super.goDown();
        img = Sprite.movingSprite(Sprite.oneal_right1, Sprite.oneal_right2, Sprite.oneal_right3, img_No++, 18).getFxImage();
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
            img = Sprite.oneal_dead.getFxImage();
//            img = Sprite.movingSprite(Sprite.mob_dead1, Sprite.mob_dead2,
//                    Sprite.mob_dead3, animate, 10).getFxImage();
        } else
            BombermanGame.enemies.remove(this);
    }

    @Override
    public void generateDirection() {
        direction = -1;
        Bomber bomber = BombermanGame.myBomber;
        int j = this.getX() / Sprite.SCALED_SIZE;
        int i = (this.getY() / Sprite.SCALED_SIZE) ;

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
