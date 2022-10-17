package uet.oop.bomberman.Menu;


import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.effect.DropShadow;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseDragEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.*;
import javafx.stage.Stage;
import uet.oop.bomberman.BombermanGame;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;



public class ViewManager {
    private static final int WIDTH = 1024;
    private static final int HEIGHT = 768;
    private AnchorPane mainPane;
    private Scene mainScene;
    private Stage mainStage;

    private final static  int MENU_BUTTONS_START_X = 100;
    private final static  int MENU_BUTTONS_START_Y = 200;

    //private BombermanSubscene credistsSubscene;
    private BombermanSubscene helpSubscene;
    private BombermanSubscene scoreSubscene;
    private BombermanSubscene shipChooserScene;

    private BombermanSubscene sceneToHide;

    List<BombermanButton> menuButtons;
    //List<ShipPicker>  shipsList;
    //private SHIP choosenShip;

    public ViewManager(){
        menuButtons = new ArrayList<>();
        mainPane = new AnchorPane();
        mainScene = new Scene(mainPane,WIDTH,HEIGHT);
        mainStage = new Stage();
        mainStage.setScene(mainScene);
        createSubScenes();
        createButtons();
        createBackground();
        createLogo();
    }

    private void showSubScene(BombermanSubscene subScene) {
        if( sceneToHide == subScene) {
            sceneToHide.moveSubScene();
        }
        else if (sceneToHide == null){
            //sceneToHide.moveSubScene();
            subScene.moveSubScene();
            sceneToHide = subScene;
        }
        else {
            if(sceneToHide.isHidden != true)
                sceneToHide.moveSubScene();
            subScene.moveSubScene();
            sceneToHide = subScene;
        }
    }
    private void addMenuButton(BombermanButton button) {
        button.setLayoutX(MENU_BUTTONS_START_X);
        button.setLayoutY(MENU_BUTTONS_START_Y + menuButtons.size() * 120);
        menuButtons.add(button);
        mainPane.getChildren().add(button);
    }

    public Stage getMainStage() {
        return mainStage;
    }

    private void createButtons() {
        //createLogo();
        createStartButtons();
        createScoreButtons();
        createBackground();
        //createCreditsButton();
        createHelpButton();
        createExitButton();
    }

    private void createSubScenes() {
//        credistsSubscene = new BombermanSubscene();
//        mainPane.getChildren().add(credistsSubscene);

        helpSubscene = new BombermanSubscene();
        mainPane.getChildren().add(helpSubscene);

        createInstructionSubscene();

        scoreSubscene = new BombermanSubscene();
        mainPane.getChildren().add(scoreSubscene);

//        shipChooserScene = new BombermanSubscene();
//        mainPane.getChildren().add(shipChooserScene);

        //createShipChooserSubScene();
    }
//    private  void createShipChooserSubScene() {
//        shipChooserScene = new BombermanSubscene();
//        mainPane.getChildren().add(shipChooserScene);
//
//        InfoLabel chooseShipLabel = new InfoLabel("CHOOSE YOUR SHIP");
//        chooseShipLabel.setLayoutX(110);
//        chooseShipLabel.setLayoutY(25);
//        shipChooserScene.getPane().getChildren().add(chooseShipLabel);
//        shipChooserScene.getPane().getChildren().add(createShipToChoose());
//        shipChooserScene.getPane().getChildren().add(createButtonToStart());
//    }

//    private HBox createShipToChoose() {
//        HBox box = new HBox();
//        box.setSpacing(40);
//        shipsList = new ArrayList<>();
//        for(SHIP ship : SHIP.values()) {
//            ShipPicker shipToPick = new ShipPicker(ship);
//            shipsList.add(shipToPick);
//            box.getChildren().add(shipToPick);
//            shipToPick.setOnMouseClicked(new EventHandler<MouseEvent>() {
//                @Override
//                public void handle(MouseEvent event) {
//                    for (ShipPicker ship : shipsList) {
//                        ship.setIsCircleChoosen(false);
//                    }
//                    shipToPick.setIsCircleChoosen(true);
//                    choosenShip = shipToPick.getShip();
//                }
//            });
//        }
//        box.setLayoutX(300 - (118*2) + 50);
//        box.setLayoutY(100);
//        return box;
//    }

