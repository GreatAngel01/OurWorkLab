package ru.vsu.app.support;

import javafx.scene.image.Image;


public class GraphicData {
    final private Image menuButtonEntered = new Image(String.valueOf(getClass().getResource("/graphic/menu/buttonOn.png")));
    final private Image menuButtonExited = new Image(String.valueOf(getClass().getResource("/graphic/menu/buttonOf.png")));
    final private Image menuBackground = new Image(String.valueOf(getClass().getResource("/graphic/menu/menu.gif")));
    final private Image menuMonsters = new Image(String.valueOf(getClass().getResource("/graphic/menu/monster.gif")));
    final private Image menuEye = new Image(String.valueOf(getClass().getResource("/graphic/menu/eye.gif")));
    final private Image menuGameName = new Image(String.valueOf(getClass().getResource("/graphic/menu/gamename.png")));

    public Image getMenuButtonEntered() {
        return menuButtonEntered;
    }

    public Image getMenuButtonExited() {
        return menuButtonExited;
    }

    public Image getMenuBackground() {
        return menuBackground;
    }

    public Image getMenuMonsters() {
        return menuMonsters;
    }

    public Image getMenuEye() {
        return menuEye;
    }

    public Image getMenuGameName() {
        return menuGameName;
    }
}
