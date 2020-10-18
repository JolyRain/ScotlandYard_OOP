package game;

import graph.Graph;
import graph.TypeRoad;
import graph.Vertex;
import players.MisterX;
import players.Player;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class ScotlandYardGame {
    public static final Map<TypeTicket, TypeRoad> TICKET_ROAD_MAP = new HashMap<>();
    public static final Integer MOVE_AMOUNT = 23;
    public static final Integer DETECTIVES_AMOUNT = 3;

    static {
        TICKET_ROAD_MAP.put(TypeTicket.TAXI, TypeRoad.TAXI);
        TICKET_ROAD_MAP.put(TypeTicket.BUS, TypeRoad.BUS);         //говнокод какой-то
        TICKET_ROAD_MAP.put(TypeTicket.METRO, TypeRoad.METRO);
    }

    private Graph graph;
    private GameState gameState;
    private Map<Vertex, Player> vertexPlayerMap;
    private Map<Player, Vertex> playerVertexMap;
    private Set<Player> detectives;
    private MisterX misterX;
    private List<Vertex> stations;

    public Graph getGraph() {
        return graph;
    }

    public void setGraph(Graph graph) {
        this.graph = graph;
    }

    public GameState getGameState() {
        return gameState;
    }

    public void setGameState(GameState gameState) {
        this.gameState = gameState;
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

    public List<Vertex> getStations() {
        return stations;
    }

    public void setStations(List<Vertex> stations) {
        this.stations = stations;
    }

}