    //    private BombermanButton createButtonToStart() {
//        BombermanButton startButton = new BombermanButton("START");
//        startButton.setLayoutX(350);
//        startButton.setLayoutY(300);
//
//        startButton.setOnAction(new EventHandler<ActionEvent>() {
//            @Override
//            public void handle(ActionEvent event) {
//                if(choosenShip != null) {
//                    GameViewManager gameManager = new GameViewManager();
//                    gameManager.createNewGame(mainStage, choosenShip);
//                }
//            }
//        });
//        return startButton;
//    }
    private void createInstructionSubscene() {
        helpSubscene = new BombermanSubscene();
        mainPane.getChildren().add(helpSubscene);

        ImageView moveUpButton = new ImageView("lineLight24.png");
        moveUpButton.setFitHeight(40);
        moveUpButton.setFitWidth(40);
        ImageView moveDownButton = new ImageView("lineLight25.png");
        moveDownButton.setFitHeight(40);
        moveDownButton.setFitWidth(40);
        ImageView moveLeftButton = new ImageView("lineLight22.png");
        moveLeftButton.setFitHeight(40);
        moveLeftButton.setFitWidth(40);
        ImageView moveRightButton = new ImageView("lineLight23.png");
        moveRightButton.setFitHeight(40);
        moveRightButton.setFitWidth(40);

        BombermanButton setBombButton = new BombermanButton();
        setBombButton.setText("SPACE");
        setBombButton.setPrefHeight(40);
        setBombButton.setPrefWidth(80);

        InfoLabel moveUp = new InfoLabel("     : Di chuyen len");
        InfoLabel moveDown = new InfoLabel("    : Di chuyen xuong");
        InfoLabel moveLeft = new InfoLabel("    : Di chuyen sang trai");
        InfoLabel moveRight = new InfoLabel("    : Di chuyen phai");
        InfoLabel setBomb = new InfoLabel("    : An de dat bom");

        moveUp.setXY(140, 30);
        moveDown.setXY(140, (int)moveUp.getLayoutY() + 55);
        moveLeft.setXY(140,(int)moveDown.getLayoutY() + 55);
        moveRight.setXY(140, (int)moveLeft.getLayoutY() + 55);
        setBomb.setXY(140, (int)moveRight.getLayoutY() + 55);

        moveUpButton.setLayoutX(80);
        moveUpButton.setLayoutY(moveUp.getLayoutY());
        moveDownButton.setLayoutX(80);
        moveDownButton.setLayoutY(moveDown.getLayoutY());
        moveLeftButton.setLayoutX(80);
        moveLeftButton.setLayoutY(moveLeft.getLayoutY());
        moveRightButton.setLayoutX(80);
        moveRightButton.setLayoutY(moveRight.getLayoutY());

        setBombButton.setXY_Button(40, setBomb.getLayoutY());

        helpSubscene.getPane().getChildren().add(moveUp);
        helpSubscene.getPane().getChildren().add(moveDown);
        helpSubscene.getPane().getChildren().add(moveLeft);
        helpSubscene.getPane().getChildren().add(moveRight);
        helpSubscene.getPane().getChildren().add(setBomb);

        helpSubscene.getPane().getChildren().add(moveUpButton);
        helpSubscene.getPane().getChildren().add(moveDownButton);
        helpSubscene.getPane().getChildren().add(moveLeftButton);
        helpSubscene.getPane().getChildren().add(moveRightButton);
        helpSubscene.getPane().getChildren().add(setBombButton);
    }
    private void createStartButtons() {
        BombermanButton startButton = new BombermanButton("Play");
        addMenuButton(startButton);

        startButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                BombermanGame game = new BombermanGame();
                try {
                    game.start(mainStage);
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
        });
    }

    private void createScoreButtons(){
        BombermanButton scoreButton = new BombermanButton("Scores");
        addMenuButton(scoreButton);

        scoreButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                showSubScene(scoreSubscene);
            }
        });
    }

    private void createHelpButton() {
        BombermanButton helpButton = new BombermanButton("Help");
        addMenuButton(helpButton);

        helpButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                showSubScene(helpSubscene);
            }
        });
    }

//    private void createCreditsButton() {
//        BombermanButton creditButton = new BombermanButton("Credits");
//        addMenuButton(creditButton);
//
//        creditButton.setOnAction(new EventHandler<ActionEvent>() {
//            @Override
//            public void handle(ActionEvent event) {
//                showSubScene(credistsSubscene);
//            }
//        });
//    }

    private void createExitButton() {
        BombermanButton exitButton = new BombermanButton("Exit");
        addMenuButton(exitButton);

        exitButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                mainStage.close();
            }
        });
    }
    private void createBackground() {
        Image backgroundImage = new Image("backgroundDesert.png", 768, 1024,false, true);
        BackgroundImage background = new BackgroundImage(backgroundImage, BackgroundRepeat.REPEAT, BackgroundRepeat.REPEAT, BackgroundPosition.DEFAULT,null);
        mainPane.setBackground(new Background(background));
    }

    private void createLogo() {
        ImageView logo =  new ImageView("Logo1.png");
        //logo.setFitHeight(200);
        logo.setLayoutX(300);
        logo.setLayoutY(50);
        logo.setOnMouseEntered(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                logo.setEffect(new DropShadow());
            }
        });

        logo.setOnMouseExited(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                logo.setEffect(null);
            }
        });
        mainPane.getChildren().add(logo);
    }


}
