package ru.vsu.app.services;


import ru.vsu.app.data.GameData;
import ru.vsu.app.help.Coordinate;
import ru.vsu.app.models.creatures.Enemy;
import ru.vsu.app.models.creatures.GameCharacter;
import ru.vsu.app.models.map.Direction;
import ru.vsu.app.models.map.GameMap;
import ru.vsu.app.models.map.GameMapType;
import ru.vsu.app.models.map.NodeState;
import ru.vsu.app.visual.EnemyDrawer;

import java.util.*;

public record GameService(GameData gameData) {


    public boolean createGame() {
        gameData.setCharacter(new GameCharacter(4, 6, 4, "Lolik"));
        generateWorld();
        startGame();
        createEnemy();
        return true;
    }

    private boolean startGame() {
        gameData.setCharaPositionMap(gameData.getWorld()[0]);
        for (int i = 0; i < gameData.getCharaPositionMap().getHeight(); i++) {
            for (int j = 0; j < gameData.getCharaPositionMap().getHeight(); j++) {
                if (gameData.getCharaPositionMap().getMap()[i][j].getState() == NodeState.NONE) {
                    gameData.setCharaPos(new Coordinate(i, j));
                }
            }
        }
        return true;
    }


    private void generateWorld() {
        for (int i = 0; i < gameData.getWorld().length; i++) {
            GameMapGenerator gameMapGenerator = new GameMapGenerator(gameData);
            gameData.getWorld()[i] = gameMapGenerator.generate(gameData.getMapSize(), gameData.getMapSize(), GameMapType.UNDERGROUND, Direction.North, Direction.South);
        }
        gameData.setCharaPos(new Coordinate(1, 1));

    }

    private void createEnemy() {
        HashMap<GameMap, List<Enemy>> map = new HashMap<>();
        Enemy enemy1 = new Enemy(4, 2, 2);
        Enemy enemy2 = new Enemy(4, 2, 2);
        List<Enemy> list = new ArrayList<>();
        list.add(enemy1);
        list.add(enemy2);
        map.put(gameData.getCharaPositionMap(), list);
        gameData.setEnemyPosMap(map);

        for (int i = 0; i < gameData.getCharaPositionMap().getHeight(); i++) {
            for (int j = 0; j < gameData.getCharaPositionMap().getHeight(); j++) {
                if (gameData.getCharaPositionMap().getMap()[i][j].getState() == NodeState.NONE) {
                    gameData.getEnemyPosCor().put(enemy1, new Coordinate(i, j));
                    gameData.getEnemyPosCor().put(enemy2, new Coordinate(i, j));
                }
            }
        }
    }

    private Direction randomDirection() {
        Random random = new Random();
        int value = random.nextInt(1, 4);
        if (value == 1)
            return Direction.North;
        if (value == 2)
            return Direction.South;
        if (value == 3)
            return Direction.West;
        if (value == 4)
            return Direction.East;
        return null;
    }

    private GameMapType randomMapType() {
        Random random = new Random();
        int value = 1;// random.nextInt(1, 1 /* 2 */);
        if (value == 1)
            return GameMapType.UNDERGROUND;
        /*
        if (value == 2)
            return GameMapType.TERRAIN;
           
         */
        return null;
    }

}


