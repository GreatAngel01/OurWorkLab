package ru.vsu.app.services;

import ru.vsu.app.models.map.Node;
import ru.vsu.app.models.map.NodeState;

public class CharacterService {
    public static int step(Node nextNode){
        if (nextNode.getState() == NodeState.BLOCK){
            return 0;
        }
        if (nextNode.getState() == NodeState.NONE){
            return 1;
        }
        if (nextNode.getState() == NodeState.EXIT){
            return -1;
        }
        if (nextNode.getState() == NodeState.TELEPORT){
            return 2;
        }
        return 0;
    }
}
