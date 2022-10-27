package uet.oop.bomberman.entities.bomb;

import javafx.scene.image.Image;
import uet.oop.bomberman.BombermanGame;
import uet.oop.bomberman.entities.CollisionTypeCheck;
import uet.oop.bomberman.entities.Entity;
import uet.oop.bomberman.entities.fixed.Brick;
import uet.oop.bomberman.entities.fixed.Wall;
import uet.oop.bomberman.graphics.Sprite;

import java.awt.*;

public class Flame extends Entity {
    private int left;
    private int right;
    private int top;
    private int down;
    public int radius;
    private int size = Sprite.SCALED_SIZE;
    private int direction;
    private int time = 0;


    /**
     * Constructor.
     */
    public Flame(int _x, int _y, Image _image, int _direction) {
        super(_x, _y);
        this.img = _image;
        this.direction = direction;
    }

    public Flame(int _x, int _y, Image _image) {
        super(_x, _y);
        this.img = _image;
        this.radius = 2;

    }

    public Flame(int _x, int _y) {
        super(_x, _y);
    }

    public void setRadius(int radius) {

        this.radius = radius;
    }


    @Override
    public void update() {
        if (time <= 19) {
            time++;
            setImg();
        } else
            BombermanGame.flameList.remove(this);
    }

    public void render_explosion() {

        Right();
        Left();
        Top();
        Down();
        create_explosion();
    }

    private void create_explosion() {
        BombermanGame.flameList.add(new Flame(x, y, Sprite.bomb_exploded.getFxImage(), 0));
        for (int i = 0; i < right; i++) {
            Flame e = new Flame(x + size * (i + 1), y);
            if (i == right - 1) {
                e.img = Sprite.explosion_horizontal_right_last.getFxImage();
                e.direction = 2;
            } else {
                e.img = Sprite.explosion_horizontal.getFxImage();
                e.direction = 1;
            }
            BombermanGame.flameList.add(e);
        }

        for (int i = 0; i < left; i++) {
            Flame e = new Flame(x - size * (i + 1), y);
            if (i == left - 1) {
                e.img = Sprite.explosion_horizontal_left_last.getFxImage();
                e.direction = 3;
            } else {
                e.img = Sprite.explosion_horizontal.getFxImage();
                e.direction = 1;
            }
            BombermanGame.flameList.add(e);
        }

        for (int i = 0; i < top; i++) {
            Flame e = new Flame(x, y - size * (i + 1));
            if (i == top - 1) {
                e.img = Sprite.explosion_vertical_top_last.getFxImage();
                e.direction = 5;
            } else {
                e.img = Sprite.explosion_vertical.getFxImage();
                e.direction = 4;
            }
            BombermanGame.flameList.add(e);
        }

        for (int i = 0; i < down; i++) {
            Flame e = new Flame(x, y + size * (i + 1));
            if (i == right - 1) {
                e.img = Sprite.explosion_vertical_down_last.getFxImage();
                e.direction = 6;
            } else {
                e.img = Sprite.explosion_vertical.getFxImage();
                e.direction = 4;
            }
            BombermanGame.flameList.add(e);
        }
    }

    private void Right() {
        // move right.
        if(BombermanGame.stillObjects == null){
            return;
        }
        int right_layer = 0;
        for (int i = 0; i < radius; i++) {
            //Rectangle ex_right = new Rectangle(x + size * (i + 1), y, size, size);
            for(int j = 0; j < BombermanGame.stillObjects.size(); j++) {
                Entity ex_right = BombermanGame.stillObjects.get(j);
                if(ex_right.getX() == x + size * (i + 1) && ex_right.getY() == y) {
                    right_layer = ex_right.getLayer();
                    break;
                }
            }
            if (right_layer == 4) {
                right = i;
                return;
            } else if (right_layer == 3) {
                right = i + 1;
                return;
            }
            right = i + 1;
        }
    }

    private void Left() {
        // move left
        if(BombermanGame.stillObjects == null){
            return;
        }
        int left_layer = 0;
        for (int i = 0; i < radius; i++) {
            //Rectangle ex_left = new Rectangle(x - size * (i + 1), y, size, size);
            for(int j = 0; j < BombermanGame.stillObjects.size(); j++) {
                Entity ex_left = BombermanGame.stillObjects.get(j);
                if(ex_left.getX() == x - size * (i + 1) && ex_left.getY() == y) {
                    left_layer = ex_left.getLayer();
                    break;
                }
            }
            if (left_layer == 4) {
                left = i;
                return;
            } else if (left_layer == 3) {
                left = i + 1;
                return;
            }
            left = i + 1;
        }
    }

