package ue04;

import com.sun.source.tree.Tree;

import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.TreeSet;

public class Node implements Comparable<Node> {
     private String id;
     private TreeSet<Edge> edges = new TreeSet<>();
     private int distance = Integer.MAX_VALUE;
     private Node previous = null;
     private boolean isVisited = false;

    /***
     * Konstruktor
     * @param id Name von Node
     */
    public Node(String id) {
        this.id = id;
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

    /**
     * getter
     * @return
     */
    public String getId() {
        return id;
    }

    public int getDistance() {
        return distance;
    }

    public Node getPrevious() {
        return previous;
    }

    public boolean isVisited() {
        return isVisited;
    }


    /*
    macht keinen sinn da man durch alle nodes (bzw deren prev.) durchiterieren muss.
    public String getPath(){
        if (distance == Integer.MAX_VALUE){
            return "no path available for " + id;
        }
        else {
            return previous.id + " (" + previous.distance + ") ";
        }

    }
     */


    /**
     * adds edge
     * @param edgeToAdd
     */
    public void addEdge(Edge edgeToAdd){
        edges.add(edgeToAdd);
    }

    /***
     * checkt ob erster punkt (start node)
     * @return
     */
    public boolean isFirst() {
        return previous == null && distance == 0;
    }

    public void setStartNode() {
        distance = 0;
    }
    public void init() {
        isVisited = false;
        previous = null;
        distance = Integer.MAX_VALUE;
    }

    public void visit(PriorityQueue priorityQueue){

    }

}
