package ru.vsu.app.services;

import ru.vsu.app.data.GameData;
import ru.vsu.app.models.map.*;

import java.util.*;

public record GameMapGenerator(GameData gameData) {
    public GameMap generate(int height, int width, GameMapType type, Direction start, Direction exit) {
        if (type == GameMapType.UNDERGROUND) {
            Random random = new Random();
            GameMap gameMap = createMap(height, width);

            // создание комнат с помощью дерева
            Tree tree = new Tree(new NodeTree(new int[] {2, 2}, new int[] {width - 2, height - 2}, null));
            List<Room> list = new ArrayList<>();
            gameMap.setRooms(list);
            createRoom(gameMap, tree.getHead(), list, height/5, width/5, height/20, width/20);
            NodeTree temp = tree.getHead();
            while (temp.getLeft() != null) {
                temp = temp.getLeft();
            }
            createWay(gameMap, list);

            int tempX;
            int tempY;
            if (start == Direction.East) {
                tempX = width - 1;
                tempY = random.nextInt(1, height - 2);
            } else {
                if (start == Direction.North) {
                    tempX = random.nextInt(1, width - 2);
                    tempY = 0;
                } else {
                    if (start == Direction.South) {
                        tempX = random.nextInt(1, width - 2);
                        tempY = height - 1;
                    } else {
                        tempX = 0;
                        tempY = random.nextInt(1, height - 2);
                    }
                }
            }
            Map<Node, Direction> exits = new HashMap<>();
            exits.put(gameMap.getMap()[tempY][tempX], start);
            cutExitWay(gameMap, tempX, tempY, list.get(0));
            gameMap.getMap()[tempY][tempX].setState(NodeState.EXIT);

            if (exit == Direction.East) {
                tempX = width - 1;
                tempY = random.nextInt(1, height - 2);
            } else {
                if (exit == Direction.North) {
                    tempX = random.nextInt(1, width - 2);
                    tempY = 0;
                } else {
                    if (exit == Direction.South) {
                        tempX = random.nextInt(1, width - 2);
                        tempY = height - 1;
                    } else {
                        tempX = 0;
                        tempY = random.nextInt(1, height - 2);
                    }
                }
            }

            cutExitWay(gameMap, tempX, tempY, list.get(list.size() - 1));


            gameMap.getMap()[tempY][tempX].setState(NodeState.EXIT);
            exits.put(gameMap.getMap()[tempY][tempX], exit);
            gameMap.setExits(exits);

            return gameMap;
        }


        if (type == GameMapType.TERRAIN) {
            return null;
        }
        return null;
    }

    public void cutExitWay(GameMap gameMap, int x, int y, Room room) {
        Random random = new Random();
        Node[][] nodes = gameMap.getMap();
        int inputX = x;
        int inputY = y;
        int outputX = random.nextInt(room.getLeftUpX(), room.getRightDownX());
        int outputY = random.nextInt(room.getLeftUpY(), room.getRightDownY());

        if (inputX > outputX) {
            int temp = inputX;
            inputX = outputX;
            outputX = temp;
            temp = inputY;
            inputY = outputY;
            outputY = temp;
        }
        for (int i = inputX; i <= outputX; i++) {
            nodes[inputY][i].setState(NodeState.NONE);
        }
        int input = inputY;
        int output = outputY;
        if (outputY <= inputY) {
            input = outputY;
            output = inputY;
        }
        for (int i = input; i <= output; i++) {
            nodes[i][outputX].setState(NodeState.NONE);
        }
        gameMap.setMap(nodes);
    }

    public void cutRoom(GameMap gameMap, Room room) {
        Node[][] nodes = gameMap.getMap();
        for (int i = room.getLeftUpX(); i <= room.getRightDownX(); i++) {
            for (int j = room.getLeftUpY(); j < room.getRightDownY(); j++) {
                nodes[j][i].setState(NodeState.NONE);
            }
        }
        gameMap.setMap(nodes);
    }

    public void cutWay(GameMap gameMap, Room firstRoom, Room secondRoom) {
        Node[][] nodes = gameMap.getMap();
        Random random = new Random();

        int inputX = random.nextInt(firstRoom.getLeftUpX(), firstRoom.getRightDownX());
        int inputY = random.nextInt(firstRoom.getLeftUpY(), firstRoom.getRightDownY());
        int outputX = random.nextInt(secondRoom.getLeftUpX(), secondRoom.getRightDownX());
        int outputY = random.nextInt(secondRoom.getLeftUpY(), secondRoom.getRightDownY());

        if (inputX > outputX) {
            int temp = inputX;
            inputX = outputX;
            outputX = temp;
            temp = inputY;
            inputY = outputY;
            outputY = temp;
        }
        for (int i = inputX; i <= outputX; i++) {
            nodes[inputY][i].setState(NodeState.NONE);
        }
        int input = inputY;
        int output = outputY;
        if (outputY < inputY) {
            input = outputY;
            output = inputY;
        }
        for (int i = input; i <= output; i++) {
            nodes[i][outputX].setState(NodeState.NONE);
        }
        gameMap.setMap(nodes);
    }

