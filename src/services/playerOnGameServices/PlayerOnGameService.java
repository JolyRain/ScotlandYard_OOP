package services.playerOnGameServices;

import game.ScotlandYardGame;
import game.Ticket;
import game.TypePlayer;
import graph.Edge;
import graph.Vertex;
import players.MisterX;
import players.Player;

import java.util.List;
import java.util.Map;
import java.util.Set;

public interface PlayerOnGameService {

    default Vertex getCurrentStation(Player player, ScotlandYardGame game) {
        Map<Player, Vertex> playerVertexMap = game.getPlayerVertexMap();
        return playerVertexMap.get(player);
    }

    List<Edge> getAvailableRoads(Player player, ScotlandYardGame game, Ticket ticket);

    List<Vertex> getAvailableStations(Player player, ScotlandYardGame game, Ticket ticket); // ???

    Vertex setTargetStation(Player player, ScotlandYardGame game, Ticket ticket);

    default boolean isFreeStation(Vertex station, ScotlandYardGame game) {
        return game.getVertexPlayerMap().get(station) == null;
    }

    default TypePlayer getTypePlayerOnStation(Vertex station, ScotlandYardGame game) {
        if (!isFreeStation(station, game)) {
            return game.getVertexPlayerMap().get(station).getTYPE();
        }
        return null;
    }
}
