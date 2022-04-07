package ru.vsu.app.services;

import ru.vsu.app.AStar;
import ru.vsu.app.AsNode;
import ru.vsu.app.help.Coordinate;
import ru.vsu.app.models.map.GameMap;

import java.util.ArrayList;
import java.util.List;

public class WayService {
    public static List<Coordinate> generateWay(Coordinate start, Coordinate end, GameMap map) {
        List<Coordinate> path = new ArrayList<>();
        AStar aStar = new AStar(map,start,end);
        aStar.findPath();
        for(AsNode node : aStar.getPath()){
            path.add(new Coordinate(node.getRow(),node.getCol()));
        }
        return path;
    }
}
