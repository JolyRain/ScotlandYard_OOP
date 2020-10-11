package services.playerOnGameServices;

import game.ScotlandYardGame;
import game.Ticket;
import game.TypePlayer;
import graph.Edge;
import graph.TypeRoad;
import graph.Vertex;
import players.MisterX;
import players.Player;

import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public class DetectiveOnGame implements PlayerOnGameService {

    @Override
    public Vertex getCurrentStation(Player player, ScotlandYardGame game) {
        MisterX misterX = (MisterX) player;
        Map<Player, Vertex> playerVertexMap = game.getPlayerVertexMap();
        return playerVertexMap.get(misterX);
    }

    @Override
    public List<Edge> getAvailableRoads(Player player, ScotlandYardGame game, Ticket ticket) {
        Vertex currentStation = getCurrentStation(player, game);
        Map<TypeRoad, List<Edge>> roadMap = currentStation.getRoadMap();
        switch (ticket) {
            case TAXI:
                return roadMap.get(TypeRoad.TAXI);
            case BUS:
                return roadMap.get(TypeRoad.BUS);
            case METRO:
                return roadMap.get(TypeRoad.METRO);
        }
        return null;
    }

    @Override
    public List<Vertex> getAvailableStations(Player player, ScotlandYardGame game, Ticket ticket) {
        Vertex currentStation = getCurrentStation(player, game);
        List<Edge> availableRoads = getAvailableRoads(player, game, ticket);
        List<Vertex> availableStations = new LinkedList<>();
        for (Edge road : availableRoads) {
            Vertex availableStation = road.getStartVertex().equals(currentStation) ? road.getEndVertex() : road.getStartVertex();
            if (isFreeStation(availableStation, game)
                    || getTypePlayerOnStation(availableStation, game).equals(TypePlayer.MISTER_X)) {
                availableStations.add(availableStation);
            }
            availableStations.add(road.getStartVertex().equals(currentStation) ? road.getEndVertex() : road.getStartVertex());
        }
        return availableStations;
    }

    //stupid logic - random choose of station
    @Override
    public Vertex setTargetStation(Player player, ScotlandYardGame game, Ticket ticket) {
        List<Vertex> availableStations = getAvailableStations(player, game, ticket);
        int randomIndex = (int) (Math.random() * availableStations.size());
        return availableStations.get(randomIndex);
    }
}
