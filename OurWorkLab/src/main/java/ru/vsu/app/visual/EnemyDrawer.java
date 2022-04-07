package ru.vsu.app.visual;


import javafx.scene.canvas.Canvas;
import ru.vsu.app.data.VisualData;
import ru.vsu.app.models.creatures.Enemy;

public record EnemyDrawer(VisualData visualData) {

    public void draw() {
        for (Enemy enemy : visualData.getBuilder().getGameData().getEnemyPosMap().get(visualData.getBuilder().getGameData().getCharaPositionMap())) {
            reDrawEnemy(enemy, visualData.getEnemyCanvases().get(enemy));
        }
    }

    private void reDrawEnemy(Enemy enemy, Canvas canvas) {
        canvas.getGraphicsContext2D().drawImage(visualData.getFactory().getImage("/graphic/enemy.png"), 0, 0);
        canvas.setLayoutX(visualData.getBuilder().getGameData().getEnemyPosCor().get(enemy).getX() * visualData.getBuilder().getGameData().getTailSize());
        canvas.setLayoutY(visualData.getBuilder().getGameData().getEnemyPosCor().get(enemy).getY() * visualData.getBuilder().getGameData().getTailSize());
    }
}
