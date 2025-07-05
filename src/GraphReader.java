import java.io.*;
import java.util.*;

public class GraphReader {
    public static WeightedGraph readGraph(String graphFile, String stationsFile) throws IOException {
        WeightedGraph graph = new WeightedGraph();
        readStationNames(graph, stationsFile);
        readGraphConnections(graph, graphFile);
        return graph;}
    private static void readStationNames(WeightedGraph graph, String filename) throws IOException {
        try (BufferedReader reader = new BufferedReader(new FileReader(filename))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(", ");
                if (parts.length >= 2) {
                    graph.addStation(parts[0], parts[1]);}}}}
    private static void readGraphConnections(WeightedGraph graph, String filename) throws IOException {
        try (BufferedReader reader = new BufferedReader(new FileReader(filename))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(" ");
                if (parts.length >= 1) {
                    String fromStation = parts[0];
                    for (int i = 1; i < parts.length; i++) {
                        String[] edgeParts = parts[i].split(",");
                        if (edgeParts.length == 2) {
                            String toStation = edgeParts[0];
                            int weight = Integer.parseInt(edgeParts[1]);
                            graph.addEdge(fromStation, toStation, weight);}}}}}}
}