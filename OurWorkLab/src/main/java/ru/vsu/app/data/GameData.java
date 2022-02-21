package ru.vsu.app.data;

import ru.vsu.app.models.GameCharacter;
import ru.vsu.app.models.GameMap;
import ru.vsu.app.models.Node;

public class GameData {
    private GameMap[] world;
    private GameCharacter character;
    private Node charaPositionNode;
    private GameMap charaPositionMap;


    public GameMap[] getWorld() {
        return world;
    }

    public void setWorld(GameMap[] world) {
        this.world = world;
    }

    public GameCharacter getCharacter() {
        return character;
    }

    public void setCharacter(GameCharacter character) {
        this.character = character;
    }

    public Node getCharaPositionNode() {
        return charaPositionNode;
    }

    public void setCharaPositionNode(Node charaPositionNode) {
        this.charaPositionNode = charaPositionNode;
    }

    public GameMap getCharaPositionMap() {
        return charaPositionMap;
    }

    public void setCharaPositionMap(GameMap charaPositionMap) {
        this.charaPositionMap = charaPositionMap;
    }
}
