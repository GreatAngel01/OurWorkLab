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
import ru.vsu.app.GameBuilder;
import ru.vsu.app.data.ImageFactory;
import ru.vsu.app.models.GameMap;
import ru.vsu.app.models.NodeState;

import java.util.Timer;
import java.util.TimerTask;


public class VisualController {
    private final GameBuilder builder;
    private final ImageFactory images;
    private final Stage stage;
    private Pane nowPane = null;

    public VisualController(GameBuilder builder) {
        this.builder = builder;
        this.stage = new Stage();
        this.images = new ImageFactory();
        stage.setResizable(false);
        stage.getIcons().add(images.getImage("/graphic/menu/appIcon.png"));
    }


    public void createMenu() {
        Background background = new Background(new BackgroundImage(images.getImage("/graphic/menu/menuStatic.png"), null, null, BackgroundPosition.CENTER, null));
        Canvas canvas = new Canvas(700, 600);
        Group group = new Group();
        this.nowPane = new Pane(canvas, group);
        nowPane.setBackground(background);

        drawGif(nowPane, images.getImage("/graphic/menu/eye.gif"), 75, 90);
        drawGif(nowPane, images.getImage("/graphic/menu/monster.gif"), 60, 435);
        drawName(canvas);

        //Button start
        Button start = drawButton(images.getImage("/graphic/menu/buttons/buttonStartOn.png"),
                images.getImage("/graphic/menu/buttons/buttonStartOff.png"), 250, 200);
        start.onActionProperty().set(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                builder.getGameService().createGame(20, 20, 2);
                createGameWindow();
            }
        });

        //Button config
        Button configure = drawButton(images.getImage("/graphic/menu/buttons/buttonConfOn.png"),
                images.getImage("/graphic/menu/buttons/buttonConfOff.png"), 250, 280);
        configure.onActionProperty().set(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {

            }
        });

        //Button exit
        Button exit = drawButton(images.getImage("/graphic/menu/buttons/buttonExitOn.png"),
                images.getImage("/graphic/menu/buttons/buttonExitOff.png"), 250, 360);
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
        GameMap map = builder.getGameData().getWorld()[0];
        Canvas canvas = new Canvas(map.getHeight() * 24, map.getWidth() * 24);
        for (int i = 0; i < map.getWidth(); i++) {
            for (int j = 0; j < map.getHeight(); j++) {
                if (map.getMap()[j][i].getState() == NodeState.BLOCK)
                    canvas.getGraphicsContext2D().drawImage(images.getImage("/graphic/underground/block.png"), i * 24, j * 24);
                if (map.getMap()[j][i].getState() == NodeState.NONE)
                    canvas.getGraphicsContext2D().drawImage(images.getImage("/graphic/underground/none.png"), i * 24, j * 24);
                if (map.getMap()[j][i].getState() == NodeState.EXIT)
                    canvas.getGraphicsContext2D().drawImage(images.getImage("/graphic/underground/exit.png"), i * 24, j * 24);
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
                gc.drawImage(images.getImage("/graphic/menu/gameName.png"), 60, 80);
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
                button.setBackground(new Background(new BackgroundImage(mouseEntered, BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.CENTER, BackgroundSize.DEFAULT)));
            }
        });
        button.setOnMouseExited(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                button.setBackground(new Background(new BackgroundImage(mouseExited, BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.CENTER, BackgroundSize.DEFAULT)));
            }
        });
        return button;
    }
}


