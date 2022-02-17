package ru.vsu.app.support;

import ru.vsu.app.models.GameMap;
import ru.vsu.app.models.GameMapType;

public class GameMapGenerator {
     public GameMap generate(int height, int weight, GameMapType type){
         if (type == GameMapType.UNDERGROUND){
             return null;
         }
         if (type == GameMapType.TERRAIN){
             return null;
         }
         return null;
     }
}
