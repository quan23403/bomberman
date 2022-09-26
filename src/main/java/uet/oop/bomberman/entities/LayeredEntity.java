package uet.oop.bomberman.entities;

import java.util.LinkedList;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import uet.oop.bomberman.graphics.Screen;
import uet.oop.bomberman.graphics.Sprite;

public class LayeredEntity extends Entity{
    protected LinkedList<Entity> entities = new LinkedList<>();

    // Khởi tạo đối tượng, chuyển từ tọa độ đơn vị sang tọa độ trong canvas
    public LayeredEntity(int xUnit, int yUnit, Image img) {
        super(xUnit, yUnit, img);
    }

    public LayeredEntity(int x, int y, Entity ... entities) {
        super(x, y, (Image) null);
        for (int i = 0; i < entities.length; i++) {
            this.entities.add(entities[i]);
//            if (i > 1){
//
//            }
        }
    }

    /**
     * Thêm một Entity vào LayeredEntity
     */
    @Override
    public void update() {
        clearRemoved();
        getTopEntity().update();
    }

    @Override
    public void render(Screen screen) {

    }

    @Override
    public boolean collide(Entity e) {
        // get top entity to check collision
        return getTopEntity().collide(e);
    }

    @Override
    public void render(GraphicsContext gc) {
        getTopEntity().render(gc);
    }

    public Entity getTopEntity() {
        return entities.getLast();
    }

    private void clearRemoved(){
        Entity top = getTopEntity();
        if(top.isRemoved()) {
            entities.removeLast();
        }
    }

    public void addBeforeTop(Entity e) {
        clearRemoved();
        entities.add(entities.size() - 1, e);
    }
}
