package ue04;

import com.sun.source.tree.Tree;

import java.sql.Array;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.TreeSet;

public class Node {
     private String id;
     private ArrayList<Edge> edges = new ArrayList<>();
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

    public String edgetoString() {
        StringBuilder edgeStr = new StringBuilder();
        for (Edge edge : edges) {
            edgeStr.append(edge.getNeighbor().getId())
                    .append(":")
                    .append(edge.getDistance())
                    .append(" ");
        }
        return edgeStr.toString();
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
        isVisited = true;
        for (Edge edge : edges) {
            Node neighbour = edge.getNeighbor();
            int newDist = distance + edge.getDistance();
            if (newDist < neighbour.distance) {
                neighbour.distance = newDist;
                neighbour.previous = this;
                priorityQueue.add(edge.getNeighbor());
            }
        }
    }

}
