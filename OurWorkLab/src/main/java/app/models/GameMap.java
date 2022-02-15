package app.models;

public class GameMap {
     private int height = 0;
     private  int weight = 0;
     private  Node[][] map = null;

     public GameMap(int height, int weight, Node[][] map) {
          this.height = height;
          this.weight = weight;
          this.map = map;
     }


     public Node[][] getMap() {
          return map;
     }

     public void setMap(Node[][] map,int weight) {
          this.map = map;
     }

}
