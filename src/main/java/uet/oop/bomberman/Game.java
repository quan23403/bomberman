package uet.oop.bomberman;

import uet.oop.bomberman.graphics.Screen;
import uet.oop.bomberman.input.Keyboard;

import java.awt.*;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;
import java.awt.image.DataBufferInt;

public class Game extends Canvas {
    // TODO: Create the game attributes here
    // Screen
    public static final int TILES_SIZE = 16;
    public static final int WIDTH = TILES_SIZE * (31 / 2);
    public static final int HEIGHT = 13 * TILES_SIZE;
    public static final int SCALE = 3;
    public static final String TITLE = "Bomberman Game";

    // Constaints attributes
    private static final int BOMBRATE = 1;
    private static final int BOMBRADIUS = 1;
    private static final double BOMBERSPEED = 1.0; // bomber speed in tiles per second.

    public static final int TIME = 200;
    public static final int POINTS = 0;

    protected static int SCREENDELAY = 3;

    // Game attributes
    protected static int bombRate = BOMBRATE;
    protected static int bombRadius = BOMBRADIUS;
    protected static double bomberSpeed = BOMBERSPEED;

    protected int _screenDelay = SCREENDELAY;

    private Keyboard _input;
    private boolean isRunning = false;
    private boolean isPaused = true;

    private Board _board;
    private Screen screen;
    private Frame _frame;

    private BufferedImage image = new BufferedImage(WIDTH, HEIGHT, BufferedImage.TYPE_INT_RGB);
    private int[] pixels = ((DataBufferInt) image.getRaster().getDataBuffer()).getData();

    /**
     * Constructor.
     */
    public Game(Frame frame) {
        _frame = frame;
        _frame.setTitle(TITLE);

        screen = new Screen(WIDTH, HEIGHT);
        _input = new Keyboard();
        _board = new Board(this, _input, screen);
        addKeyListener(_input);
    }

    /**
     * render the game.
     */
    private void renderGame() {
        BufferStrategy bs = getBufferStrategy();
        if (bs == null) {
            createBufferStrategy(3);
            return;
        }

        screen.clear();

        _board.render(screen);

        for (int i = 0; i < pixels.length; i++) {
            pixels[i] = screen._pixels[i];
        }

        Graphics g = bs.getDrawGraphics();
        g.drawImage((Image) image, 0, 0, (int) getWidth(), (int) getHeight(), null);
        g.dispose();
        bs.show();
    }

    /**
     * render the screen.
     */
    private void renderScreen() {
        BufferStrategy bs = getBufferStrategy();
        if (bs == null) {
            createBufferStrategy(3);
            return;
        }

        screen.clear();

        Graphics g = bs.getDrawGraphics();

        _board.drawScreen(g);

        g.dispose();
        bs.show();
    }

    /**
     * update game state.
     */
    private void updateGame() {
        _board.update();
        _input.update();
    }

    /**
     * Start the game
     */
    public void start() {
        isRunning = true;

        long lastTime = System.nanoTime();
        long timer = System.currentTimeMillis();
        final double ns = 1000000000.0 / 60.0;
        double delta = 0;
        int frames = 0;
        int updates = 0;
        requestFocus();

        while (isRunning) {
            long now = System.nanoTime();
            delta += (now - lastTime) / ns;
            lastTime = now;
            boolean shouldRender = false;

            while (delta >= 1) {
                updates++;
                updateGame();
                delta--;
                shouldRender = true;
            }

            try {
                Thread.sleep(2);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            if (shouldRender) {
                frames++;
            }

            if (isPaused) {
                if (_screenDelay <= 0) {
                    isPaused = false;
                    _board.setShow(1);
                }
                renderScreen();
            } else {
                renderGame();
            }
        }
    }

    // setter and getter.
    public static int getBombRate() {
        return bombRate;
    }

    public static void setBombRate(int bombRate) {
        Game.bombRate = bombRate;
    }

    public static int getBombRadius() {
        return bombRadius;
    }

    public static void setBombRadius(int bombRadius) {
        Game.bombRadius = bombRadius;
    }

    public static double getBomberSpeed() {
        return bomberSpeed;
    }

    public static void setBomberSpeed(double bomberSpeed) {
        Game.bomberSpeed = bomberSpeed;
    }

    public void resetScreenDelay() {
        _screenDelay = SCREENDELAY;
    }

    public void pause() {
        isPaused = true;
        assert _board != null;
        _board.setShow(0);
    }

    public boolean isPaused() {
        return isPaused;
    }

    /**
     * bomber speed after power up.
     */
    public static void addBomberSpeed(double i) {
        bomberSpeed += i;
    }

    /**
     * bomb radius after power up.
     */
    public static void addBombRadius(int i) {
        bombRadius += i;
    }

    /**
     * bomb rate after power up.
     */
    public static void addBombRate(int i) {
        bombRate += i;
    }
}