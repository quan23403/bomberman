package uet.oop.bomberman;

import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.input.KeyCode;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import org.w3c.dom.Text;
import uet.oop.bomberman.Menu.BombermanButton;
import uet.oop.bomberman.Menu.InfoLabel;
import uet.oop.bomberman.Menu.PauseScene;
import uet.oop.bomberman.Menu.ViewManager;
import uet.oop.bomberman.audio.MyAudioPlayer;
import uet.oop.bomberman.entities.Entity;
import uet.oop.bomberman.entities.bomb.Bomb;
import uet.oop.bomberman.entities.bomb.Flame;
import uet.oop.bomberman.entities.character.Bomber;
import uet.oop.bomberman.entities.character.enemies.*;
import uet.oop.bomberman.entities.fixed.Grass;
import uet.oop.bomberman.entities.fixed.Portal;
import uet.oop.bomberman.entities.fixed.Wall;
import uet.oop.bomberman.entities.fixed.Brick;
import uet.oop.bomberman.entities.item.BombItem;
import uet.oop.bomberman.entities.item.FlameItem;
import uet.oop.bomberman.entities.item.Item;
import uet.oop.bomberman.entities.item.SpeedItem;
import uet.oop.bomberman.graphics.Sprite;

import java.io.BufferedReader;
import java.awt.*;
import java.io.IOException;
import java.net.URL;
import java.util.*;
import java.util.List;

import static uet.oop.bomberman.Menu.ViewManager.musicPlayer;

public class BombermanGame extends Application {

    public static int WIDTH = 31;
    public static int HEIGHT = 13;
    public static int level = 1;

    private GraphicsContext gc;
    private boolean paused = false;
    private boolean muted = false;
    private Canvas canvas;

    private Runnable newGameDo;
    private List<String> input = new ArrayList<>();

    private AnchorPane mainPane;
    private PauseScene pauseScene;
    private Stage mainStage = new Stage();

    private String BUTTON_MENU_PRESS_STYLE = "-fx-background-color: transparent; -fx-background-image : url('grey_button3.png');";
    private String BUTTON_MENU_FREE_STYLE = "-fx-background-color: transparent; -fx-background-image : url('grey_button4.png');";
    private BombermanButton Resume;
    private BombermanButton NewGame;

    private int xStart;
    private int yStart;
    public static final List<Enemy> enemies = new ArrayList<>();
    public static List<Entity> stillObjects = new ArrayList<>();
    public static final List<Flame> flameList = new ArrayList<>();
    public int startBomb = 1;
    public int startSpeed = 2;
    public int startFlame = 1;
    public static Bomber myBomber;
    public static int[][] map = new int[HEIGHT][WIDTH];
    public static int[][] mapAStar = new int[HEIGHT][WIDTH];
    //public static MyAudioPlayer musicPlayer = new MyAudioPlayer(MyAudioPlayer.BACKGROUND_MUSIC);
    private Group root = new Group();
    public MyAudioPlayer getMusicPlayer() {
        return musicPlayer;
    }

    public void setMusicPlayer(MyAudioPlayer _musicPlayer) {
        musicPlayer = _musicPlayer;
    }


//    public static void main(String[] args) {
//        Application.launch(BombermanGame.class);
//    }

    @Override
    public void start(Stage stage) throws IOException {

        mainStage = stage;
        mainPane = new AnchorPane();

        //musicPlayer.();
//        load();
        load(level);
        // Tao Canvas


        canvas = new Canvas(Sprite.SCALED_SIZE * WIDTH, Sprite.SCALED_SIZE * HEIGHT);
        gc = canvas.getGraphicsContext2D();

        // Tao root container
        //root = new Group();
        mainPane.getChildren().add(canvas);
        makePauseScene();
        // Tao scene
        Scene scene = new Scene(mainPane, 990, 416);


        // Them scene vao stage
        stage.setScene(scene);
        stage.setTitle("Bomberman Game");

//        stage.getIcons().add(img);
        stage.setResizable(true);
        stage.setX(300);
        stage.setY(200);
        stage.show();


        AnimationTimer timer = new AnimationTimer() {
            @Override
            public void handle(long l) {
                if (!paused) {
                    render();
                    update();
                } else {
                    //halted
                }
                if (muted) {
                    musicPlayer.stop();
                } else {
                    musicPlayer.loop();
                }
            }
        };
        timer.start();
        scene.setOnKeyPressed(event -> {
            myBomber.handleKeyPressedEvent(event.getCode());
            if (event.getCode() == KeyCode.K) {
                paused = !paused;
                pauseScene.moveSubScene();
            }
            if (event.getCode() == KeyCode.M) {
                if (muted) {
                    muted = false;
                } else {
                    muted = true;
                }
            }
        });
        scene.setOnKeyReleased(event -> myBomber.handleKeyReleasedEvent(event.getCode()));
    }

