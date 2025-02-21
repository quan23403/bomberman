package uet.oop.bomberman.entities.character;

import javafx.scene.image.Image;
import javafx.scene.input.KeyCode;
import uet.oop.bomberman.BombermanGame;
import uet.oop.bomberman.audio.MyAudioPlayer;
import uet.oop.bomberman.entities.AnimatedEntity;
import uet.oop.bomberman.entities.bomb.Bomb;
import uet.oop.bomberman.graphics.Sprite;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class Bomber extends AnimatedEntity {
    private int bombRemain;
    private boolean placeBombCommand = false;
    private final List<Bomb> bombs = new ArrayList<>();
    private int radius;
    private KeyCode direction = null;
    private int timeAfterDie = 0;

    //rivate int img_No = 0;
    private int power;


    /**
     * Constructor.
     */
    public Bomber(int x, int y, Image img) {
        super(x, y, img);
        setLayer(1);
        setSpeed(2);
        setBombRemain(1);
        setPower(1);
        setRadius(1);
    }


    public void setRadius(int radius) {
        this.radius = radius;
    }

    @Override
    public void update() {
        if (direction == KeyCode.LEFT || direction == KeyCode.A) {
            goLeft();
        }
        if (direction == KeyCode.RIGHT || direction == KeyCode.D) {
            goRight();
        }
        if (direction == KeyCode.UP || direction == KeyCode.W) {
            goUp();
        }
        if (direction == KeyCode.DOWN || direction == KeyCode.S) {
            goDown();
        }
        if (placeBombCommand) {
            placeBomb();
            //âm thanh đặt bom
            MyAudioPlayer placeSound = new MyAudioPlayer(MyAudioPlayer.PLACE_BOMB);
            placeSound.play();
        }
        for (int i = 0; i < bombs.size(); i++) {
            Bomb bomb = bombs.get(i);
            if (!bomb.isAlive()) {
                bombs.remove(bomb);
                bombRemain++;
            }
        }
        //animate();
        if (!isAlive()) {
            timeAfterDie++;
            die();
        }
    }

    public void handleKeyPressedEvent(KeyCode keyCode) {

        if (keyCode == KeyCode.LEFT || keyCode == KeyCode.RIGHT
                || keyCode == KeyCode.UP || keyCode == KeyCode.DOWN
                || keyCode == KeyCode.A || keyCode == KeyCode.D
                || keyCode == KeyCode.W || keyCode == KeyCode.S) {
            this.direction = keyCode;
        }
        if (keyCode == KeyCode.SPACE) {
            placeBombCommand = true;
        }
    }

    public void handleKeyReleasedEvent(KeyCode keyCode) {
        if (direction == keyCode) {
            if (direction == KeyCode.LEFT || direction == KeyCode.A) {
                img = Sprite.player_left.getFxImage();
            }
            if (direction == KeyCode.RIGHT || direction == KeyCode.D) {
                img = Sprite.player_right.getFxImage();
            }
            if (direction == KeyCode.UP || direction == KeyCode.W) {
                img = Sprite.player_up.getFxImage();
            }
            if (direction == KeyCode.DOWN || direction == KeyCode.S) {
                img = Sprite.player_down.getFxImage();
            }
            direction = null;
        }
        if (keyCode == KeyCode.SPACE) {
            placeBombCommand = false;
        }
    }

    public void goLeft() {
        //super.goLeft();
        super.desX -= super.speed;
        img = Sprite.movingSprite(Sprite.player_left, Sprite.player_left_1, Sprite.player_left_2, img_No++, 20).getFxImage();
    }

    public void goRight() {
        //super.goRight();
        super.desX += super.speed;
        img = Sprite.movingSprite(Sprite.player_right, Sprite.player_right_1, Sprite.player_right_2, img_No++, 20).getFxImage();
    }

    public void goUp() {
        //super.goUp();
        super.desY -= super.speed;
        img = Sprite.movingSprite(Sprite.player_up, Sprite.player_up_1, Sprite.player_up_2, img_No++, 20).getFxImage();
    }

    public void goDown() {
        //super.goDown();
        super.desY += super.speed;
        img = Sprite.movingSprite(Sprite.player_down, Sprite.player_down_1, Sprite.player_down_2, img_No++, 20).getFxImage();
    }

    public void die() {
        if (timeAfterDie <= 45) {
            img = Sprite.movingSprite(Sprite.player_dead1, Sprite.player_dead2,
                    Sprite.player_dead3, timeAfterDie, 20).getFxImage();
        }
    }

    public int getPower() {
        return power;
    }

    public void setPower(int power) {
        this.power = power;
    }

    public Rectangle getHitBoxPlayer() {
        return new Rectangle(desX + 4, desY + 4,
                Sprite.SCALED_SIZE - 12, Sprite.SCALED_SIZE * 3 / 4);
    }

    public void setCoordinate(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public void placeBomb() {
        int xB = (int) Math.round((x + 4) / (double) Sprite.SCALED_SIZE);
        int yB = (int) Math.round((y + 4) / (double) Sprite.SCALED_SIZE);
        if (bombRemain > 0) {
            BombermanGame.isSolid[yB][xB] = 1;
            for (Bomb bomb : bombs) {
                if (xB * Sprite.SCALED_SIZE == bomb.getX() && yB * Sprite.SCALED_SIZE == bomb.getY()) return;
            }
            bombs.add(new Bomb(xB, yB, Sprite.bomb.getFxImage(), radius));
            bombRemain--;
        } else
            BombermanGame.isSolid[yB][xB] = 0;
    }

    public int getBombRemain() {
        return bombRemain;
    }

    public void setBombRemain(int bombRemain) {
        this.bombRemain = bombRemain;
    }

    public List<Bomb> getBombs() {
        return bombs;
    }

    public boolean isAlive() {
        return alive;
    }

}