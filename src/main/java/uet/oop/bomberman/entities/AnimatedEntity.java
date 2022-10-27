package uet.oop.bomberman.entities;

import javafx.scene.image.Image;

import uet.oop.bomberman.graphics.Sprite;

import java.awt.*;


public abstract class AnimatedEntity extends Entity {
    protected int animate = 0;
    protected final int MAX_ANIMATE = 7500; //save the animation status and dont let this get too big
    protected int desX = x;
    protected int desY = y;
    protected int speed;
    protected int img_No = 0;
    public AnimatedEntity(int xUnit, int yUnit, Image img) {
        super(xUnit, yUnit, img);
        alive = true;
    }

    public void setSpeed(int speed) {
        this.speed = speed;
    }

    public void goLeft() {
        desX = x - speed;
        desX = (int)roundNumber(desX * 10.0 ) / 10;
    }

    public void goRight() {
        desX = x + speed;
        desX = (int)roundNumber(desX * 10.0 ) / 10;
    }
    public void goUp() {
        desY = y - speed;
        desY = (int)roundNumber(desY * 10.0 ) / 10;
    }

    public void goDown() {
        desY = y + speed;
        desY = (int)roundNumber(desY * 10.0 ) / 10;
    }

    public void move() {
        x = desX;
        y = desY;
    }

    public void stay() {
        desX = x;
        desY = y;
    }

    public Rectangle getBounds() {
        return new Rectangle(desX, desY , Sprite.SCALED_SIZE -1 , Sprite.SCALED_SIZE -1 );
    }

    protected void animate() {
        if(animate < MAX_ANIMATE) {
            animate++;
        } else {
            animate = 0; //reset animation
        }
    }

    double roundNumber(double a) {
        double num = (int) (a);
        if (num + 0.5 <= a) {
            num += 1;
        }
        return num;
    }
}
