package ue04;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.nio.Buffer;
import java.nio.file.Path;
import java.util.*;

public class Graph {

    private PriorityQueue<Node> pq;
    private List<Node> nodes;

    /**
     * Konstruktor
     */
    public Graph() {
        pq = new PriorityQueue<>(Comparator.comparingInt((Node n) -> n.getDistance()).thenComparing(n -> n.getId()));
        nodes = new ArrayList<>();
    }

    public void readGraphFromAdjacencyMatrixFile (Path file) throws IOException {
        try (BufferedReader br = new BufferedReader(new FileReader(file.toFile()))){
            String line;
            int row = -1;
            List<String> letters = new ArrayList<>();
            while ((line = br.readLine()) != null ){
                if (row == -1){
                    letters = new ArrayList<>(Arrays.asList(line.split(";")));
                    continue;
                }
                String[] values = line.split(";");
                Node n = findOrCreateNode(values[0]);
                for (int i = 0; i < values.length; i++) {
                    if (!values[i].isEmpty()) {
                        int dist = Integer.parseInt(values[i]);
                        n.addEdge(new Edge(dist, findOrCreateNode(values[0])));
                    }
                }
                row++;
            }
        }
        catch (IOException e){
            throw new IOException(e.getMessage());
        }
    }

    private Node findOrCreateNode(String id) {
        Optional<Node> node = nodes.stream().filter(a -> Objects.equals(a.getId(), id)).findFirst();
        if (node.isPresent()) return node.get();
        Node newNode = new Node(id);
        nodes.add(newNode);
        return newNode;
    }

    private Node findNodeById(String id) {
        for (Node node : nodes) {
            if (node.getId().equals(id)) {
                return node;
            }
        }
        return null;
    }


    public String getAllPaths(){
     return "";
    }

    public String getPath(Node n){
        StringBuilder pathBuilder = new StringBuilder();
        for (Node cur = n; cur.previous != null; cur = cur.previous) {
            pathBuilder.insert(0, "--(" + cur.getDistance() + ")-> " + cur.getId() + " ");
        }
        return pathBuilder.toString();
    }

    public void calcWithDijkstra (String startNodeId){

    }



}
