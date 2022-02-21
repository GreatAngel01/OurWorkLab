package ru.vsu.app.services;


import ru.vsu.app.data.GameData;
import ru.vsu.app.models.Direction;
import ru.vsu.app.models.GameMap;
import ru.vsu.app.models.GameMapType;

import java.util.Random;

public class GameService {
    final private GameData gameData;

    public GameService(GameData gameData) {
        this.gameData = gameData;

    }

    public boolean createGame(int mheight, int mwidth, int wsize) {
        gameData.setWorld(new GameMap[wsize]);
        for (int i = 0; i < wsize; i++) {
            gameData.getWorld()[i] = GameMapGenerator.generate(mheight, mwidth, GameMapType.UNDERGROUND, Direction.North, Direction.South);
        }
        return true;
    }

    private boolean startGame() {

        return true;
    }


    private GameMap[] generateWorld(int mheight, int mwidth, int wsize) {

        return null;
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


