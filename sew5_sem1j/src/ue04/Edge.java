package ue04;

public class Edge{

     int distance;
     Node neighbor;

    /***
     * Konstruktor
     * @param distance
     * @param neighbor
     */
    public Edge(int distance, Node neighbor) {
        this.distance = distance;
        this.neighbor = neighbor;
    }

}
