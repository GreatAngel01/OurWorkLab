package ru.vsu.app.support;

import javafx.scene.image.Image;


public class GraphicData {
    final private Image appIcon = new Image(String.valueOf(getClass().getResource("/graphic/menu/appIcon.png")));
    final private Image menuBackground = new Image(String.valueOf(getClass().getResource("/graphic/menu/menuStatic.png")));
    final private Image menuMonsters = new Image(String.valueOf(getClass().getResource("/graphic/menu/monster.gif")));
    final private Image menuEye = new Image(String.valueOf(getClass().getResource("/graphic/menu/eye.gif")));
    final private Image menuGameName = new Image(String.valueOf(getClass().getResource("/graphic/menu/gamename.png")));

    final private Image menuButtonEntered = new Image(String.valueOf(getClass().getResource("/graphic/menu/buttons/buttonOn.png")));
    final private Image menuButtonExited = new Image(String.valueOf(getClass().getResource("/graphic/menu/buttons/buttonOff.png")));

    final private Image buttonStartOn = new Image(String.valueOf(getClass().getResource("/graphic/menu/buttons/buttonStartOn.png")));
    final private Image buttonStartOff = new Image(String.valueOf(getClass().getResource("/graphic/menu/buttons/buttonStartOff.png")));

    final private Image buttonExitOn = new Image(String.valueOf(getClass().getResource("/graphic/menu/buttons/buttonExitOn.png")));
    final private Image buttonExitOff = new Image(String.valueOf(getClass().getResource("/graphic/menu/buttons/buttonExitOff.png")));

    final private Image buttonConfOn = new Image(String.valueOf(getClass().getResource("/graphic/menu/buttons/buttonConfOn.png")));
    final private Image buttonConfOff = new Image(String.valueOf(getClass().getResource("/graphic/menu/buttons/buttonConfOff.png")));

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

    public Image getButtonStartOn() {
        return buttonStartOn;
    }

    public Image getButtonStartOff() {
        return buttonStartOff;
    }

    public Image getButtonExitOn() {
        return buttonExitOn;
    }

    public Image getButtonExitOff() {
        return buttonExitOff;
    }

    public Image getButtonConfOn() {
        return buttonConfOn;
    }

    public Image getButtonConfOff() {
        return buttonConfOff;
    }

    public Image getAppIcon() {
        return appIcon;
    }
}
