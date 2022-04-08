package ru.vsu.app.visual;


import javafx.event.EventHandler;
import javafx.scene.Camera;
import javafx.scene.PerspectiveCamera;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.control.Button;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.Pane;
import ru.vsu.app.data.VisualData;
import ru.vsu.app.models.creatures.Enemy;
import ru.vsu.app.models.map.GameMap;
import ru.vsu.app.models.map.NodeState;
import ru.vsu.app.services.EnemyServices;

import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

public record GameDrawer(VisualData visualData) {

    public void draw() {
        GameMap map = visualData.getBuilder().getGameData().getWorld()[0];
        Canvas canvas = new Canvas(map.getHeight() * 48, map.getWidth() * 48);
        for (int i = 0; i < map.getWidth(); i++) {
            for (int j = 0; j < map.getHeight(); j++) {
                if (map.getMap()[j][i].getState() == NodeState.BLOCK)
                    canvas.getGraphicsContext2D().drawImage(visualData.getFactory().getImage("/graphic/underground/block.png"), i * 48, j * 48);
                if (map.getMap()[j][i].getState() == NodeState.NONE)
                    canvas.getGraphicsContext2D().drawImage(visualData.getFactory().getImage("/graphic/underground/none.png"), i * 48, j * 48);
                if (map.getMap()[j][i].getState() == NodeState.EXIT)
                    canvas.getGraphicsContext2D().drawImage(visualData.getFactory().getImage("/graphic/underground/exit.png"), i * 48, j * 48);
            }
        }
        visualData.setNowPane(new Pane(canvas));
        Camera camera = new PerspectiveCamera();
        visualData.setCamera(camera);
        Canvas chaCanvas = new Canvas(50, 50);
        visualData.setCharaCanvas(chaCanvas);
        visualData.getNowPane().getChildren().add(chaCanvas);

        for (Enemy enemy : visualData.getBuilder().getGameData().getEnemyPosMap().get(visualData.getBuilder().getGameData().getCharaPositionMap())) {
            visualData.getEnemyCanvases().put(enemy, new Canvas(50, 50));
            visualData.getNowPane().getChildren().add(visualData.getEnemyCanvases().get(enemy));
        }
        CharacterDrawer drawer = new CharacterDrawer(visualData);
        drawer.draw();

        EnemyDrawer enemyDrawer = new EnemyDrawer(visualData);
        EnemyServices enemyServices = new EnemyServices(visualData.getBuilder().getGameData());

        Timer timer = new Timer();
        TimerTask timerTask = new TimerTask() {
            @Override
            public void run() {
                List<Enemy> enemyList = visualData.getBuilder().getGameData().getEnemyPosMap().get(visualData.getBuilder().getGameData().getCharaPositionMap());
                enemyServices.play(enemyList);
                enemyDrawer.draw();
            }
        };
        timer.schedule(timerTask, 1000, 1000);

        Scene scene = new Scene(visualData.getNowPane(), 700, 600);
        scene.setCamera(camera);
        visualData.getStage().setScene(scene);
        visualData.getStage().show();
    }

}
