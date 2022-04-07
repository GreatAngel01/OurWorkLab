package ru.vsu.app.models.map;

public class Node {
    private NodeState state = null;

    public Node(NodeState state) {
        this.state = state;
    }

    public NodeState getState() {
        return state;
    }

    public void setState(NodeState state) {
        this.state = state;
    }
}
