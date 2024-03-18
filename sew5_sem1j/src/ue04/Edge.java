package ue04;

public class Edge{

     private int distance;
     private Node neighbor;

    /***
     * Konstruktor
     * @param distance
     * @param neighbor
     */
    public Edge(int distance, Node neighbor) {
        this.distance = distance;
        this.neighbor = neighbor;
    }

    public int getDistance() {
        return distance;
    }

    public Node getNeighbor() {
        return neighbor;
    }
}
