package ue04;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.nio.Buffer;
import java.nio.file.Path;
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

    public void readGraphFromAdjacencyMatrixFile (Path file) throws IOException {
        try (BufferedReader br = new BufferedReader(new FileReader(file.toFile()))){
            String line;
            int row = 0;
            while ((line = br.readLine()) != null ){
                String[] values = line.split(";");
                for (int i = 0; i < values.length; i++) {
                    int dist = Integer.parseInt(values[i]);
                    if (dist != 0){
                        //hier dann nodes und edge adden -> dafür fehlen methoden bei node
                    }
                }
                row++;
            }
        }
        catch (IOException e){
            throw new IOException(e.getMessage());
        }
    }

    public String getAllPaths(){
     return "";
    }

    public void calcWithDijkstra (String startNodeId){

    }



}
