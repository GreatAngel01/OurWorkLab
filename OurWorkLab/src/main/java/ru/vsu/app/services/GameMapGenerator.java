package ru.vsu.app.services;

import ru.vsu.app.models.*;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

public class GameMapGenerator {
    public static GameMap generate(int height, int width, GameMapType type, Direction start, Direction exit) {
        if (type == GameMapType.UNDERGROUND) {
            Random random = new Random();
            GameMap gameMap = createMap(height, width);
            Room[] rooms = new Room[random.nextInt(2, 5)];

            for (int i = 0; i < rooms.length; i++) {
                boolean intersects = true;
                int counter = 0;
                while (intersects && counter <= 30) {
                    intersects = false;
                    int openX = random.nextInt(2, width - 4);
                    int openY = random.nextInt(2, height - 4);
                    int endX = random.nextInt(openX + 2, width - 2);
                    int endY = random.nextInt(openY + 2, height - 2);

                    rooms[i] = new Room(openX, openY, endX, endY);

                    for (int j = openY - 2; j <= endY + 2; j++) {
                        for (int k = openX - 2; k <= endX + 2; k++) {
                            if (gameMap.getMap()[j][k].getState() == NodeState.NONE) {
                                intersects = true;
                            }
                        }
                    }
                    counter++;
                }
                if (counter != 30) {
                    cutRoom(gameMap, rooms[i]);
                }
            }

            for (Room i : rooms) {
                for (Room j : rooms) {
                    if (i != j) {
                        cutWay(gameMap, i, j);
                    }
                }
            }
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
            cutExitWay(gameMap, tempX, tempY, rooms);
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

            cutExitWay(gameMap, tempX, tempY, rooms);

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

    public static void cutExitWay(GameMap gameMap, int x, int y, Room[] rooms) {
        Random random = new Random();
        Node[][] nodes = gameMap.getMap();
        Room room = rooms[random.nextInt(rooms.length)];
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

    public static void cutRoom(GameMap gameMap, Room room) {
        Node[][] nodes = gameMap.getMap();
        for (int i = room.getLeftUpX(); i <= room.getRightDownX(); i++) {
            for (int j = room.getLeftUpY(); j < room.getRightDownY(); j++) {
                nodes[j][i].setState(NodeState.NONE);
            }
        }
        gameMap.setMap(nodes);
    }

    public static void cutWay(GameMap gameMap, Room firstRoom, Room secondRoom) {
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
        if (outputY <= inputY) {
            input = outputY;
            output = inputY;
        }
        for (int i = input; i <= output; i++) {
            nodes[i][outputX].setState(NodeState.NONE);
        }
        gameMap.setMap(nodes);
    }

    public static GameMap createMap(int height, int width) {
        Node[][] miniMap = new Node[height][width];
        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {
                miniMap[i][j] = new Node(NodeState.BLOCK);
            }
        }
        return new GameMap(miniMap);
    }
}
