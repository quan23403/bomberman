package uet.oop.bomberman.input;

import javafx.scene.input.KeyCode;


/**
 * Tiếp nhận và xử lý các sự kiện nhập từ bàn phím
 */
public class Keyboard {

    private final boolean[] keys = new boolean[120]; // 120 is the max number of keys
    public boolean _up, _down, _left, _right, _space, enter, esc;

    // method to update the keys
    public void update() {
        _up = keys[KeyCode.UP.getCode()] || keys[KeyCode.W.getCode()];
        _down = keys[KeyCode.DOWN.getCode()] || keys[KeyCode.S.getCode()];
        _left = keys[KeyCode.LEFT.getCode()] || keys[KeyCode.A.getCode()];
        _right = keys[KeyCode.RIGHT.getCode()] || keys[KeyCode.D.getCode()];
        _space = keys[KeyCode.SPACE.getCode()];
    }

    public Keyboard() {
        for (int j = 0; j < keys.length; j++) {
            keys[j] = false;
        }
    }
}
