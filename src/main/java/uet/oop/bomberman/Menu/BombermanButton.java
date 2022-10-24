package uet.oop.bomberman.Menu;

import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.effect.DropShadow;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.text.Font;
import uet.oop.bomberman.entities.bomb.Bomb;
import uet.oop.bomberman.entities.character.Bomber;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

public class BombermanButton extends Button {
    private String FONT_PATH = "src/main/resources/kenvector_future.ttf";
    private String BUTTON_PRESSED_STYLE = "-fx-background-color: transparent; -fx-background-image : url('yellow_button03.png');";
    private String BUTTON_FREE_STYLE = "-fx-background-color: transparent; -fx-background-image : url('yellow_button04.png');";

    public BombermanButton() {
        //setStyle(BUTTON_FREE_STYLE);
    }

    public void set_Style(String url) {
        this.setStyle(url);
    }
    public BombermanButton(String text) {
        setText(text);
        setButtonFont();
        setPrefHeight(45);
        setPrefWidth(190);
        setStyle(BUTTON_FREE_STYLE);
        initializeButtonListeners();
    }

    public BombermanButton(String text, double height, double width, String style) {
        setText(text);
        setButtonFont();
        setPrefHeight(height);
        setPrefWidth(width);
        setStyle(style);
    }
    private void setButtonFont(){
        try {
            setFont(Font.loadFont(new FileInputStream(FONT_PATH), 23));
        } catch (FileNotFoundException e) {
            setFont(Font.font("Verdana", 23));
        }
    }

    private void setButtonPressedStyle() {
        setStyle(BUTTON_PRESSED_STYLE);
        setPrefHeight(40);
        setLayoutY(getLayoutY() + 4);
    }

    private void setButtonReleasedStyle() {
        setStyle(BUTTON_FREE_STYLE);
        setPrefHeight(40);
        setLayoutY(getLayoutY() - 4);
    }

    private void initializeButtonListeners() {
        setOnMousePressed(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                if (mouseEvent.getButton().equals(MouseButton.PRIMARY)) {
                    setButtonPressedStyle();
                }
            }
        });

        setOnMouseReleased(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                if(mouseEvent.getButton().equals(MouseButton.PRIMARY)) {
                    setButtonReleasedStyle();
                }
            }
        });

        setOnMouseEntered(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                setEffect(new DropShadow());
            }
        });

        setOnMouseExited(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                setEffect(null);
            }
        });
    }

    public void setXY_Button(double x, double y) {
        this.setLayoutX(x);
        this.setLayoutY(y);
    }

    public void setBUTTON_PRESSED_STYLE(String s) {
        this.BUTTON_PRESSED_STYLE = s;
    }

    public void setBUTTON_FREE_STYLE(String s) {
        this.BUTTON_FREE_STYLE = s;
    }

}
