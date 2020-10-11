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

public class MisterXOnGameService implements PlayerOnGameService {

    @Override
    public List<Edge> getAvailableRoads(Player player, ScotlandYardGame game, Ticket ticket) {
        Vertex currentStation = getCurrentStation(player, game);
        Map<TypeRoad, List<Edge>> roadMap = currentStation.getRoadMap();
        List<Edge> allRoads = new LinkedList<>();
        switch (ticket) {
            case TAXI:
                return roadMap.get(TypeRoad.TAXI);
            case BUS:
                return roadMap.get(TypeRoad.BUS);
            case METRO:
                return roadMap.get(TypeRoad.METRO);
            case BLACK:
                allRoads.addAll(roadMap.get(TypeRoad.METRO));
                allRoads.addAll(roadMap.get(TypeRoad.BUS));
                allRoads.addAll(roadMap.get(TypeRoad.TAXI));
                return allRoads;
                //            case DOUBLE:   think over the mechanics of a double ticket as a carte blanche for an additional move
        }
        return null;
    }


    @Override
    public List<Vertex> getAvailableStations(Player player, ScotlandYardGame game, Ticket ticket) {
        Vertex currentStation = getCurrentStation(player, game);
        List<Edge> availableRoads = getAvailableRoads(player, game, ticket);
        List<Vertex> availableStations = new LinkedList<>();
        for (Edge road : availableRoads) {
//            Vertex availableStation = road.getStartVertex().equals(currentStation) ? road.getEndVertex() : road.getStartVertex();
//            if (isFreeStation(availableStation, game)) availableStations.add(availableStation);
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