    private void Top() {
        // move top
        if(BombermanGame.stillObjects == null){
            return;
        }
        int top_layer = 0;
        for (int i = 0; i < radius; i++) {
            //Rectangle ex_top = new Rectangle(x, y - size * (i + 1), size, size);
            for(int j = 0; j < BombermanGame.stillObjects.size(); j++) {
                Entity ex_top = BombermanGame.stillObjects.get(j);
                if(ex_top.getX() == x  && ex_top.getY()  == y - size * (i + 1)) {
                    top_layer = ex_top.getLayer();
                    break;
                }
            }
            if (top_layer == 4) {
                top = i;
                return;
            } else if (top_layer == 3) {
                top = i + 1;
                return;
            }
            top = i + 1;
        }
    }

    private void Down() {
        // move down
        if(BombermanGame.stillObjects == null){
            return;
        }
        int down_layer = 0;
        for (int i = 0; i < radius; i++) {
            //Rectangle ex_down = new Rectangle(x, y + size * (i + 1), size, size);
            for(int j = 0; j < BombermanGame.stillObjects.size(); j++) {
                Entity ex_down = BombermanGame.stillObjects.get(j);
                if(ex_down.getX() == x  && ex_down.getY()  == y + size * (i + 1)) {
                    down_layer = ex_down.getLayer();
                    break;
                }
            }
            if (down_layer == 4) {
                down = i;
                return;
            } else if (down_layer == 3) {
                down = i + 1;
                return;
            }
            down = i + 1;
        }
    }

    private static boolean isCollisionsWall(Rectangle r) {
        // Box
        for (Entity e : BombermanGame.stillObjects) {
            Rectangle r2 = e.getHitbox();
            if (r.intersects(r2)) {
                if (e instanceof Wall)
                    return true;
            }
        }
        return false;
    }

//    @Override
//    public Object collisionType(Rectangle _r) {
////        for (Entity e : BombermanGame.stillObjects) {
////            Rectangle r2 = e.getHitbox();
////            if (_r.intersects(r2)) {
////                return e;
////            }
////        }
//        return _r;
//    }

    private void setImg() {
        // Hinh anh dong khi di chuyen .
        switch (direction) {
            case 0:
                img = Sprite.movingSprite(Sprite.bomb_exploded, Sprite.bomb_exploded1,
                        Sprite.bomb_exploded2, time, 20).getFxImage();
                break;
            case 1:
                img = Sprite.movingSprite(Sprite.explosion_horizontal, Sprite.explosion_horizontal1
                        , Sprite.explosion_horizontal2, time, 20).getFxImage();
                break;
            case 2:
                img = Sprite.movingSprite(Sprite.explosion_horizontal_right_last, Sprite.explosion_horizontal_right_last1
                        , Sprite.explosion_horizontal_right_last2, time, 20).getFxImage();
                break;
            case 3:
                img = Sprite.movingSprite(Sprite.explosion_horizontal_left_last, Sprite.explosion_horizontal_left_last1
                        , Sprite.explosion_horizontal_left_last2, time, 20).getFxImage();
                break;
            case 4:
                img = Sprite.movingSprite(Sprite.explosion_vertical, Sprite.explosion_vertical1
                        , Sprite.explosion_vertical2, time, 20).getFxImage();
                break;
            case 5:
                img = Sprite.movingSprite(Sprite.explosion_vertical_top_last, Sprite.explosion_vertical_top_last1
                        , Sprite.explosion_vertical_top_last2, time, 20).getFxImage();
                break;
            case 6:
                img = Sprite.movingSprite(Sprite.explosion_vertical_down_last, Sprite.explosion_vertical_down_last1
                        , Sprite.explosion_vertical_down_last2, time, 20).getFxImage();
                break;
        }
    }

    public void setHitBox() {
        this.hitbox = new Rectangle(x, y, Sprite.SCALED_SIZE - 5, Sprite.SCALED_SIZE - 5);
    }

    public Rectangle getHitBox() {
        return this.hitbox;
    }
}
