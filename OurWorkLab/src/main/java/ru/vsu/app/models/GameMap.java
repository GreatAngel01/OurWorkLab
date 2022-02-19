package ru.vsu.app.models;

import java.util.HashMap;
import java.util.Map;

public class GameMap {
    private int height = 0;
    private int weight = 0;
    private Node[][] map = null;
    private GameMapType type;
    private Map<Node, GameMap> exits = new HashMap<>();

    public GameMap(int height, int weight, Node[][] map) {
        this.height = height;
        this.weight = weight;
        this.map = map;
    }


    public Node[][] getMap() {
        return map;
    }

    public void setMap(Node[][] map) {
        this.map = map;
    }

    public GameMapType getType() {
        return type;
    }

    public void setType(GameMapType type) {
        this.type = type;
    }

     public Map<Node, GameMap> getExits() {
          return exits;
     }

     public void setExits(Map<Node, GameMap> exits) {
          this.exits = exits;
     }
}
