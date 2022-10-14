package uet.oop.bomberman.graphics;

import javafx.fxml.Initializable;
import uet.oop.bomberman.BombermanGame;

import java.io.IOException;

public class Controller implements Initializable {
    private BombermanGame scene = new BombermanGame();

    @Override
    public void initialize(java.net.URL location, java.util.ResourceBundle resources) {
    }

    public void changeScreen(javafx.event.ActionEvent event) throws IOException {
        //BombermanGame.ai=0;
        javafx.stage.Stage stage = (javafx.stage.Stage)((javafx.scene.Node) event.getSource()).getScene().getWindow();
        scene.start(stage);
    }
}
