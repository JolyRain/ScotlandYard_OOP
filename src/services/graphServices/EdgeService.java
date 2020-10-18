package services.graphServices;

import graph.Edge;
import graph.Vertex;

public class EdgeService {

    public boolean contains(Edge edge, Vertex vertex) {
        return edge.getStartVertex().equals(vertex) || edge.getEndVertex().equals(vertex);
    }

    public Vertex getNeededVertex(Edge edge, Vertex targetVertex) {
        return edge.getStartVertex().equals(targetVertex) ? edge.getEndVertex() : edge.getStartVertex();
    }
}
