package uet.oop.bomberman;

import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.input.KeyEvent;
import javafx.stage.Stage;
import uet.oop.bomberman.entities.Entity;
import uet.oop.bomberman.entities.character.Bomber;
import uet.oop.bomberman.entities.character.enemy.Balloon;
import uet.oop.bomberman.entities.character.enemy.Oneal;
import uet.oop.bomberman.entities.tile.Grass;
import uet.oop.bomberman.entities.tile.Portal;
import uet.oop.bomberman.entities.tile.Wall;
import uet.oop.bomberman.entities.tile.destroyable.Brick;
import uet.oop.bomberman.entities.tile.destroyable.DestroyableTile;
import uet.oop.bomberman.entities.tile.item.FlameItem;
import uet.oop.bomberman.graphics.Sprite;
import uet.oop.bomberman.sound.Sound;

import java.io.BufferedReader;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class BombermanGame extends Application {

    public static int WIDTH = 35;
    public static final int HEIGHT = 20;

    private GraphicsContext gc;
    private Canvas canvas;
    private List<Entity> entities = new ArrayList<>();
    private List<Entity> stillObjects = new ArrayList<>();


    public static void main(String[] args) {
        Application.launch(BombermanGame.class);
    }

    @Override
    public void start(Stage stage) {
        // Tao Canvas
        canvas = new Canvas(Sprite.SCALED_SIZE * WIDTH, Sprite.SCALED_SIZE * HEIGHT);
        gc = canvas.getGraphicsContext2D();

        // Logo
        Image icon = new Image("logo.png");
        stage.getIcons().add(icon);

        // TITLE
        stage.setTitle("Bomberman Game");

        // Tao root container
        Group root = new Group();
        root.getChildren().add(canvas);

        // Tao scene
        Scene scene = new Scene(root);
//        scene.setOnKeyPressed(new EventHandler<KeyEvent>() {
//            @Override
//            public void handle(KeyEvent keyEvent) {
//                switch (keyEvent.getCode()) {
//                    case UP:
//                        Bomber.move(0, -1);
//                        break;
//                    case DOWN:
//                        Bomber.getInstance().move(0, 1);
//                        break;
//                    case LEFT:
//                        Bomber.getInstance().move(-1, 0);
//                        break;
//                    case RIGHT:
//                        Bomber.getInstance().move(1, 0);
//                        break;
//                    case SPACE:
//                        Bomber.getInstance().placeBomb();
//                        break;
//                }
//            }
//        });

        // Them scene vao stage
        stage.setScene(scene);
        stage.resizableProperty().setValue(Boolean.FALSE);
        stage.show();

//        createMap();
        createMap1();
        AnimationTimer timer = new AnimationTimer() {
            @Override
            public void handle(long l) {
                render();
                update();
            }
        };
        timer.start();

        Sound.play();

        Entity bomberman = new Bomber(1, 1, Sprite.player_right.getFxImage());
        entities.add(bomberman);

//        while (true) {
//            Game _game = new Game(new Frame());
//            _game.start();
//        }
    }

    public void createMap() {
        for (int i = 0; i < WIDTH; i++) {
            for (int j = 0; j < HEIGHT; j++) {
                Entity object;
                if (j == 0 || j == HEIGHT - 1 || i == 0 || i == WIDTH - 1) {
                    object = new Wall(i, j, Sprite.wall.getFxImage());
                } else if (i % 2 == 0 && j % 2 == 0) {
                    object = new DestroyableTile(i, j, Sprite.brick.getFxImage());
                } else {
                    object = new Grass(i, j, Sprite.grass.getFxImage());
                }

                stillObjects.add(object);
            }
        }
    }

    public void createMap1() {
        char[][] _map;
        List<String> list = new ArrayList<>();
        try {
            URL url = BombermanGame.class.getResource("/levels/Level1.txt");
            assert url != null;
            BufferedReader br = new BufferedReader(new java.io.FileReader(url.getPath()));
            String line;
            while ((line = br.readLine()) != null) {
                list.add(line);
            }
            br.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

        // get input from file
        String[] arrays = list.get(0).trim().split(" ");
        int _level = Integer.parseInt(arrays[0]);
        int _height = Integer.parseInt(arrays[1]);
        int _width = Integer.parseInt(arrays[2]);
        _map = new char[_height][_width];

        for (int i = 0; i < _height; i++) {
            String[] array = list.get(i + 1).trim().split("");
            for (int j = 0; j < _width; j++) {
                _map[i][j] = list.get(i + 1).charAt(j);
            }
        }

        for (int y = 0; y < _height; y++) {
            for (int x = 0; x < _width; x++) {

                Entity object = new Grass(x, y, Sprite.grass.getFxImage());
                char c = _map[y][x];
                switch (c) {
                    // Thêm grass

                    // Thêm Wall
                    case '#':
                        object = new Wall(x, y, Sprite.wall.getFxImage());
                        break;

                    // Thêm Portal
                    case 'x':
                        object = new Portal(x, y, Sprite.portal.getFxImage());
                        break;

                    // Thêm brick
                    case '*':
                        object = new Brick(x, y, Sprite.brick.getFxImage());
                        break;

                    // Thêm Bomber
                    case 'p':
//                        object  = new Bomber(y, x, Sprite.)
//                        _board.addCharacter(new Bomber(Coordinates.tileToPixel(x), Coordinates.tileToPixel(y) + Game.TILES_SIZE, Sprite.player_up.getFxImage()));
//                        Screen.setOffset(0, 0);
//                        _board.addEntity(x + y * _width, new Grass(x, y, Sprite.grass));
                        break;

                    // Thêm balloon
                    case '1':
                        object = new Balloon(x, y, Sprite.balloom_left2.getFxImage());
//                        _board.addCharacter(new Balloon(Coordinates.tileToPixel(x), Coordinates.tileToPixel(y) + Game.TILES_SIZE, _board));
//                        _board.addEntity(x + y * _width, new Grass(x, y, Sprite.grass));
                        break;

                    // Thêm oneal
                    case '2':
                        object = new Oneal(x, y, Sprite.oneal_right2.getFxImage());
//                        _board.addCharacter(new Oneal(Coordinates.tileToPixel(x), Coordinates.tileToPixel(y) + Game.TILES_SIZE, _board));
//                        _board.addEntity(pos, new Grass(x, y, Sprite.grass));
                        break;

//                    // Thêm doll
//                    case '3':
//                        _board.addCharacter(new Doll(Coordinates.tileToPixel(x), Coordinates.tileToPixel(y) + Game.TILES_SIZE, _board));
//                        _board.addEntity(x + y * _width, new Grass(x, y, Sprite.grass));
//                        break;

//                    // Thêm BomItem
//                    case 'b':
//                        LayeredEntity layer = new LayeredEntity(x, y,
//                                new Grass(x, y, Sprite.grass),
//                                new BombItem(x, y, Sprite.powerup_bombs),
//                                new Brick(x, y, Sprite.brick));
//                        _board.addEntity(pos, layer);
//                        break;

//                    // Thêm SpeedItem
//                    case 's':
//                        layer = new LayeredEntity(x, y,
//                                new Grass(x, y, Sprite.grass),
//                                new SpeedItem(x, y, Sprite.powerup_speed),
//                                new Brick(x, y, Sprite.brick));
//                        _board.addEntity(pos, layer);
//                        break;

//                    // Thêm FlameItem
                    case 'f':
                        object = new FlameItem(x, y, Sprite.powerup_flames.getFxImage());
//                        layer = new LayeredEntity(x, y,
//                                new Grass(x, y, Sprite.grass),
//                                new FlameItem(x, y, Sprite.powerup_flames),
//                                new Brick(x, y, Sprite.brick));
//                        _board.addEntity(pos, layer);
                        break;

                    default:
                        object = new Grass(x, y, Sprite.grass.getFxImage());
//                        _board.addEntity(pos, new Grass(x, y, Sprite.grass));
                        break;

                }

                stillObjects.add(object);

            }
        }
    }

    public void update() {
        entities.forEach(Entity::update);
    }

    public void render() {
        gc.clearRect(0, 0, canvas.getWidth(), canvas.getHeight());
//        stillObjects.forEach(g -> g.render(gc));
//        entities.forEach(g -> g.render(gc));
        assert stillObjects != null;
        for (Entity stillObject : stillObjects) {
            assert stillObject != null;
            stillObject.render(gc);
        }
        assert entities != null;
        for (Entity entity : entities) {
            assert entity != null;
            entity.render(gc);
        }
    }
}