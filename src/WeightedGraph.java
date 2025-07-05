import java.util.*;

public class WeightedGraph {
    private Map<String, List<Edge>> adjacencyList;
    private Map<String, String> stationNames;
    public WeightedGraph() {
        adjacencyList = new HashMap<>();
        stationNames = new HashMap<>();}
    public void addStation(String id, String name) {
        stationNames.put(id, name);
        adjacencyList.putIfAbsent(id, new ArrayList<>());}
    public void addEdge(String from, String to, int weight) {adjacencyList.get(from).add(new Edge(to, weight));}
    public String getStationName(String id) {return stationNames.getOrDefault(id, "Unknown Station");}
    public List<Edge> getEdges(String stationId) {return adjacencyList.getOrDefault(stationId, new ArrayList<>());}
    public Set<String> getAllStations() {return adjacencyList.keySet();}
    public static class Edge {
        String to;
        int weight;
        public Edge(String to, int weight) {
            this.to = to;
            this.weight = weight;}}
}