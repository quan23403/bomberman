package uet.oop.bomberman.graphics;

import uet.oop.bomberman.Board;
import uet.oop.bomberman.Game;
import uet.oop.bomberman.entities.Entity;
import uet.oop.bomberman.entities.character.Bomber;

public class Screen {
    protected int _width, _height;
    public int[] _pixels;
    private int _transparentColor = 0xff000000;

    public static int xOffset = 0, yOffset = 0;

    public Screen(int width, int height) {
        _width = width;
        _height = height;
        _pixels = new int[width * height];
    }

    /**
     * Clear screen.
     */
    public void clear() {
        for (int i = 0; i < _pixels.length; i++) {
            _pixels[i] = 0;
        }
    }

    /**
     * Render a sprite to the screen.
     *
     * @param xp
     * @param yp
     * @param entity
     */
    public void renderEntity(int xp, int yp, Entity entity) {
        xp -= xOffset;
        yp -= yOffset;
        for (int y = 0; y < entity.getSprite().getSize(); y++) {
            int ya = y + yp;    // add y position

            for (int x = 0; x < entity.getSprite().getSize(); x++) {
                int xa = x + xp;    // add x position
                if (xa < -entity.getSprite().getSize() || xa >= _width || ya < 0 || ya >= _height) break;
                if (xa < 0) xa = 0;
                int color = entity.getSprite().getPixels(x + y * entity.getSprite().getSize());
                if (color != _transparentColor) _pixels[xa + ya * _width] = color;
            }
        }
    }

    /**
     * Render a sprite to the screen with a given size.
     *
     * @param xp
     * @param yp
     * @param entity
     * @param below
     */
    public void renderEntityWithBelowSprite(int xp, int yp, Entity entity, Entity below) {
        xp -= xOffset;
        yp -= yOffset;
        for (int y = 0; y < entity.getSprite().getSize(); y++) {
            int ya = y + yp;    // add y position

            for (int x = 0; x < entity.getSprite().getSize(); x++) {
                int xa = x + xp;    // add x position
                if (xa < -entity.getSprite().getSize() || xa >= _width || ya < 0 || ya >= _height) break;
                if (xa < 0) xa = 0;
                int color = entity.getSprite().getPixels(x + y * entity.getSprite().getSize());
                if (color != _transparentColor) _pixels[xa + ya * _width] = color;
                else {
                    color = below.getSprite().getPixels(x + y * below.getSprite().getSize());
                    if (color != _transparentColor) _pixels[xa + ya * _width] = color;
                }
            }
        }
    }

    public static int calculateXOffset(Board board, Bomber bomber) {
        if (bomber == null) return 0;
        int temp = xOffset;
        double bomberX = bomber.getX() / Game.TILES_SIZE;
        double complement = 0.5;
        int firstBreakpoint = board.getWidth() / 4;
        int lastBreakpoint = board.getWidth() - firstBreakpoint;

        if( bomberX > firstBreakpoint + complement && bomberX < lastBreakpoint - complement) {
            temp = (int)bomber.getX()  - (Game.WIDTH / 2);
        }

        return temp;
    }


    // setters.
    public static void setOffset(int xO, int yO) {
        xOffset = xO;
        yOffset = yO;
    }


    // getters.
    public int getWidth() {
        return _width;
    }

    public int getHeight() {
        return _height;
    }

    public int getRealWidth() {
        return _width * Game.SCALE;
    }

    public int getRealHeight() {
        return _height * Game.SCALE;
    }
}
