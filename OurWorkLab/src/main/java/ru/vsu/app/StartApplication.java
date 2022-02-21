package ru.vsu.app;

import javafx.application.Application;
import javafx.stage.Stage;
import ru.vsu.app.services.GameService;
import ru.vsu.app.data.GameData;
import ru.vsu.app.visual.HController;

import java.io.IOException;

public class StartApplication extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        GameData gameData = new GameData();
        GameService service = new GameService(gameData);
        HController controller = new HController(stage, gameData, service);
        controller.createMenu();

    }


    public static void main(String[] args) {
        launch();
    }
}