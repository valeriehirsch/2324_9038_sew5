package ue04;

import java.util.Comparator;
import java.util.TreeSet;

public class Node implements Comparable<Node> {
     String id;
     TreeSet<Edge> edges;
     int distance;
     Node previous;
     boolean isVisited;

    public Node(String id) {
        this.id = id;
        edges = new TreeSet<>(Comparator.comparingInt((Edge e) -> e.neighbor.distance).thenComparing(e -> e.neighbor.id));
        distance = Integer.MAX_VALUE;
        previous = null;
        isVisited = false;
    }

    @Override
    public int compareTo(Node o) {
        return Integer.compare(distance, o.distance);
    }
}
