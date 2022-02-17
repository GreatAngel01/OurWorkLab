package ru.vsu.app.models;

public class Node {
    private Node NodeUp = null;
    private Node NodeDown = null;
    private Node NodeLeft = null;
    private Node NodeRight = null;
    private NodeState state = null;

    public Node(Node nodeUp, Node nodeDown, Node nodeLeft, Node nodeRight, NodeState state) {
        NodeUp = nodeUp;
        NodeDown = nodeDown;
        NodeLeft = nodeLeft;
        NodeRight = nodeRight;
        this.state = state;
    }


    public Node getNodeUp() {
        return NodeUp;
    }

    public void setNodeUp(Node nodeUp) {
        NodeUp = nodeUp;
    }

    public Node getNodeDown() {
        return NodeDown;
    }

    public void setNodeDown(Node nodeDown) {
        NodeDown = nodeDown;
    }

    public Node getNodeLeft() {
        return NodeLeft;
    }

    public void setNodeLeft(Node nodeLeft) {
        NodeLeft = nodeLeft;
    }

    public Node getNodeRight() {
        return NodeRight;
    }

    public void setNodeRight(Node nodeRight) {
        NodeRight = nodeRight;
    }

    public NodeState getState() {
        return state;
    }

    public void setState(NodeState state) {
        this.state = state;
    }
}
