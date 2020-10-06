package game;

import graph.Edge;
import graph.Graph;
import graph.Vertex;
import players.MisterX;
import players.Player;

import java.util.List;
import java.util.Map;
import java.util.Set;

public class ScotlandYardGame {
    private Graph graph;
    private Map<Vertex, Player> vertexPlayerMap;
    private Map<Player, Vertex> playerVertexMap;
    private Set<Player> detectives;
    private MisterX misterX;
    private Integer stepsToShow;
    private List<Vertex> stations;
    private Vertex lastThiefLocation;
    private Edge thiefEdge;
}
