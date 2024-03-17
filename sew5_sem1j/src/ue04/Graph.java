package ue04;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.PriorityQueue;

public class Graph {

    private PriorityQueue<Node> pq;
    private List<Node> nodes;

    /**
     * Konstruktor
     */
    public Graph() {
        pq = new PriorityQueue<>(Comparator.comparingInt((Node n) -> n.distance).thenComparing(n -> n.id));
        nodes = new ArrayList<>();
    }

}
