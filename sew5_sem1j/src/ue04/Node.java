package ue04;

import java.util.Comparator;
import java.util.TreeSet;

public class Node implements Comparable<Node> {
     String id;
     TreeSet<Edge> edges;
     int distance;
     Node previous;
     boolean isVisited;

    /***
     * Konstruktor
     * @param id Name von Node
     */
    public Node(String id) {
        this.id = id;
        edges = new TreeSet<>(Comparator.comparingInt((Edge e) -> e.neighbor.distance).thenComparing(e -> e.neighbor.id));
        distance = Integer.MAX_VALUE;
        previous = null;
        isVisited = false;
    }

    /***
     *  Comparable
     * @param o the object to be compared.
     * @return wer die kleine distance hat
     */
    @Override
    public int compareTo(Node o) {
        return Integer.compare(distance, o.distance);
    }

    @Override
    public String toString() {
        return "Node{" +
                "id='" + id + '\'' +
                ", edges=" + edges +
                ", distance=" + distance +
                ", previous=" + previous +
                ", isVisited=" + isVisited +
                '}';
    }

    public String getPath(){
        if (distance == Integer.MAX_VALUE){
            return "no path available for " + id;
        }
        else {
            return previous.id + " (" + previous.distance + ") ";
        }

    }

}
