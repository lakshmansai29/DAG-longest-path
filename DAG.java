import java.util.*;

class Vertex {
    long id;
    public Vertex(long id) { this.id = id; }
}

class Edge {
    Vertex from, to;
    public Edge(Vertex from, Vertex to) { this.from = from; this.to = to; }
}

class DAG {
    private Map<Vertex, List<Vertex>> adjList = new HashMap<>();

    public void addEdge(Vertex from, Vertex to) {
        adjList.computeIfAbsent(from, k -> new ArrayList<>()).add(to);
        adjList.putIfAbsent(to, new ArrayList<>());
    }

    private List<Vertex> topologicalSort() {
        Map<Vertex, Integer> inDegree = new HashMap<>();
        Queue<Vertex> queue = new LinkedList<>();
        List<Vertex> topoOrder = new ArrayList<>();

        for (Vertex v : adjList.keySet()) { inDegree.put(v, 0); }
        for (Vertex v : adjList.keySet()) {
            for (Vertex neighbor : adjList.get(v)) {
                inDegree.put(neighbor, inDegree.get(neighbor) + 1);
            }
        }
        for (Vertex v : inDegree.keySet()) {
            if (inDegree.get(v) == 0) queue.add(v);
        }
        while (!queue.isEmpty()) {
            Vertex curr = queue.poll();
            topoOrder.add(curr);
            for (Vertex neighbor : adjList.get(curr)) {
                inDegree.put(neighbor, inDegree.get(neighbor) - 1);
                if (inDegree.get(neighbor) == 0) queue.add(neighbor);
            }
        }
        return topoOrder;
    }

    public int findLongestPath(Vertex start) {
        Map<Vertex, Integer> longestPath = new HashMap<>();
        for (Vertex v : adjList.keySet()) { longestPath.put(v, Integer.MIN_VALUE); }
        longestPath.put(start, 0);

        for (Vertex v : topologicalSort()) {
            if (longestPath.get(v) != Integer.MIN_VALUE) {
                for (Vertex neighbor : adjList.get(v)) {
                    longestPath.put(neighbor, Math.max(longestPath.get(neighbor), longestPath.get(v) + 1));
                }
            }
        }
        return Collections.max(longestPath.values());
    }
}