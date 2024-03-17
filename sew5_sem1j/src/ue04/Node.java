package ue04;

import java.util.Comparator;
import java.util.TreeSet;

public class Node implements Comparable<Node> {
    private String id;
    private TreeSet<Edge> edges;
    private int distance;
    private Node previous;
    private boolean isVisited;



    @Override
    public int compareTo(Node o) {
        return Integer.compare(distance, o.distance);
    }
}
