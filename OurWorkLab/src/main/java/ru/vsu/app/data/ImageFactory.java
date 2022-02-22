package ru.vsu.app.data;

import javafx.scene.image.Image;

import java.util.HashMap;
import java.util.Map;

public class ImageFactory {
    private final Map<String, Image> images;

    public ImageFactory() {
        this.images = new HashMap<>();
    }


    public Image getImage(String path) {
        if (!images.containsKey(path)) {
            images.put(path, new Image(String.valueOf(getClass().getResource(path))));
        }
        return images.get(path);
    }


}
