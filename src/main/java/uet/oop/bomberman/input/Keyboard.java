package uet.oop.bomberman.input;

import javafx.scene.input.KeyCode;


/**
 * Tiếp nhận và xử lý các sự kiện nhập từ bàn phím
 */
public class Keyboard {

    private final boolean[] keys = new boolean[120]; // 120 is the max number of keys
    public boolean up, down, left, right, space, enter, esc;

    // method to update the keys
    public void update() {
        up = keys[KeyCode.UP.getCode()] || keys[KeyCode.W.getCode()];
        down = keys[KeyCode.DOWN.getCode()] || keys[KeyCode.S.getCode()];
        left = keys[KeyCode.LEFT.getCode()] || keys[KeyCode.A.getCode()];
        right = keys[KeyCode.RIGHT.getCode()] || keys[KeyCode.D.getCode()];
        space = keys[KeyCode.SPACE.getCode()];
    }

    public Keyboard() {
        for (int i = 0; i < keys.length; i++) {
            keys[i] = false;
        }
    }
}
