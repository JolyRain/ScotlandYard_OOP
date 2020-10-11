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

    public Graph getGraph() {
        return graph;
    }

    public void setGraph(Graph graph) {
        this.graph = graph;
    }

    public Map<Vertex, Player> getVertexPlayerMap() {
        return vertexPlayerMap;
    }

    public void setVertexPlayerMap(Map<Vertex, Player> vertexPlayerMap) {
        this.vertexPlayerMap = vertexPlayerMap;
    }

    public Map<Player, Vertex> getPlayerVertexMap() {
        return playerVertexMap;
    }

    public void setPlayerVertexMap(Map<Player, Vertex> playerVertexMap) {
        this.playerVertexMap = playerVertexMap;
    }

    public Set<Player> getDetectives() {
        return detectives;
    }

    public void setDetectives(Set<Player> detectives) {
        this.detectives = detectives;
    }

    public MisterX getMisterX() {
        return misterX;
    }

    public void setMisterX(MisterX misterX) {
        this.misterX = misterX;
    }

    public Integer getStepsToShow() {
        return stepsToShow;
    }

    public void setStepsToShow(Integer stepsToShow) {
        this.stepsToShow = stepsToShow;
    }

    public List<Vertex> getStations() {
        return stations;
    }

    public void setStations(List<Vertex> stations) {
        this.stations = stations;
    }

    public Vertex getLastThiefLocation() {
        return lastThiefLocation;
    }

    public void setLastThiefLocation(Vertex lastThiefLocation) {
        this.lastThiefLocation = lastThiefLocation;
    }

    public Edge getThiefEdge() {
        return thiefEdge;
    }

    public void setThiefEdge(Edge thiefEdge) {
        this.thiefEdge = thiefEdge;
    }

}
