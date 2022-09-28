package uet.oop.bomberman.entities.tile.destroyable;

import uet.oop.bomberman.entities.tile.Tile;
import uet.oop.bomberman.graphics.Screen;
import uet.oop.bomberman.graphics.Sprite;
import javafx.scene.image.Image;
import uet.oop.bomberman.entities.Entity;
import uet.oop.bomberman.entities.bomb.Flame;
/**
 * Entity cố định có thể bị phá hủy
 */
public class DestroyableTile extends Tile {
    //
    private final int MAX_ANIMATE = 7500;
    private int animate = 0;
    protected boolean isDestroyed = false;
    protected int _timeToDisapear = 20;
    protected Sprite _belowSprite = Sprite.grass;

    public DestroyableTile(int x, int y, Sprite sprite) {
        super(x, y, sprite);
    }

    public DestroyableTile(int x, int y, Image img) {
        super(x, y, img);
    }

    @Override
    public void update() {
        if (isDestroyed) {
            animate = (animate + 1) % MAX_ANIMATE;
            if (_timeToDisapear > 0) {
                _timeToDisapear--;
                if (_timeToDisapear == 0) {
                    remove();
                }
            }
        }
    }

    @Override
    public void render(Screen screen) {

    }

    public void destroy() {
        isDestroyed = true;
    }

    @Override
    public boolean collide(Entity e) {
        if (e instanceof Flame) {
            destroy();
        }
        return false;
    }

    public void addBelowSprite(Sprite sprite) {
        _belowSprite = sprite;
    }

    protected Sprite movingSprite(Sprite normal, Sprite x1, Sprite x2) {
        int calc = animate % 60;
        if (calc < 20) {
            return normal;
        }
        if (calc < 40) {
            return x1;
        }
        return x2;
    }
}
