package uet.oop.bomberman.entities.character.enemy;

import uet.oop.bomberman.Board;
import uet.oop.bomberman.Game;
import uet.oop.bomberman.entities.Entity;
import uet.oop.bomberman.graphics.Screen;
import uet.oop.bomberman.graphics.Sprite;
import uet.oop.bomberman.entities.character.Character;
public abstract class Enemy extends Character {
    protected int _points;

    protected double _speed;
//    protected AI _ai;

    protected final double MAX_STEPS;
    protected final double rest;
    protected double _steps;

    protected int _finalAnimation = 30;
    protected Sprite _deadSprite;

    public Enemy(int x, int y, Board board, Sprite dead, double speed, int points) {
        super(x, y, board);

        _points = points;
        _speed = speed;

        MAX_STEPS = Game.TILES_SIZE / _speed;
        rest = (MAX_STEPS - (int) MAX_STEPS) / MAX_STEPS;
        _steps = MAX_STEPS;

        _timeAfter = 20;
        _deadSprite = dead;
    }

    @Override
    public void update() {
        animate();

        if(!_alive) {
            afterKill();
            return;
        }

        if(_alive)
            calculateMove();
    }

    @Override
    public void render(Screen screen) {

        if(_alive)
            chooseSprite();
        else {
            if(_timeAfter > 0) {
                _sprite = _deadSprite;
                _animate = 0;
            } else {
                _sprite = Sprite.movingSprite(Sprite.mob_dead1, Sprite.mob_dead2, Sprite.mob_dead3, _animate, 60);
            }

        }

        screen.renderEntity((int)_x, (int)_y - _sprite.SIZE, this);
    }


    @Override
    public boolean collide(Entity e) {
        return false;
    }

    @Override
    protected void calculateMove() {

    }

    @Override
    protected boolean canMove(double x, double y) {
        return false;
    }

    @Override
    protected void move(double xa, double ya) {

    }

    @Override
    public void kill() {

    }

    @Override
    protected void afterKill() {

    }

    protected abstract void chooseSprite();
}
