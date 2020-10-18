package services.playerServices;

import game.ScotlandYardGame;
import game.Ticket;
import graph.Vertex;
import players.Detective;
import players.MisterX;
import players.Player;

import java.util.Map;

public class DetectiveService implements PlayerService {

    private MisterXService misterXService = new MisterXService();

    @Override
    public void moveTo(Player player, ScotlandYardGame game, Vertex targetStation, Ticket ticket) {
        Detective detective = (Detective) player;
        Map<Player, Vertex> playerVertexMap = game.getPlayerVertexMap();
        Map<Vertex, Player> vertexPlayerMap = game.getVertexPlayerMap();
        playerVertexMap.put(detective, targetStation);
        vertexPlayerMap.put(targetStation, detective);
        removeTicket(detective, game.getMisterX(), ticket);
    }

    private void removeTicket(Player player, MisterX misterX, Ticket ticket) {
        removeTicket(player, ticket);
        misterXService.addTicket(misterX, ticket);
    }
}
