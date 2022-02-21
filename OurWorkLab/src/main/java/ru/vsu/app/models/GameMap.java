package ru.vsu.app.models;

import java.util.HashMap;
import java.util.Map;

public class GameMap {
    private int height = 0;
    private int width = 0;
    private Node[][] map = null;
    private GameMapType type;
    private Map<Node, Direction> exits = new HashMap<>();

    public GameMap(Node[][] map) {
        setMap(map);
    }


    public Node[][] getMap() {
        return map;
    }

    public void setMap(Node[][] map) {
        this.height = map.length;
        this.width = map[0].length;
        this.map = map;
    }

    public GameMapType getType() {
        return type;
    }

    public void setType(GameMapType type) {
        this.type = type;
    }

    public Map<Node, Direction> getExits() {
        return exits;
    }

    public void setExits(Map<Node, Direction> exits) {
        this.exits = exits;
    }

    public int getHeight() {
        return height;
    }


    public int getWidth() {
        return width;
    }


}
