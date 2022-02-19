package ru.vsu.app.services;


import ru.vsu.app.models.GameCharacter;
import ru.vsu.app.models.GameMap;
import ru.vsu.app.models.GameMapType;
import ru.vsu.app.support.GameData;

public class GameService {
    public boolean createGame(int mheight, int mweight, int wsize) {
        GameData gameData = new GameData();
        gameData.setCharacter(new GameCharacter("NewHuman"));
        GameMapGenerator generator = new GameMapGenerator();
        for (int i = 0; i < wsize; i++) {
            GameMap map = generator.generate(mheight, mweight, randomMapType());
            gameData.getWorld()[i] = map;
        }
        startGame();
        return true;
    }

    private boolean startGame() {
        return true;
    }


    private GameMapType randomMapType() {
        if (Math.random() * 100 % 2 == 0)
            return GameMapType.UNDERGROUND;
        return GameMapType.TERRAIN;
    }
}


