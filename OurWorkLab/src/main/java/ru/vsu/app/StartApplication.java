package ru.vsu.app;

import javafx.application.Application;
import javafx.stage.Stage;

import java.io.IOException;

public class StartApplication extends Application {
    @Override
    public void start(Stage stage) throws IOException {
       HController  controller = new HController();
       controller.createMenu(stage);

    }


    public static void main(String[] args) {
        launch();
    }
}