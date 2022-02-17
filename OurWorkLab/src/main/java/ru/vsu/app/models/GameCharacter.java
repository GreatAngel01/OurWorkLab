package ru.vsu.app.models;

public class GameCharacter {
     private int speed = 1;
     private String name = "A";

    public GameCharacter(int speed, String name) {
        this.speed = speed;
        this.name = name;
    }

    public GameCharacter(String name) {
        this.name = name;
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getSpeed() {
        return speed;
    }

    public void setSpeed(int speed) {
        this.speed = speed;
    }


}
