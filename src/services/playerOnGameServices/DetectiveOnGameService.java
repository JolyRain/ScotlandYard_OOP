package services.playerOnGameServices;

import game.ScotlandYardGame;
import game.Ticket;
import game.TypeTicket;
import game.TypePlayer;
import graph.Edge;
import graph.TypeRoad;
import graph.Vertex;
import players.MisterX;
import players.Player;
import services.graphServices.EdgeService;

import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public class DetectiveOnGameService implements PlayerOnGameService {

    public Ticket getCurrentTicket(Player player) {
        return player.getTickets().get(0);
    }

    @Override
    public Vertex getCurrentStation(Player player, ScotlandYardGame game) {
        MisterX misterX = (MisterX) player;
        Map<Player, Vertex> playerVertexMap = game.getPlayerVertexMap();
        return playerVertexMap.get(misterX);
    }

    @Override
    public List<Edge> getAvailableRoads(Player player, ScotlandYardGame game, Ticket ticket) {
        Vertex currentStation = getCurrentStation(player, game);
        TypeTicket typeTicket = ticket.getType();
        Map<TypeRoad, List<Edge>> roadMap = currentStation.getRoadMap();
        return roadMap.get(ScotlandYardGame.TICKET_ROAD_MAP.get(typeTicket));
    }

    @Override
    public List<Vertex> getAvailableStations(Player player, ScotlandYardGame game, Ticket ticket) {
        Vertex currentStation = getCurrentStation(player, game);
        EdgeService edgeService = new EdgeService();
        List<Edge> availableRoads = getAvailableRoads(player, game, ticket);
        List<Vertex> availableStations = new LinkedList<>();
        for (Edge road : availableRoads) {
            Vertex availableStation = edgeService.getNeededVertex(road, currentStation);
            Player playerOnStation = getPlayerOnStation(game, availableStation);
            if (gameService.isFreeStation(availableStation, game) || playerOnStation.getTYPE().equals(TypePlayer.MISTER_X)) {
                availableStations.add(availableStation);
            }
            availableStations.add(availableStation);
        }
        return availableStations;
    }

    @Override
    public Vertex setTargetStation(Player player, ScotlandYardGame game, Ticket ticket) {
        List<Vertex> availableStations = getAvailableStations(player, game, ticket);
        int randomIndex = (int) (Math.random() * availableStations.size());
        return availableStations.get(randomIndex);
    }
}
