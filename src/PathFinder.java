import java.util.*;

public class PathFinder {
    public static class PathResult {
        public int distance;
        public List<String> path;
        public PathResult(int distance, List<String> path) {
            this.distance = distance;
            this.path = path;}}
    public static PathResult findShortestPath(WeightedGraph graph, String start, String end) {
        if (!graph.getAllStations().contains(start) || !graph.getAllStations().contains(end)) {
            return null;}
        PriorityQueue<QueueNode> queue = new PriorityQueue<>(Comparator.comparingInt(node -> node.distance));
        Map<String, Integer> distances = new HashMap<>();
        Map<String, String> previous = new HashMap<>();
        Set<String> visited = new HashSet<>();
        for (String station : graph.getAllStations()) {
            distances.put(station, Integer.MAX_VALUE);}
        distances.put(start, 0);
        queue.add(new QueueNode(start, 0));
        while (!queue.isEmpty()) {
            QueueNode current = queue.poll();
            if (current.station.equals(end)) {
                break;}
            if (visited.contains(current.station)) {
                continue;}
            visited.add(current.station);
            for (WeightedGraph.Edge edge : graph.getEdges(current.station)) {
                int newDistance = distances.get(current.station) + edge.weight;
                if (newDistance < distances.get(edge.to)) {
                    distances.put(edge.to, newDistance);
                    previous.put(edge.to, current.station);
                    queue.add(new QueueNode(edge.to, newDistance));}}}
        if (distances.get(end) == Integer.MAX_VALUE) {
            return null;}
        List<String> path = new ArrayList<>();
        String current = end;
        while (current != null) {
            path.add(current);
            current = previous.get(current);}
        Collections.reverse(path);
        return new PathResult(distances.get(end), path);}
    private static class QueueNode {
        String station;
        int distance;
        public QueueNode(String station, int distance) {
            this.station = station;
            this.distance = distance;}}
    public static void printPath(WeightedGraph graph, PathResult result) {
        if (result == null) {
            System.out.println("No path here :/");
            return;}
        System.out.println("Total travel time: " + result.distance + " seconds (" +
                (result.distance / 60) + " minutes " + (result.distance % 60) + " seconds)");
        System.out.println("Route:");
        for (int i = 0; i < result.path.size(); i++) {
            String stationId = result.path.get(i);
            System.out.printf("%2d. %s (%s)%n",
                    i + 1,
                    graph.getStationName(stationId),
                    stationId);}}
}