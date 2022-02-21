package ru.vsu.app.visual;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.*;
import javafx.stage.Stage;
import ru.vsu.app.data.GameData;
import ru.vsu.app.data.GraphicData;
import ru.vsu.app.models.GameMap;
import ru.vsu.app.models.NodeState;
import ru.vsu.app.services.GameService;

import java.util.Timer;
import java.util.TimerTask;


public class HController {
    private final GraphicData graphicData = new GraphicData();
    private final GameData gameData;
    private final GameService gameService;
    private Stage stage = null;
    private Pane nowPane = null;

    public HController(Stage stage, GameData data, GameService service) {
        this.gameService = service;
        this.stage = stage;
        this.gameData = data;
        stage.setResizable(false);
        stage.getIcons().add(graphicData.getAppIcon());
    }


    public void createMenu() {
        Background background = new Background(new BackgroundImage(graphicData.getMenuBackground(), null, null, BackgroundPosition.CENTER, null));
        Canvas canvas = new Canvas(700, 600);
        Group group = new Group();
        this.nowPane = new Pane(canvas, group);
        nowPane.setBackground(background);

        drawGif(nowPane, graphicData.getMenuEye(), 75, 90);
        drawGif(nowPane, graphicData.getMenuMonsters(), 60, 435);
        drawName(canvas);

        //Button start
        Button start = drawButton(graphicData.getButtonStartOn(), graphicData.getButtonStartOff(), 250, 200);
        start.onActionProperty().set(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                gameService.createGame(20, 20, 2);
                createGameWindow();
            }
        });

        //Button config
        Button configure = drawButton(graphicData.getButtonConfOn(), graphicData.getButtonConfOff(), 250, 280);
        configure.onActionProperty().set(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {

            }
        });

        //Button exit
        Button exit = drawButton(graphicData.getButtonExitOn(), graphicData.getButtonExitOff(), 250, 360);
        exit.onActionProperty().set(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                System.exit(1);
            }
        });

        nowPane.getChildren().add(configure);
        nowPane.getChildren().add(exit);
        nowPane.getChildren().add(start);


        Scene menu = new Scene(nowPane, 700, 600);
        stage.setScene(menu);
        stage.show();
    }

    public void createGameWindow() {
        GameMap map = gameData.getWorld()[0];
        Canvas canvas = new Canvas(map.getHeight() * 24, map.getWidth() * 24);
        for (int i = 0; i < map.getWidth(); i++) {
            for (int j = 0; j < map.getHeight(); j++) {
                if (map.getMap()[j][i].getState() == NodeState.BLOCK)
                    canvas.getGraphicsContext2D().drawImage(graphicData.getUnderBlock(), i * 24, j * 24);
                if (map.getMap()[j][i].getState() == NodeState.NONE)
                    canvas.getGraphicsContext2D().drawImage(graphicData.getUnderNone(), i * 24, j * 24);
                if (map.getMap()[j][i].getState() == NodeState.EXIT)
                    canvas.getGraphicsContext2D().drawImage(graphicData.getUnderExit(), i * 24, j * 24);
            }
        }
        this.nowPane = new Pane(canvas);
        Scene scene = new Scene(nowPane);
        stage.setScene(scene);
    }

    private void drawGameField(Canvas canvas) {
    }

    private void drawName(Canvas canvas) {
        GraphicsContext gc = canvas.getGraphicsContext2D();
        Timer timer = new Timer();
        TimerTask timerTask = new TimerTask() {
            @Override
            public void run() {
                gc.setGlobalAlpha(0.02);
                gc.drawImage(graphicData.getMenuGameName(), 60, 80);
            }
        };
        timer.schedule(timerTask, 100, 100);

    }

    private void drawGif(Pane pane, Image image, int x, int y) {
        ImageView view = new ImageView(image);
        pane.getChildren().add(view);
        view.setX(x);
        view.setY(y);
    }

    private Button drawButton(Image mouseEntered, Image mouseExited, int x, int y) {
        Button button = new Button();
        button.setBackground(new Background(new BackgroundImage(mouseExited, BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.CENTER, BackgroundSize.DEFAULT)));
        button.setLayoutX(x);
        button.setLayoutY(y);
        button.setPadding(Insets.EMPTY);
        button.setPrefSize(215, 50);
        button.setOnMouseEntered(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                button.setBackground(new Background(new BackgroundImage(mouseEntered,
                        BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.CENTER, BackgroundSize.DEFAULT)));
            }
        });
        button.setOnMouseExited(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                button.setBackground(new Background(new BackgroundImage(mouseExited,
                        BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.CENTER, BackgroundSize.DEFAULT)));
            }
        });
        return button;
    }


}