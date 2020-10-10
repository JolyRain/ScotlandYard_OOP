package services.graphServices;

import graph.Edge;
import graph.Graph;
import graph.Vertex;

import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class GraphService {

    private Iterable<Vertex> adjacent(Graph graph, Vertex vertex) {
        return graph.getAdjacencyMap().get(vertex);
    }

    public boolean addVertex(Graph  graph, Vertex vertex) {
        Map<Vertex, Set<Vertex>> adjacencyMap = graph.getAdjacencyMap();
        Set<Vertex> vertices = graph.getVertices();
        vertex.setStationNumber(String.valueOf(vertices.size()));
        adjacencyMap.put(vertex, new HashSet<>());
        return vertices.add(vertex);
    }

    public Vertex getVertex(Graph graph, String stationNumber) {
        Set<Vertex> vertices = graph.getVertices();
        for (Vertex vertex : vertices) {
            if (vertex.getStationNumber().equals(stationNumber)) return vertex;
        }
        return null;
    }

    private boolean isAdjacent(Graph graph, Vertex firstVertex, Vertex secondVertex) {
        for (Vertex adjacent : adjacent(graph, firstVertex)) {
            if (adjacent.equals(secondVertex)) return true;
        }
        return false;
    }

    public Edge getEdge(Graph graph, Vertex start, Vertex end) {
        List<Edge> edges = graph.getEdges();
        EdgeService edgeService = new EdgeService();
        for (Edge edge : edges) {
            if (edgeService.contains(edge, start) && edgeService.contains(edge, end)) return edge;
        }
        return null;
    }

    public void addEdge(Graph graph, Edge edge) {
        Map<Vertex, Set<Vertex>> adjacencyMap = graph.getAdjacencyMap();
        Vertex startVertex = edge.getStartVertex();
        Vertex endVertex = edge.getEndVertex();
        if (!isAdjacent(graph, startVertex, endVertex)) {
            adjacencyMap.get(startVertex).add(endVertex);
            startVertex.getRoadMap().get(edge.getType()).add(edge);
            adjacencyMap.get(endVertex).add(startVertex);
            endVertex.getRoadMap().get(edge.getType()).add(edge);
            graph.getEdges().add(edge);
        }
    }

    public Vertex getVertex(Graph graph, Vertex target) {
        Set<Vertex> vertices = graph.getVertices();
        for (Vertex current : vertices) {
            if (current.equals(target)) return current;
        }
        return  null;
    }


    public void clear(Graph graph) {
        Map<Vertex, Set<Vertex>> adjacencyMap = graph.getAdjacencyMap();
        Set<Vertex> vertices = graph.getVertices();
        List<Edge> edges = graph.getEdges();
        if (adjacencyMap != null) adjacencyMap.clear();
        if (vertices != null) vertices.clear();
        if (edges != null) edges.clear();
    }
}
