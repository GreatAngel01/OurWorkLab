package ru.vsu.app.models.map;

public class Room {
    private int leftUpX = -1 ;
    private int leftUpY = -1 ;;
    private int rightDownX = -1 ;;
    private int rightDownY = -1 ;;

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
