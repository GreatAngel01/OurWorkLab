package ru.vsu.app.data;

import ru.vsu.app.help.Coordinate;
import ru.vsu.app.models.creatures.Enemy;
import ru.vsu.app.models.creatures.GameCharacter;
import ru.vsu.app.models.map.GameMap;

import java.util.HashMap;
import java.util.List;

public class GameData {
    private GameMap[] world = new GameMap[2];
    private int mapSize = 100;

    private GameCharacter character;
    private Coordinate charaPos;
    private GameMap charaPositionMap;
    private int TailSize = 48;

    private HashMap<Enemy,Coordinate> enemyPosCor = new HashMap<>();
    private HashMap<Enemy,List<Coordinate> >enemyWays = new HashMap<>()  ;
    private HashMap<GameMap, List<Enemy>> enemyPosMap;


    public HashMap<Enemy, List<Coordinate>> getEnemyWays() {
        return enemyWays;
    }

    public void setEnemyWays(HashMap<Enemy, List<Coordinate>> enemyWays) {
        this.enemyWays = enemyWays;
    }

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


    public GameMap getCharaPositionMap() {
        return charaPositionMap;
    }

    public void setCharaPositionMap(GameMap charaPositionMap) {
        this.charaPositionMap = charaPositionMap;
    }

    public int getMapSize() {
        return mapSize;
    }

    public void setMapSize(int mapSize) {
        this.mapSize = mapSize;
    }

    public Coordinate getCharaPos() {
        return charaPos;
    }

    public void setCharaPos(Coordinate charaPos) {
        this.charaPos = charaPos;
    }

    public int getTailSize() {
        return TailSize;
    }

    public void setTailSize(int tailSize) {
        TailSize = tailSize;
    }

    public HashMap<Enemy, Coordinate> getEnemyPosCor() {
        return enemyPosCor;
    }

    public void setEnemyPosCor(HashMap<Enemy, Coordinate> enemyPosCor) {
        this.enemyPosCor = enemyPosCor;
    }

    public HashMap<GameMap, List<Enemy>> getEnemyPosMap() {
        return enemyPosMap;
    }

    public void setEnemyPosMap(HashMap<GameMap, List<Enemy>> enemyPosMap) {
        this.enemyPosMap = enemyPosMap;
    }
}
