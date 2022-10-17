package uet.oop.bomberman.Menu;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.BackgroundPosition;
import javafx.scene.layout.BackgroundRepeat;
import javafx.scene.text.Font;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;

public class InfoLabel extends Label {
    public final static String FONT_PATH = "src/main/resources/kenvector_future.ttf";

    public final static String BACKGROUND_IMAGE = "yellow_button13.png";

    public InfoLabel(String text) {
        setPrefWidth(340);
        setPrefHeight(40);
        //setPadding(new Insets(40,40,40,40));
        setText(text);
        setWrapText(true);
        setLabelFont();
        setAlignment(Pos.CENTER_LEFT);
        BackgroundImage backgroundImage = new BackgroundImage(new Image(BACKGROUND_IMAGE, 380, 49, false, true)
                , BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.DEFAULT, null);
        setBackground(new Background(backgroundImage));
    }

    public void setXY(int x, int y) {
        this.setLayoutX(x);
        this.setLayoutY(y);
    }
    private void setLabelFont() {
        try {
            setFont(Font.loadFont(new FileInputStream(new File(FONT_PATH)),20));
        } catch (FileNotFoundException e) {
            setFont(Font.font("Verdana",20));
        }
    }
}

