package ru.vsu.app;


import ru.vsu.app.data.GameData;
import ru.vsu.app.services.GameService;

public class GameBuilder {

    private GameData gameData;
    private GameService gameService;

    public void build() {
        this.gameData = new GameData();
        this.gameService = new GameService(gameData);
    }

    public GameService getGameService() {
        return gameService;
    }

    public GameData getGameData() {
        return gameData;
    }
}