package uet.oop.bomberman.entities;


import javafx.scene.image.Image;
import uet.oop.bomberman.graphics.Screen;

import java.awt.*;

public class Message extends Entity {
    protected String _message;
    protected int _duration;
    protected Color _color;
    protected int _size;

    /**
     * Hiển thị message khi tiêu diệt được Enemy ví dụ (+100).
     */
    public Message(String message, double x, double y, int duration, Color color, int size) {
        super((int) x, (int) y, (Image) null);
        _message = message;
        _duration = duration * 60; // seconds
        _color = color;
        _size = size;
    }

    /**
     * setter and getter.
     */
    public String getMessage() {
        return _message;
    }

    public void setDuration(int duration) {
        _duration = duration;
    }

    public int getDuration() {
        return _duration;
    }

    public Color getColor() {
        return _color;
    }

    public int getSize() {
        return _size;
    }


    @Override
    public void update() {

    }

    @Override
    public void render(Screen screen) {

    }

    @Override
    public boolean collide(Entity e) {
        return true;
    }
}
