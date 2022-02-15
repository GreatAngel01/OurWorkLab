package app.services;

import app.models.NodeState;

public class NodeService {
    public boolean work(NodeState state){
        if (state == NodeState.NONE ){
            return true;
        }
        if (state == NodeState.TELEPORT){
            return true;
        }
        return false;
    }
}
