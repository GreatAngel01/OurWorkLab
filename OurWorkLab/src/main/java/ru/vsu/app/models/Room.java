package ru.vsu.app.models;

public class Room {
    private final int leftUpX;
    private final int leftUpY;
    private final int rightDownX;
    private final int rightDownY;

    public Room(int leftUpX, int leftUpY, int rightDownX, int rightDownY) {
        this.leftUpX = leftUpX;
        this.leftUpY = leftUpY;
        this.rightDownX = rightDownX;
        this.rightDownY = rightDownY;
    }

    public int getLeftUpX() {
        return leftUpX;
    }

    public int getLeftUpY() {
        return leftUpY;
    }

    public int getRightDownX() {
        return rightDownX;
    }

    public int getRightDownY() {
        return rightDownY;
    }
}
