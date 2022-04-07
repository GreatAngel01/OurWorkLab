package ru.vsu.app.visual;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.*;
import ru.vsu.app.data.VisualData;

import java.util.Timer;
import java.util.TimerTask;

public record MenuDrawer(VisualData visualData) {

    public void draw() {
        Background background = new Background(new BackgroundImage(visualData.getFactory().getImage("/graphic/menu/menuStatic.png"), null, null, BackgroundPosition.CENTER, null));
        Canvas canvas = new Canvas(700, 600);
        visualData.setNowPane(new Pane(canvas));
        visualData.getNowPane().setBackground(background);

        drawGif(visualData.getNowPane(), visualData.getFactory().getImage("/graphic/menu/eye.gif"), 75, 90);
        drawGif(visualData.getNowPane(), visualData.getFactory().getImage("/graphic/menu/monster.gif"), 60, 435);
        drawName(canvas);

        //Button start
        Button start = drawButton(visualData.getFactory().getImage("/graphic/menu/buttons/buttonStartOn.png"),
                visualData.getFactory().getImage("/graphic/menu/buttons/buttonStartOff.png"), 250, 200);
        start.onActionProperty().set(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                visualData.getBuilder().getGameService().createGame();
                GameDrawer drawer = new GameDrawer(visualData);
                drawer.draw();
            }
        });

        //Button config
        Button configure = drawButton(visualData.getFactory().getImage("/graphic/menu/buttons/buttonConfOn.png"),
                visualData.getFactory().getImage("/graphic/menu/buttons/buttonConfOff.png"), 250, 280);
        configure.onActionProperty().set(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                ConfigDrawer drawer = new ConfigDrawer(visualData);
                drawer.draw();
            }
        });

        //Button exit
        Button exit = drawButton(visualData.getFactory().getImage("/graphic/menu/buttons/buttonExitOn.png"),
                visualData.getFactory().getImage("/graphic/menu/buttons/buttonExitOff.png"), 250, 360);
        exit.onActionProperty().set(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                System.exit(1);
            }
        });


        visualData.getNowPane().getChildren().add(configure);
        visualData.getNowPane().getChildren().add(exit);
        visualData.getNowPane().getChildren().add(start);


        Scene menu = new Scene(visualData.getNowPane(), 700, 600);
        visualData.getStage().setScene(menu);
        visualData.getStage().show();
    }

    private void drawGif(Pane pane, Image image, int x, int y) {
        ImageView view = new ImageView(image);
        pane.getChildren().add(view);
        view.setX(x);
        view.setY(y);
    }

    private void drawName(Canvas canvas) {
        GraphicsContext gc = canvas.getGraphicsContext2D();
        Timer timer = new Timer();
        TimerTask timerTask = new TimerTask() {
            @Override
            public void run() {
                gc.setGlobalAlpha(0.02);
                gc.drawImage(visualData.getFactory().getImage("/graphic/menu/gameName.png"), 60, 80);
            }
        };
        timer.schedule(timerTask, 100, 100);
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
