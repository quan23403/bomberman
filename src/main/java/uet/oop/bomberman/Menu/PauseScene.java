package uet.oop.bomberman.Menu;

import javafx.animation.TranslateTransition;
import javafx.scene.SubScene;
import javafx.scene.image.Image;
import javafx.scene.layout.*;
import javafx.util.Duration;

public class PauseScene extends SubScene {
    private final static String FONT_PATH = "src/main/resources/kenvector_future.ttf";
    private final static String BACKGROUND_IMAGE = "pausescreen1.png";
    public static boolean isHidden = true;
    public PauseScene() {
        super(new AnchorPane(), 400, 250);
        prefWidth(400);
        prefHeight(250);

        BackgroundImage image = new BackgroundImage(new Image(BACKGROUND_IMAGE,400,250,false,true),
                BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.DEFAULT,null);

        AnchorPane root2 = (AnchorPane) this.getRoot();

        root2.setBackground(new Background(image));
        //isHidden = true;
        setLayoutX(1400);
        setLayoutY(80);
    }

    public void moveSubScene() {
        TranslateTransition transition = new TranslateTransition();
        transition.setDuration(Duration.seconds(0.3));
        transition.setNode(this);
        if(isHidden) {
            transition.setToX(-1100);
            isHidden = false;
        } else {
            transition.setToX(0);
            isHidden = true;
        }
        transition.play();
    }

    public AnchorPane getPane() {
        return (AnchorPane) this.getRoot();
    }
}
