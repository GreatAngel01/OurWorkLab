package ru.vsu.app.services;

import ru.vsu.app.models.Node;
import ru.vsu.app.models.NodeState;

public class NodeService {
    public boolean work( NodeState state){
        if (state == NodeState.NONE ){
            return true;
        }
        if (state == NodeState.TELEPORT){
            return true;
        }
        if (state == NodeState.EXIT){

        }
        return true;
    }
}
