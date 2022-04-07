package ru.vsu.app.visual;

import javafx.event.EventHandler;
import javafx.scene.Camera;
import javafx.scene.canvas.Canvas;
import javafx.scene.control.Button;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import ru.vsu.app.data.VisualData;
import ru.vsu.app.help.Coordinate;

public record CharacterDrawer(VisualData visualData) {
    public void draw() {
        visualData.getNowPane().getChildren().add(createManage(visualData.getCamera(), visualData.getCharaCanvas()));
        reDrawCharacter(visualData.getCharaCanvas());
    }


    private Button createManage(Camera camera, Canvas canvas) {
        camera.setLayoutX(-350+visualData.getBuilder().getGameData().getCharaPos().getX()*visualData.getBuilder().getGameData().getTailSize());
        camera.setLayoutY(-300+visualData.getBuilder().getGameData().getCharaPos().getY()*visualData.getBuilder().getGameData().getTailSize());

        Button control = new Button();

        control.setOnKeyPressed(new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent keyEvent) {
                if (keyEvent.getCode() == KeyCode.A) {
                    camera.setLayoutX(camera.getLayoutX()- visualData.getBuilder().getGameData().getTailSize());
                    visualData.getBuilder().getGameData().setCharaPos(new Coordinate(visualData.getBuilder().getGameData().getCharaPos().getY(),visualData.getBuilder().getGameData().getCharaPos().getX() - 1));
                }
                if (keyEvent.getCode() == KeyCode.D) {
                    camera.setLayoutX(camera.getLayoutX() + visualData.getBuilder().getGameData().getTailSize());
                    visualData.getBuilder().getGameData().setCharaPos(new Coordinate(visualData.getBuilder().getGameData().getCharaPos().getY(),visualData.getBuilder().getGameData().getCharaPos().getX() + 1));
                }
                if (keyEvent.getCode() == KeyCode.W) {
                    camera.setLayoutY(camera.getLayoutY() - visualData.getBuilder().getGameData().getTailSize());
                    visualData.getBuilder().getGameData().setCharaPos(new Coordinate(visualData.getBuilder().getGameData().getCharaPos().getY()-1,visualData.getBuilder().getGameData().getCharaPos().getX()));
                }
                if (keyEvent.getCode() == KeyCode.S) {
                    camera.setLayoutY(camera.getLayoutY() + visualData.getBuilder().getGameData().getTailSize());
                    visualData.getBuilder().getGameData().setCharaPos(new Coordinate(visualData.getBuilder().getGameData().getCharaPos().getY()+1,visualData.getBuilder().getGameData().getCharaPos().getX() ));
                }
                reDrawCharacter(canvas);
            }
        });
        return control;
    }

    private void reDrawCharacter(Canvas canvas) {
        canvas.getGraphicsContext2D().drawImage(visualData.getFactory().getImage("/graphic/chaLeft.png"), 0, 0);
        canvas.setLayoutX(visualData.getBuilder().getGameData().getCharaPos().getX()*visualData.getBuilder().getGameData().getTailSize());
        canvas.setLayoutY(visualData.getBuilder().getGameData().getCharaPos().getY()*visualData.getBuilder().getGameData().getTailSize());
    }
}
