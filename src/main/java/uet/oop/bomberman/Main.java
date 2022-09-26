package uet.oop.bomberman;

import javafx.application.Application;
import javafx.stage.Stage;

import java.io.IOException;

public class Main extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        BombermanGame game = new BombermanGame();
        game.start(stage);
    }

    public static void main(String[] args) {
        BombermanGame.main(args);
    }
}
