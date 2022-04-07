package ru.vsu.app.visual;

import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.control.Slider;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import ru.vsu.app.data.VisualData;
import ru.vsu.app.models.map.GameMap;

import java.util.Objects;
import java.util.Random;

public record ConfigDrawer(VisualData visualData) {
    public void draw() {
        Canvas canvas = new Canvas(visualData.getStage().getWidth(), visualData.getStage().getHeight());
        drawBackground(canvas);

        visualData.setNowPane(new Pane(canvas));
        drawGif(visualData.getNowPane(), visualData.getFactory().getImage("/graphic/config/backObj/torchFlame.gif"), 116.5, 200);
        drawGif(visualData.getNowPane(), visualData.getFactory().getImage("/graphic/config/backObj/torchFlame.gif"), 554.5, 200);
        createManageSys(visualData.getNowPane());
        Scene scene = new Scene(visualData().getNowPane(), 700, 600);
        visualData.getStage().setScene(scene);
    }

    public void createManageSys(Pane pane) {
        Group group = new Group();

        Slider worldSize = new Slider();
        worldSize.setMin(2);
        worldSize.setMax(5);
        worldSize.setMajorTickUnit(1);
        worldSize.setValue(visualData.getBuilder().getGameData().getWorld().length);

        Slider mapSize = new Slider();
        mapSize.setMin(30);
        mapSize.setMax(100);
        mapSize.setMajorTickUnit(10);
        mapSize.setLayoutY(100);
        mapSize.setValue(visualData.getBuilder().getGameData().getMapSize());

        group.getChildren().add(worldSize);
        group.getChildren().add(mapSize);

        Button menu = new Button("Назад");
        menu.setOnMousePressed(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                MenuDrawer drawer = new MenuDrawer(visualData);
                drawer.draw();
                visualData.getBuilder().getGameData().setWorld(new GameMap[(int) worldSize.getValue()]);
                visualData.getBuilder().getGameData().setMapSize((int) mapSize.getValue());
            }
        });

        pane.getChildren().add(menu);
        pane.getChildren().add(group);
        group.setLayoutX(280);
        group.setLayoutY(200);
    }

    public void drawBackground(Canvas canvas) {
        Random random = new Random();
        GraphicsContext gc = canvas.getGraphicsContext2D();
        gc.setFill(Color.BLACK);
        gc.fillRect(0, 0, 700, 600);
        gc.fill();

        for (int i = 0; i < 15; i++) {
            for (int j = 0; j < 19; j++) {
                String str = generateDamageSize();
                if (j % 2 == 0)
                    gc.drawImage(visualData.getFactory().getImage("/graphic/config/bricks/1x/" +
                            str + "/" + generateNumber(str) + ".png"), i * 53, j * 33);
                else
                    gc.drawImage(visualData.getFactory().getImage("/graphic/config/bricks/1x/" +
                            str + "/" + generateNumber(str) + ".png"), (i - 0.5) * 53, j * 33);
            }
        }

        gc.setFill(Color.color(0, 0, 0, 0.80));
        gc.fillRect(0, 0, 700, 600);

        gc.drawImage(visualData.getFactory().getImage("/graphic/config/backObj/torch.png"), 109.5, 235);
        gc.drawImage(visualData.getFactory().getImage("/graphic/config/backObj/torch.png"), 547.5, 235);
        gc.drawImage(visualData.getFactory().getImage("/graphic/config/backObj/light.png"), -160, -70);
        gc.drawImage(visualData.getFactory().getImage("/graphic/config/backObj/light.png"), 260, -70);

        gc.drawImage(visualData.getFactory().getImage("/graphic/config/backObj/configPlate.png"), 220, 100);
    }

    private void drawGif(Pane pane, Image image, double x, double y) {
        ImageView view = new ImageView(image);
        pane.getChildren().add(view);
        view.setX(x);
        view.setY(y);
    }

    private String generateDamageSize() {
        Random random = new Random();
        int r = random.nextInt(1, 6);
        if (r == 1)
            return "small";

        if (r == 2)
            return "middle";

        if (r == 3)
            return "very";

        return "special";
    }

    private String generateNumber(String string) {
        Random random = new Random();
        if (Objects.equals(string, "special")) {
            return "1";
        }
        if (Objects.equals(string, "very")) {
            return String.valueOf(random.nextInt(1, 7));
        } else return String.valueOf(random.nextInt(1, 3));
    }

}
