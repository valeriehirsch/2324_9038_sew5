package ue04;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.nio.Buffer;
import java.nio.file.Path;
import java.util.*;

public class Graph {
    public static void main(String[] args) throws IOException {
        Graph g = new Graph(Path.of("src/ue04/resources/Graph_A-H.csv"));
//        Graph g = new Graph(Path.of("resources/kaputt_Graph_A-H_a.csv"));
//        Graph g = new Graph(Path.of("resources/kaputt_Graph_A-H_b.csv"));
//        Graph g = new Graph(Path.of("resources/kaputt_Graph_A-H_c.csv"));
//        Graph g = new Graph(Path.of("resources/kaputt_Graph_A-H_d.csv"));
//        Graph g = new Graph(Path.of("resources/Graph_12_with_names.csv"));
//        Graph g = new Graph(Path.of("resources/unzusammenhaengend_Graph_A-M.csv"));
        System.out.println(g);
        System.out.println();
        System.out.println(g.getAllPaths());
        System.out.println();

        g.calcWithDijkstra("A");
//      g.calcWithDijkstra("Adonaäis");
        System.out.println(g);
        System.out.println();
        System.out.println(g.getAllPaths());
        System.out.println();


        g.calcWithDijkstra("B");
//        g.calcWithDijkstra("Barthhal");
        System.out.println(g);
        System.out.println();
        System.out.println(g.getAllPaths());
        System.out.println();
    }

    private PriorityQueue<Node> pq = new PriorityQueue<>(Comparator.comparingInt(Node::getDistance));;
    private List<Node> nodes = new ArrayList<>();

    /**
     * Konstruktor
     */
    public Graph(Path p) {
        try {
            readGraphFromAdjacencyMatrixFile(p);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /***
     * lies Grafik ein
     * @param file
     * @throws IOException
     */
    public void readGraphFromAdjacencyMatrixFile (Path file) throws IOException {
        try (BufferedReader br = new BufferedReader(new FileReader(file.toFile()))){
            String line;
            int row = 0;
            List<String> letters = new ArrayList<>();
            while ((line = br.readLine()) != null ){
                String[] values = line.split(";");
                Node n = findOrCreateNode(values[0]);
                for (int i = 1; i < values.length; i++) {
                    if (row == 0) continue;
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

    /***
     *
     * @param id name der node
     * @return entweder vorhande node zurück oder created
     */
    private Node findOrCreateNode(String id) {
        Optional<Node> node = nodes.stream().filter(a -> Objects.equals(a.getId(), id)).findFirst();
        if (node.isPresent()) return node.get();
        Node newNode = new Node(id);
        nodes.add(newNode);
        return newNode;
    }

    /***
     *
     * @param id node name
     * @return gibt einen node wert zurück
     */
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

    /***
     *
     * @param n Node für path
     * @return gibt kompletten pfad des nodes zurück
     */
    public String getPath(Node n){
        StringBuilder pathBuilder = new StringBuilder();
        for (Node cur = n; cur.getPrevious() != null; cur = cur.getPrevious()) {
            pathBuilder.insert(0, "--(" + cur.getDistance() + ")-> " + cur.getId() + " ");
        }
        return pathBuilder.toString();
    }

    /**
     * rechnet aus
     * @param startNodeId anfangspunkt (node)
     */
    public void calcWithDijkstra (String startNodeId){

        for (Node node : nodes) {
            node.init();
        }

        Node startNode = findNodeById(startNodeId);
        if (startNode == null) {
            throw new IllegalArgumentException("Start node " + startNodeId + " nicht im Graph");
        }

        startNode.setStartNode();
        pq.add(startNode);

        while (!pq.isEmpty()) {
            Node currentNode = pq.poll(); //first element
            if (currentNode.isVisited()) {
                continue;
            }
            currentNode.visit(pq);
        }

    }

    /**
     * toString
     * @return
     */
    @Override
    public String toString() {
        StringBuilder graphBuilder = new StringBuilder();
        Node startNode = nodes.stream().filter(Node::isFirst).findFirst().orElse(null);

        for (Node node : nodes) {
            if (node == startNode){
                graphBuilder.append(node.getId())
                        .append("----> is start node ")
                        .append(node.edgetoString())
                        .append("\n");
            }
            else{
                graphBuilder.append(node.getId())
                        .append(" [totalDistance: ")
                        .append(node.getDistance() != Integer.MAX_VALUE ? node.getDistance() : "?").append("] ")
                        .append(node.edgetoString())
                        .append("\n");
            }
        }
        return graphBuilder.toString();
    }
}