    public void createRoom(GameMap gameMap, NodeTree node, List<Room> list, int maxHeightRoom, int maxWidthRoom, int minHeightRoom, int minWidthRoom) {
        Random random = new Random();
        int height = node.getOutputPoint()[1] - node.getInputPoint()[1];
        int width = node.getOutputPoint()[0] - node.getInputPoint()[0];
        boolean rand = random.nextBoolean();
        boolean heightBool;
        boolean widthBool;
        if (rand) {
            heightBool = splitHeight(gameMap, list, node, height, maxHeightRoom, maxWidthRoom, minHeightRoom, minWidthRoom);
            if (heightBool) {
                widthBool = splitWidth(gameMap, list,  node, width, maxHeightRoom, maxWidthRoom, minHeightRoom, minWidthRoom);
            } else {
                widthBool = false;
            }
        } else {
            widthBool = splitWidth(gameMap, list,  node, width, maxHeightRoom, maxWidthRoom, minHeightRoom, minWidthRoom);
            if (widthBool) {
                heightBool = splitHeight(gameMap, list, node, height, maxHeightRoom, maxWidthRoom, minHeightRoom, minWidthRoom);
            } else {
                heightBool = false;
            }
        }
        // Если не делится в обоих направлениях
        if (widthBool && heightBool) {
            int openX = random.nextInt(node.getInputPoint()[0] + 1, node.getOutputPoint()[0] - minWidthRoom - 2);
            int openY = random.nextInt(node.getInputPoint()[1] + 1, node.getOutputPoint()[1] - minHeightRoom - 2);
            int endX = random.nextInt(openX + minWidthRoom, node.getOutputPoint()[0] - 1);
            int endY = random.nextInt(openY + minHeightRoom, node.getOutputPoint()[1] - 1);
            Room room = new Room(openX, openY, endX, endY);
            list.add(room);
            cutRoom(gameMap, room);
        }
    }

    private void createWay(GameMap gameMap, List<Room> list) {
        for (int i = 0; i < list.size() - 1; i++) {
            cutWay(gameMap, list.get(i), list.get(i + 1));
        }
    }

    private boolean splitWidth(GameMap gameMap, List<Room> list, NodeTree node, int width,
                               int maxHeightRoom, int maxWidthRoom, int minHeightRoom, int minWidthRoom) {
        //Истина, если уже не делится
        Random random = new Random();
        boolean roomIf = true;
        if (width >= (maxWidthRoom * 2 + 2)) {
            while (roomIf) {
                int rand = random.nextInt(width);
                if (rand >= maxWidthRoom && width - rand - 1 >= maxWidthRoom) {
                    node.setLeft(new NodeTree(new int[] {node.getInputPoint()[0], node.getInputPoint()[1]},
                            new int[] {node.getInputPoint()[0] + rand, node.getOutputPoint()[1]}, node));
                    createRoom(gameMap, node.getLeft(), list, maxHeightRoom, maxWidthRoom, minHeightRoom, minWidthRoom);
                    node.setRight(new NodeTree(new int[] {node.getInputPoint()[0] + rand + 1, node.getInputPoint()[1]},
                            new int[] {node.getOutputPoint()[0], node.getOutputPoint()[1]}, node));
                    createRoom(gameMap, node.getRight(), list, maxHeightRoom, maxWidthRoom, minHeightRoom, minWidthRoom);
                    roomIf = false;
                }
            }
            return false;
        }
        return true;
    }
    private boolean splitHeight(GameMap gameMap, List<Room> list, NodeTree node, int height,
                                int maxHeightRoom, int maxWidthRoom, int minHeightRoom, int minWidthRoom) {
        //Истина, если уже не делится
        Random random = new Random();
        boolean roomIf = true;
        if (height >= (maxHeightRoom * 2 + 2)) {
            while (roomIf) {
                int rand = random.nextInt(height);
                if (rand >= maxHeightRoom && height - rand - 1 >= maxHeightRoom) {
                    node.setLeft(new NodeTree(new int[] {node.getInputPoint()[0], node.getInputPoint()[1]},
                            new int[] {node.getOutputPoint()[0], node.getInputPoint()[1] + rand}, node));
                    createRoom(gameMap, node.getLeft(), list, maxHeightRoom, maxWidthRoom, minHeightRoom, minWidthRoom);
                    node.setRight(new NodeTree(new int[] {node.getInputPoint()[0], node.getInputPoint()[1] + rand + 1},
                            new int[] {node.getOutputPoint()[0], node.getOutputPoint()[1]}, node));
                    createRoom(gameMap, node.getRight(), list, maxHeightRoom, maxWidthRoom, minHeightRoom, minWidthRoom);
                    roomIf = false;
                }
            }
            return false;
        }
        return true;
    }

    public GameMap createMap(int height, int width) {
        Node[][] miniMap = new Node[height][width];
        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {
                miniMap[i][j] = new Node(NodeState.BLOCK);
            }
        }
        return new GameMap(miniMap,new ArrayList<>());
    }

    private class Tree{
        private NodeTree head;

        public Tree(NodeTree head) {
            this.head = head;
        }

        public NodeTree getHead() {
            return head;
        }

        public void setHead(NodeTree head) {
            this.head = head;
        }
    }
    private class NodeTree{
        private int[] inputPoint = new int[2];
        private int[] outputPoint = new int[2];

        private NodeTree left;
        private NodeTree right;
        private NodeTree parent;

        public void setParent(NodeTree parent) {
            this.parent = parent;
        }

        public NodeTree getParent() {
            return parent;
        }

        public NodeTree(int[] inputPoint, int[] outputPoint, NodeTree parent) {
            this.inputPoint = inputPoint;
            this.outputPoint = outputPoint;
            this.parent = parent;
        }

        public int[] getInputPoint() {
            return inputPoint;
        }

        public int[] getOutputPoint() {
            return outputPoint;
        }

        public NodeTree getLeft() {
            return left;
        }

        public NodeTree getRight() {
            return right;
        }

        public void setInputPoint(int[] inputPoint) {
            this.inputPoint = inputPoint;
        }

        public void setOutputPoint(int[] outputPoint) {
            this.outputPoint = outputPoint;
        }

        public void setLeft(NodeTree left) {
            this.left = left;
        }

        public void setRight(NodeTree right) {
            this.right = right;
        }
    }
}