package graph;

import java.util.*;

public class Graph {

    private Map<Vertex, Set<Vertex>> adjacentVerticesMap = new HashMap<>();
    private Set<Vertex> vertices = new HashSet<>();
    private List<Edge> edges = new LinkedList<>();

//    public boolean addVertex(Vertex vertex) {
//        vertex.setStationNumber(String.valueOf(vertices.size()));
//        adjacentVerticesMap.put(vertex, new HashSet<>());
//        return vertices.add(vertex);
//    }
//
//    public Vertex getVertex(String stationNumber) {
//        for (Vertex vertex : vertices) {
//            if (vertex.getStationNumber().equals(stationNumber)) return vertex;
//        }
//        return null;
//    }
//
//    private boolean isAdjacent(Vertex firstVertex, Vertex secondVertex) {
//        for (Vertex adjacent : adjacent(firstVertex)) {
//            if (adjacent.equals(secondVertex)) return true;
//        }
//        return false;
//    }
//
//    public Edge getEdge(Vertex start, Vertex end) {
//        for (Edge edge : edges) {
//            if (edge.contains(start) && edge.contains(end)) return edge;
//        }
//        return null;
//    }
//
//    public void addEdge(Edge edge) {
//        Vertex startVertex = edge.getStartVertex();
//        Vertex endVertex = edge.getEndVertex();
//        if (!isAdjacent(startVertex, endVertex)) {
//            adjacentVerticesMap.get(startVertex).add(endVertex);
//            adjacentVerticesMap.get(endVertex).add(startVertex);
//            edges.add(edge);
//        }
//    }
//
//
//    public void clear() {
//        if (adjacentVerticesMap != null) adjacentVerticesMap.clear();
//        if (vertices != null) vertices.clear();
//        if (edges != null) edges.clear();
//    }

//    private Iterable<Vertex> adjacent(Vertex vertex) {
//        return adjacentVerticesMap.get(vertex);
//    }

    public Set<Vertex> getVertices() {
        return vertices;
    }

    public List<Edge> getEdges() {
        return edges;
    }

    public void setEdges(List<Edge> edges) {
        this.edges = edges;
    }

    public Map<Vertex, Set<Vertex>> getAdjacencyMap() {
        return adjacentVerticesMap;
    }
}
