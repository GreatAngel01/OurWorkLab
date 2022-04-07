package ru.vsu.app;

import ru.vsu.app.help.Coordinate;
import ru.vsu.app.models.map.GameMap;
import ru.vsu.app.models.map.NodeState;

import java.util.*;


public class AStar {
    private static int DEFAULT_COST = 1; // Вес клетки (настраиваемое)
    private int cost;
    private AsNode[][] searchArea;
    private PriorityQueue<AsNode> openList;
    private Set<AsNode> closedSet;
    private AsNode initialNode;
    private AsNode finalNode;
    private List<AsNode> path;

    public AStar(GameMap gameMap, Coordinate start, Coordinate end) {
        setInitialNode(new AsNode(start.getY(),start.getX()));
        setFinalNode(new AsNode(end.getY(),end.getX()));
        setNodes(gameMap);
        this.openList = new PriorityQueue<AsNode>(new Comparator<AsNode>() { // Очередь с приоритетом

            @Override
            public int compare(AsNode node0, AsNode node1) {
                return Integer.compare(node0.getF(), node1.getF());
            } // Компаратор сравнения пути
        });

        this.closedSet = new HashSet<>();
    }
    private void  setNodes(GameMap gameMap) { // Создание узлов
        this.searchArea = new AsNode[gameMap.getHeight()][gameMap.getWidth()];
        for (int i = 0;i<gameMap.getHeight();i++){
            for (int j = 0;j<gameMap.getHeight();j++){
                AsNode node = new AsNode(i, j);
                node.setBlock(gameMap.getMap()[i][j].getState() == NodeState.BLOCK);
                node.heuristic(getFinalNode());
                this.searchArea[i][j] = node;
            }
        }
    }

    public void findPath() {
        openList.add(initialNode);
        while (!isEmpty(openList)) {
            AsNode currentNode = openList.poll();
            closedSet.add(currentNode);
            if (isFinalNode(currentNode)) {
                this.path =getPath(currentNode);
                return;
            } else {
                addNeighborNodes(currentNode);
            }
        }
        this.path =new ArrayList<AsNode>();
    }

    private List<AsNode> getPath(AsNode currentNode) {
        List<AsNode> path = new ArrayList<AsNode>();
        path.add(currentNode);
        AsNode parent;
        while ((parent = currentNode.getParent()) != null) {
            path.add(0, parent);
            currentNode = parent;
        }
        return path;
    }

    private void addNeighborNodes(AsNode currentNode) {
        addNeighborUpperRow(currentNode);
        addNeighborMiddleRow(currentNode);
        addNeighborLowerRow(currentNode);
    }

    private void addNeighborLowerRow(AsNode currentNode) {
        int row = currentNode.getRow();
        int col = currentNode.getCol();
        int lowerRow = row + 1;
        if (lowerRow < getSearchArea().length) {
            checkNode(currentNode, col, lowerRow, getHvCost());
        }
    }

    private void addNeighborMiddleRow(AsNode currentNode) {
        int row = currentNode.getRow();
        int col = currentNode.getCol();
        int middleRow = row;
        if (col - 1 >= 0) {
            checkNode(currentNode, col - 1, middleRow, getHvCost());
        }
        if (col + 1 < getSearchArea()[0].length) {
            checkNode(currentNode, col + 1, middleRow, getHvCost());
        }
    }

    private void addNeighborUpperRow(AsNode currentNode) {
        int row = currentNode.getRow();
        int col = currentNode.getCol();
        int upperRow = row - 1;
        if (upperRow >= 0) {
            checkNode(currentNode, col, upperRow, getHvCost());
        }
    }

    private void checkNode(AsNode currentNode, int col, int row, int cost) {
        AsNode adjacentNode = getSearchArea()[row][col];
        if (!adjacentNode.isBlock() && !getClosedSet().contains(adjacentNode)) {
            if (!getOpenList().contains(adjacentNode)) {
                adjacentNode.setNodeData(currentNode, cost);
                getOpenList().add(adjacentNode);
            } else {
                boolean changed = adjacentNode.checkBetterPath(currentNode, cost);
                if (changed) { // Удалает и добовляет измененный узел, чтобы его можно было снова отсортировать в PQ
                    getOpenList().remove(adjacentNode);
                    getOpenList().add(adjacentNode);
                }
            }
        }
    }

    private boolean isFinalNode(AsNode currentNode) {
        return currentNode.equals(finalNode);
    }

    private boolean isEmpty(PriorityQueue<AsNode> openList) {
        return openList.size() == 0;
    }

    private void setBlock(int row, int col) {
        this.searchArea[row][col].setBlock(true);
    }

    public void setInitialNode(AsNode initialNode) {
        this.initialNode = initialNode;
    }

    public AsNode getFinalNode() {
        return finalNode;
    }

    public void setFinalNode(AsNode finalNode) {
        this.finalNode = finalNode;
    }

    public AsNode[][] getSearchArea() {
        return searchArea;
    }

    public PriorityQueue<AsNode> getOpenList() {
        return openList;
    }

    public Set<AsNode> getClosedSet() {
        return closedSet;
    }

    public int getHvCost() {
        return cost;
    }

    public List<AsNode> getPath() {
        return path;
    }

    public void setPath(List<AsNode> path) {
        this.path = path;
    }
}

