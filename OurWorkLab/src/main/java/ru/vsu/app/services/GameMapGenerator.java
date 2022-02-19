package ru.vsu.app.services;

import ru.vsu.app.models.GameMap;
import ru.vsu.app.models.GameMapType;

public class GameMapGenerator {
     public GameMap generate(int height, int weight, GameMapType type){
         if (type == GameMapType.UNDERGROUND){
             return underGeneration();
         }
         if (type == GameMapType.TERRAIN){
             return terraGeneration();
         }
         return null;
     }

     private GameMap underGeneration(){
         return null;
     }

    private GameMap terraGeneration(){
        return null;
    }
}