    public void update() {

        // không sửa thành for each trong game không sẽ bị lỗi
        for (int i = 0; i < enemies.size(); i++) {
            enemies.get(i).update();
        }
        for (int i = 0; i < flameList.size(); i++) {
            flameList.get(i).update();
        }

        myBomber.update();
        List<Bomb> bombs = myBomber.getBombs();
        for (Bomb bomb : bombs) {
            bomb.update();
        }

        for (int i = 0; i < stillObjects.size(); i++) {
            stillObjects.get(i).update();
        }
        handleCollisions();
        checkCollisionFlame();
    }

    public void load(int _level) {
        try {
            URL url = BombermanGame.class.getResource("/levels/Level" + _level + ".txt");
            assert url != null;
            BufferedReader br = new BufferedReader(new java.io.FileReader(url.getPath()));
            String line;
            while ((line = br.readLine()) != null) {
                input.add(line);
            }
            br.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

        String[] arrays = input.get(0).trim().split(" ");

        HEIGHT = Integer.parseInt(arrays[1]);
        WIDTH = Integer.parseInt(arrays[2]);
        enemies.removeAll(enemies);
        stillObjects.removeAll(stillObjects);
        flameList.removeAll(flameList);
//        if(myBomber != null)
//            myBomber.getBombs().removeAll(myBomber.getBombs());
        // print all list
//        for (String s : input) {
//            System.out.println(s);
//        }
        createMap();
    }

    public void createMap() {
        createMatrixCoordinates();
        for (int i = 0; i < HEIGHT; i++) {

            String r = input.get(i + 1);

            for (int j = 0; j < WIDTH; j++) {

                if (r.charAt(j) == '#') {
                    stillObjects.add(new Wall(j, i, Sprite.wall.getFxImage()));
                    map[i][j] = 1;
                    mapAStar[i][j] = -1;
                } else {
                    stillObjects.add(new Grass(j, i, Sprite.grass.getFxImage()));
                    if (r.charAt(j) == '*') {
                        stillObjects.add(new Brick(j, i, Sprite.brick.getFxImage()));
                        map[i][j] = 1;
                        mapAStar[i][j] = -1;
                    }
                    if (r.charAt(j) == 'x') {
                        stillObjects.add(new Portal(j, i, Sprite.portal.getFxImage()));
                        stillObjects.add(new Brick(j, i, Sprite.brick.getFxImage()));
                        map[i][j] = 1;
                        mapAStar[i][j] = -1;
                    }
                    if (r.charAt(j) == '1') {
                        enemies.add(new Balloon(j, i, Sprite.balloom_left1.getFxImage()));
                        //map[i][j] = 0;
                    }
                    if (r.charAt(j) == '2') {
//                        enemies.add(new Oneal(j, i, Sprite.oneal_left1.getFxImage(), myBomber));
                        enemies.add(new Oneal(j, i, Sprite.oneal_left1.getFxImage()));
                        //map[i][j] = 0;
                    }
                    if (r.charAt(j) == '3') {
                        enemies.add(new Minvo(j, i, Sprite.minvo_left1.getFxImage()));
                        //map[i][j] = 0;
                    }
                    if (r.charAt(j) == '4') {
                        enemies.add(new Kondoria(j, i, Sprite.kondoria_left1.getFxImage()));
                        //map[i][j] = 0;
                    }
                    if (r.charAt(j) == '5') {
                        enemies.add(new Doll(j, i, Sprite.doll_left1.getFxImage()));
                        //map[i][j] = 0;
                    }
                    if (r.charAt(j) == 'b') {
                        stillObjects.add(new BombItem(j, i, Sprite.powerup_bombs.getFxImage()));
                        stillObjects.add(new Brick(j, i, Sprite.brick.getFxImage()));
                        map[i][j] = 1;
                        mapAStar[i][j] = -1;
                    }
                    if (r.charAt(j) == 'f') {
                        stillObjects.add(new FlameItem(j, i, Sprite.powerup_flames.getFxImage()));
                        stillObjects.add(new Brick(j, i, Sprite.brick.getFxImage()));
                        map[i][j] = 1;
                        mapAStar[i][j] = -1;
                    }
                    if (r.charAt(j) == 's') {
                        stillObjects.add(new SpeedItem(j, i, Sprite.powerup_speed.getFxImage()));
                        stillObjects.add(new Brick(j, i, Sprite.brick.getFxImage()));
                        map[i][j] = 1;
                        mapAStar[i][j] = -1;
                    }
                    if (r.charAt(j) == 'p') {
                        myBomber = new Bomber(j, i, Sprite.player_right.getFxImage());
                        xStart = j;
                        yStart = i;
                        map[i][j] = 0;
                        mapAStar[i][j] = 0;
                    }
                    if (r.charAt(j) == ' ') {
                        map[i][j] = 0;
                    }
                }
            }
        }
        stillObjects.sort(new Layer());
    }

    public void createMatrixCoordinates() {
        for (int i = 0; i < HEIGHT; i++) {
            for (int j = 0; j < WIDTH; j++) {
                map[i][j] = 0;
                mapAStar[i][j] = 0;
            }
        }
    }

    public void handleCollisions() {
        List<Bomb> bombs = myBomber.getBombs();
        Rectangle r1 = myBomber.getBounds();

        //Bomber vs StillObjects
        for (Entity stillObject : stillObjects) {
            Rectangle r2 = stillObject.getBounds();
            if (r1.intersects(r2)) {
                if (myBomber.getLayer() == stillObject.getLayer() && stillObject instanceof Item) {
                    if (stillObject instanceof BombItem) {
                        startBomb++;
                        myBomber.setBombRemain(startBomb);
                    } else if (stillObject instanceof SpeedItem) {
                        startSpeed += 2;
                        myBomber.setSpeed(startSpeed);
                    } else if (stillObject instanceof FlameItem) {
                        startFlame++;
                        myBomber.setRadius(startFlame);
                    }

                    stillObjects.remove(stillObject);
                    // âm thanh ăn item
                    MyAudioPlayer powerUpAudio = new MyAudioPlayer(MyAudioPlayer.POWER_UP);
                    powerUpAudio.play();
                    map[myBomber.getY() / Sprite.SCALED_SIZE][myBomber.getX() / Sprite.SCALED_SIZE] = 0;
                    mapAStar[myBomber.getY() / Sprite.SCALED_SIZE][myBomber.getX() / Sprite.SCALED_SIZE] = 0;
                    myBomber.stay();
                } else if (myBomber.getLayer() == stillObject.getLayer() && stillObject instanceof Portal) {
                    if (enemies.size() == 0) {
                        // load next level

                        level++;
                        if (level > 4) {
                            level = 0;
                        }
                        load(level);

                        // âm thanh ăn item
                        MyAudioPlayer powerUpAudio = new MyAudioPlayer(MyAudioPlayer.POWER_UP);
                        powerUpAudio.play();
                    }
                } else if (myBomber.getLayer() >= stillObject.getLayer()) {
                    myBomber.move();
                } else {
                    myBomber.stay();
                }
                break;
            }
        }
        //Bomber vs Enemies
        for (Enemy enemy : enemies) {
            Rectangle r2 = enemy.getBounds();
            if (r1.intersects(r2)) {
                myBomber.setAlive(false);
                startBomb = 1;
                startFlame = 1;
                startSpeed = 1;
                if (!myBomber.isAlive()) {
                    Timer count = new Timer();
                    count.schedule(new TimerTask() {
                        @Override
                        public void run() {
                            myBomber = new Bomber(xStart, yStart, Sprite.player_right.getFxImage());
                            count.cancel();
                        }
                    }, 500, 1);
                    MyAudioPlayer powerUpAudio = new MyAudioPlayer(MyAudioPlayer.DEAD);
                    powerUpAudio.play();

                }
//                myBomber.setCoordinate(2,1);
            }
        }
        //Enemies vs Bombs
        for (Enemy enemy : enemies) {
            Rectangle r2 = enemy.getBounds();
            for (Bomb bomb : bombs) {
                Rectangle r3 = bomb.getBounds();
                if (!bomb.isAllowedToPassThrough(enemy) && r2.intersects(r3)) {
                    enemy.stay();
                    break;
                }
            }
        }

        //Enemies vs StillObjects
        for (Enemy enemy : enemies) {
            Rectangle r2 = enemy.getBounds();
            for (Entity stillObject : stillObjects) {
                Rectangle r3 = stillObject.getBounds();
                if (r2.intersects(r3)) {
                    if (enemy.getLayer() >= stillObject.getLayer()) {
                        enemy.move();
                    } else {
                        enemy.stay();
                    }
                    break;
                }
            }
        }
    }

    public void checkCollisionFlame() {
        //if(explosionList != null){
        for (Flame flame : flameList) {
            Rectangle r1 = flame.getBounds();
            for (Entity stillObject : stillObjects) {
                Rectangle r2 = stillObject.getBounds();
                if (r1.intersects(r2) && !(stillObject instanceof Item)) {
                    stillObject.setAlive(false);
                    map[stillObject.getY() / Sprite.SCALED_SIZE][stillObject.getX() / Sprite.SCALED_SIZE] = 0;
                    mapAStar[stillObject.getY() / Sprite.SCALED_SIZE][stillObject.getX() / Sprite.SCALED_SIZE] = 0;
                }
            }
            for (Enemy enemy : enemies) {
                Rectangle r2 = enemy.getBounds();
                if (r1.intersects(r2)) {
                    enemy.setAlive(false);
                    MyAudioPlayer powerUpAudio = new MyAudioPlayer(MyAudioPlayer.ENEMY_DEAD);
                    powerUpAudio.play();
                }
            }
            Rectangle r2 = myBomber.getBounds();
            if (r1.intersects(r2)) {
                myBomber.setAlive(false);
                startBomb = 1;
                startFlame = 1;
                startSpeed = 1;
                if (!myBomber.isAlive()) {
                    Timer count = new Timer();
                    count.schedule(new TimerTask() {
                        @Override
                        public void run() {
                            myBomber = new Bomber(xStart, yStart, Sprite.player_right.getFxImage());
                            count.cancel();
                        }
                    }, 500, 1);
                    MyAudioPlayer powerUpAudio = new MyAudioPlayer(MyAudioPlayer.DEAD);
                    powerUpAudio.play();
                }
                //createMap();
            }
        }
    }

    public void makePauseScene() {
        pauseScene = new PauseScene();
        mainPane.getChildren().add(pauseScene);

        InfoLabel pause = new InfoLabel("PAUSE", "grey_button03.png");

        pauseScene.getPane().getChildren().add(pause);
    }

    public void render() {
        gc.clearRect(0, 0, canvas.getWidth(), canvas.getHeight());
        for (int i = stillObjects.size() - 1; i >= 0; i--) {
            stillObjects.get(i).render(gc);
        }
        enemies.forEach(g -> g.render(gc));
        List<Bomb> bombs = myBomber.getBombs();
        for (Bomb bomb : bombs) {
            bomb.render(gc);
        }
        myBomber.render(gc);
        flameList.forEach(g -> g.render(gc));
    }

//    public void cleanup() {}
//    public void startGame(Stage stage) {
//        // initialisation from start method goes here
//        NewGame.setOnAction(e -> {
//            //pauseScene.moveSubScene();
//            restart(stage);
//        });
//        stage.show();
//    }
//
//    public void restart(Stage stage){
//        cleanup();
//        startGame(stage);
//    }
}