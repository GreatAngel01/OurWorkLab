package ru.vsu.app;

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
import ru.vsu.app.support.GraphicData;

import java.util.Timer;
import java.util.TimerTask;

public class HController {
    final private GraphicData graphicData = new GraphicData();

    public void createMenu(Stage stage) {


        stage.setResizable(false);

        Background background = new Background(new BackgroundImage(graphicData.getMenuBackground(), null, null, BackgroundPosition.CENTER, null));
        Canvas canvas = new Canvas(700, 600);
        Group group = new Group();
        Pane root = new Pane(canvas, group);
        root.setBackground(background);


        drawGif(root, graphicData.getMenuEye(), 75, 90);
        drawGif(root, graphicData.getMenuMonsters(), 60, 435);
        drawName(canvas);

        //Кнопка старта
        Button start = drawButton(graphicData.getMenuButtonEntered(),graphicData.getMenuButtonExited(), 250, 200);


        start.onActionProperty().set(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                System.out.println("START");
            }
        });

        //Кнопка настроек
        Button configure = drawButton(graphicData.getMenuButtonEntered(),graphicData.getMenuButtonExited(), 250, 280);
        configure.onActionProperty().set(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                System.out.println("CONF");
            }
        });

        //Кнопка выхода
        Button exit = drawButton(graphicData.getMenuButtonEntered(), graphicData.getMenuButtonExited(), 250, 360);
        exit.onActionProperty().set(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                System.out.println("EXIT");
            }
        });

        root.getChildren().add(configure);
        root.getChildren().add(exit);
        root.getChildren().add(start);


        Scene menu = new Scene(root, 700, 600);
        stage.setScene(menu);
        stage.show();
    }

    private void drawName(Canvas canvas) {
        synchronized (canvas) {
            GraphicsContext gc = canvas.getGraphicsContext2D();
            Image image = new Image(String.valueOf(getClass().getResource("/graphic/menu/gamename.png")));
            Timer timer = new Timer();
            TimerTask timerTask = new TimerTask() {
                @Override
                public void run() {
                    gc.setGlobalAlpha(0.02);
                    gc.drawImage(image, 60, 80);
                }
            };
            timer.schedule(timerTask, 100, 100);
        }
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
        button.setPrefSize(215, 100);
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