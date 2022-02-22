package ru.vsu.app;

import javafx.application.Application;
import javafx.stage.Stage;
import ru.vsu.app.visual.VisualController;

import java.io.IOException;

public class StartApplication extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        GameBuilder builder = new GameBuilder();
        builder.build();
        VisualController controller = new VisualController(builder);
        controller.createMenu();
    }


    public static void main(String[] args) {
        launch();
    }
}