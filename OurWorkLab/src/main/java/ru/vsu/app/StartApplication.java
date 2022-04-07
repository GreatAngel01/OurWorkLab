package ru.vsu.app;

import javafx.application.Application;
import javafx.stage.Stage;
import ru.vsu.app.data.ImageFactory;
import ru.vsu.app.data.VisualData;
import ru.vsu.app.visual.MenuDrawer;

import java.io.IOException;

public class StartApplication extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        GameBuilder builder = new GameBuilder();
        builder.build();
        VisualData visualData = new VisualData(builder,new Stage(),new ImageFactory());
        MenuDrawer drawer = new MenuDrawer(visualData);
        drawer.draw();
    }


    public static void main(String[] args) {
        launch();
    }
}