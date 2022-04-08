package ru.vsu.app.visual;

import javafx.event.EventHandler;
import javafx.scene.Camera;
import javafx.scene.canvas.Canvas;
import javafx.scene.control.Button;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import ru.vsu.app.data.VisualData;
import ru.vsu.app.help.Coordinate;
import ru.vsu.app.models.map.Direction;
import ru.vsu.app.models.map.GameMap;
import ru.vsu.app.models.map.Node;
import ru.vsu.app.models.map.NodeState;
import ru.vsu.app.services.CharacterService;

public record CharacterDrawer(VisualData visualData) {
    public void draw() {
        Button button = createManage(visualData.getCamera(), visualData.getCharaCanvas());
        visualData.getNowPane().getChildren().add(button);
        reDrawCharacter(visualData.getCharaCanvas(),Direction.East);
    }


    private Button createManage(Camera camera, Canvas canvas) {
        camera.setLayoutX(-350+visualData.getBuilder().getGameData().getCharaPos().getX()*visualData.getBuilder().getGameData().getTailSize());
        camera.setLayoutY(-300+visualData.getBuilder().getGameData().getCharaPos().getY()*visualData.getBuilder().getGameData().getTailSize());

        Button control = new Button();

        control.setOnKeyPressed(new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent keyEvent) {
                Direction direction = Direction.East;
                Coordinate cor = null;
                if (keyEvent.getCode() == KeyCode.A) {
                    direction = Direction.East;
                    cor = new Coordinate(visualData.getBuilder().getGameData().getCharaPos().getY(),
                            visualData.getBuilder().getGameData().getCharaPos().getX() - 1);
                    if (CharacterService.step(findNode(cor))!=0) {
                        camera.setLayoutX(camera.getLayoutX() - visualData.getBuilder().getGameData().getTailSize());
                        visualData.getBuilder().getGameData().setCharaPos(cor);
                    }
                }
                if (keyEvent.getCode() == KeyCode.D) {
                    direction = Direction.West;
                    cor =new Coordinate(visualData.getBuilder().getGameData().getCharaPos().getY(),
                            visualData.getBuilder().getGameData().getCharaPos().getX() + 1);
                    if (CharacterService.step(findNode(cor))!=0) {
                        camera.setLayoutX(camera.getLayoutX() + visualData.getBuilder().getGameData().getTailSize());
                        visualData.getBuilder().getGameData().setCharaPos(cor);
                    }
                }
                if (keyEvent.getCode() == KeyCode.W) {
                    direction = Direction.North;
                    cor =new Coordinate(visualData.getBuilder().getGameData().getCharaPos().getY() - 1, visualData.getBuilder().getGameData().getCharaPos().getX());
                    if (CharacterService.step(findNode(cor)) != 0) {
                        camera.setLayoutY(camera.getLayoutY() - visualData.getBuilder().getGameData().getTailSize());
                        visualData.getBuilder().getGameData().setCharaPos(cor);
                    }
                }
                if (keyEvent.getCode() == KeyCode.S) {
                    direction = Direction.South;
                    cor =new Coordinate(visualData.getBuilder().getGameData().getCharaPos().getY() + 1, visualData.getBuilder().getGameData().getCharaPos().getX());
                    if (CharacterService.step(findNode(cor)) != 0) {
                        camera.setLayoutY(camera.getLayoutY() + visualData.getBuilder().getGameData().getTailSize());
                        visualData.getBuilder().getGameData().setCharaPos(cor);
                    }
                }
                reDrawCharacter(canvas,direction);
            }
        });
        return control;
    }

    private void reDrawCharacter(Canvas canvas, Direction direction) {
        canvas.getGraphicsContext2D().clearRect(0,0,50,50);
        if (direction == Direction.East)
            canvas.getGraphicsContext2D().drawImage(visualData.getFactory().getImage("/graphic/character/A.gif"), 10, 0);
        if (direction == Direction.West)
            canvas.getGraphicsContext2D().drawImage(visualData.getFactory().getImage("/graphic/character/D.gif"), 10, 0);
        if (direction == Direction.South)
            canvas.getGraphicsContext2D().drawImage(visualData.getFactory().getImage("/graphic/character/S.gif"), 10, 0);
        if (direction == Direction.North)
            canvas.getGraphicsContext2D().drawImage(visualData.getFactory().getImage("/graphic/character/W.gif"), 10, 0);

        canvas.setLayoutX(visualData.getBuilder().getGameData().getCharaPos().getX()*visualData.getBuilder().getGameData().getTailSize());
        canvas.setLayoutY(visualData.getBuilder().getGameData().getCharaPos().getY()*visualData.getBuilder().getGameData().getTailSize());
    }

    private Node findNode(Coordinate coordinate){
       return visualData.getBuilder().getGameData().getCharaPositionMap().getMap()[coordinate.getY()][coordinate.getX()];
    }
}
