package uet.oop.bomberman.entities.item;

import javafx.scene.image.Image;
import uet.oop.bomberman.entities.fixed.Tile;

public abstract class Item extends Tile {
    public Item(int x, int y, Image img) {
        super(x, y, img);
        setLayer(1);
    }

}
