package ru.vsu.app;


import javafx.fxml.FXML;
import javafx.scene.canvas.Canvas;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.input.MouseEvent;
import ru.vsu.app.services.GameService;

import static java.lang.Integer.parseInt;

public class HController {
    @FXML
    public TextArea chSpeed;
    @FXML
    public TextArea mapsSize;
    @FXML
    public TextArea worldSize;
    @FXML
    public Button startButton;
    @FXML
    public Canvas canvas;


    public void mouseClickedStart(MouseEvent event) {
        GameService service = new GameService();
        String[] size = mapsSize.getText().split(":");
        service.createGame(parseInt(size[0]), parseInt(size[1]), parseInt(worldSize.getText()));
    }

}