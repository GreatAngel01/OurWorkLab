package ru.vsu.app.support;

import ru.vsu.app.models.GameCharacter;
import ru.vsu.app.models.GameMap;
import ru.vsu.app.models.Node;

import java.util.HashMap;
import java.util.Map;

public class GameData {
    private GameMap[] world;
    private GameCharacter character;
    private Map<GameCharacter, Node> position = new HashMap<>();



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

    public Map<GameCharacter, Node> getPosition() {
        return position;
    }

    public void setPosition(Map<GameCharacter, Node> position) {
        this.position = position;
    }
}
