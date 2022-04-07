package ru.vsu.app.data;

import javafx.scene.Camera;
import javafx.scene.canvas.Canvas;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import ru.vsu.app.GameBuilder;
import ru.vsu.app.models.creatures.Enemy;

import java.util.HashMap;

public class VisualData {
    private final GameBuilder builder;
    private final Stage stage;
    private Pane nowPane;
    private Camera camera;
    private Canvas charaCanvas;

    public HashMap<Enemy, Canvas> getEnemyCanvases() {
        return enemyCanvases;
    }

    public void setEnemyCanvases(HashMap<Enemy, Canvas> enemyCanvases) {
        this.enemyCanvases = enemyCanvases;
    }

    private HashMap<Enemy,Canvas> enemyCanvases = new HashMap<>();
    private final ImageFactory factory;


    public Canvas getCharaCanvas() {
        return charaCanvas;
    }
    public void setCharaCanvas(Canvas charaCanvas) {
        this.charaCanvas = charaCanvas;
    }



    public VisualData(GameBuilder builder, Stage stage, ImageFactory factory) {
        this.builder = builder;
        this.stage = stage;
        this.factory = factory;

    }

    public GameBuilder getBuilder() {
        return builder;
    }

    public Stage getStage() {
        return stage;
    }

    public Pane getNowPane() {
        return nowPane;
    }

    public ImageFactory getFactory() {
        return factory;
    }

    public void setNowPane(Pane nowPane) {
        this.nowPane = nowPane;
    }

    public Camera getCamera() {
        return camera;
    }

    public void setCamera(Camera camera) {
        this.camera = camera;
    }

}
