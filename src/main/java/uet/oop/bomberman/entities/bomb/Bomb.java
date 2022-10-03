package uet.oop.bomberman.entities.bomb;

import javafx.scene.image.Image;
import uet.oop.bomberman.Board;
import uet.oop.bomberman.Game;
import uet.oop.bomberman.entities.AnimatedEntity;
import uet.oop.bomberman.entities.Entity;
import uet.oop.bomberman.graphics.Screen;
import uet.oop.bomberman.graphics.Sprite;
import uet.oop.bomberman.sound.Sound;
import uet.oop.bomberman.entities.bomb.FlameSegment;

public class Bomb extends AnimatedEntity {
    protected double _timeToExplode = 120; //2 seconds - thoi gian phat no
    public int _timeAfter = 20;// thoi gian de no

    protected Board _board;
    protected Flame[] _flames;
    protected boolean _exploded = false;
    protected boolean _allowedToPassThru = true;

    /**
     * Constructor.
     */
    public Bomb(int x, int y, Image img) {
        super(x, y, img);
        _flames = new Flame[4];
    }
    public Bomb(int x, int y, Board board) {
        super(x, y, (Image) null);
        _board = board;
        _sprite = Sprite.bomb;
    }

    @Override
    public void update() {
//        if(_timeToExplode > 0)
//            _timeToExplode--;
//        else {
//            if(!_exploded)
//                explode();
//            else
//                updateFlames();
//
//            if(_timeAfter > 0)
//                _timeAfter--;
//            else
//                remove();
//        }
//
//        animate();
    }

    @Override
    public void render(Screen screen) {

    }

    @Override
    public boolean collide(Entity e) {
        return false;
    }

    public void renderFlames(Screen screen) {
        for (int i = 0; i < _flames.length; i++) {
            _flames[i].render(screen);
        }
    }

    public void updateFlames() {
        for (int i = 0; i < _flames.length; i++) {
            _flames[i].update();
        }
    }

    /**
     * Xử lý Bomb nổ
     */
//    protected void explode() {//nổ
//        _exploded = true;
//        _allowedToPassThru = true;
//        // TODO: xử lý khi Character đứng tại vị trí Bomb
//        Character x = _board.getCharacterAtExcluding((int)_x, (int)_y, null);
//        if(x != null){
//            x.kill();
//        }
//        // TODO: tạo các Flame
//        _flames = new Flame[4];
//        for (int i = 0; i < _flames.length; i++) {
//            _flames[i] = new Flame((int) _x, (int) _y, i, Game.getBombRadius(), _board);
//        }
//        Sound.play("BOM_11_M");
//    }

    public FlameSegment flameAt(int x, int y) {
        if(!_exploded) return null;

        for (int i = 0; i < _flames.length; i++) {
            if(_flames[i] == null) return null;
            FlameSegment e = _flames[i].flameSegmentAt(x, y);
            if(e != null) return e;
        }

        return null;
    }
}
