package ru.vsu.app.services;

import ru.vsu.app.data.GameData;
import ru.vsu.app.help.Coordinate;
import ru.vsu.app.models.map.NodeState;
import ru.vsu.app.models.creatures.Enemy;
import ru.vsu.app.models.map.GameMap;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public record EnemyServices(GameData gameData) {
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



    public void play(List<Enemy> enemyList) {
      for (Enemy enemy : enemyList){
          findWay(enemy);
          step(enemy);
      }
    }

    private void step(Enemy enemy){
        if (gameData.getEnemyWays().get(enemy).size()==1){
            damage(enemy);
            return;
        }
        gameData.getEnemyPosCor().put(enemy,gameData.getEnemyWays().get(enemy).get(1));
    }
    private void findWay(Enemy enemy){
        gameData.getEnemyWays().put(enemy,WayService.generateWay(gameData.getEnemyPosCor().get(enemy),
                gameData.getCharaPos(),gameData.getCharaPositionMap()));

    }
    private void damage(Enemy enemy){
        gameData.getCharacter().setHp(gameData.getCharacter().getHp()-enemy.getDamage());
        if (gameData.getCharacter().getHp()==0){
            System.out.println("LOL");
        }
    }

}
