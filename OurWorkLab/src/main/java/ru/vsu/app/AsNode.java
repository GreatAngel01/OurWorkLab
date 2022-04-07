package ru.vsu.app;

public class AsNode {
    private int g; // Стоимость пути от начальной вершины до конечной (любой другой)
    private int f; // Минимальная стоимость перехода от узла к узлу: f(n) = g(n) + h (n)
    private int h; // Эвристика
    private int row; // Линия
    private int col; // Столбец
    private boolean isBlock; // Проверка нахождения блока (Стенки)
    private AsNode parent;

    public AsNode(int row, int col) {
        super();
        this.row = row;
        this.col = col;
    }

    public void heuristic(AsNode finalNode) { // Считаем эвристику (каждый раз относительно нашего месторас
        this.h = Math.abs(finalNode.getRow() - getRow()) + Math.abs(finalNode.getCol() - getCol());
    }

    public void setNodeData(AsNode currentNode, int cost) { // Подсчет g
        int gCost = currentNode.getG() + cost;
        setParent(currentNode);
        setG(gCost);
        finalCost();
    }

    public boolean checkBetterPath(AsNode currentNode, int cost) { // Выбирает лучший путь из возможных
        int gCost = currentNode.getG() + cost;
        if (gCost < getG()) {
            setNodeData(currentNode, cost);
            return true;
        }
        return false;
    }

    private void finalCost() { // Финальнй вес ячейки f(n) = g(n) + h (n)
        int finalCost = getG() + getH();
        setF(finalCost);
    }

    @Override
    public boolean equals(Object arg0) {
        AsNode other = (AsNode) arg0;
        return this.getRow() == other.getRow() && this.getCol() == other.getCol();
    }

    @Override
    public String toString() {
        return "Node [row=" + row + ", col=" + col + "]";
    }

    public int getH() {
        return h;
    }

    public int getG() {
        return g;
    }

    public void setG(int g) {
        this.g = g;
    }

    public int getF() {
        return f;
    }

    public void setF(int f) {
        this.f = f;
    }

    public AsNode getParent() {
        return parent;
    }

    public void setParent(AsNode parent) {
        this.parent = parent;
    }

    public boolean isBlock() {
        return isBlock;
    }

    public void setBlock(boolean isBlock) {
        this.isBlock = isBlock;
    }

    public int getRow() {
        return row;
    }

    public int getCol() {
        return col;
    }

}
