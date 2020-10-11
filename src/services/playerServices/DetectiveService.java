package services.playerServices;

import game.ScotlandYardGame;
import game.Ticket;
import game.WayBill;
import graph.Vertex;
import players.Detective;
import players.MisterX;
import players.Player;

import java.util.Map;

public class DetectiveService implements PlayerService {

    @Override
    public void moveTo(Player player, ScotlandYardGame game, Vertex targetStation, Ticket ticket) {
        Detective detective = (Detective) player;
        Map<Player, Vertex> playerVertexMap = game.getPlayerVertexMap();
        Map<Vertex, Player> vertexPlayerMap = game.getVertexPlayerMap();
        playerVertexMap.put(detective, targetStation);
        vertexPlayerMap.put(targetStation, detective);
        removeTicket(detective, game.getMisterX(), ticket);
    }

    public void removeTicket(Player player, MisterX misterX, Ticket ticket) {
        removeTicket(player, ticket);
        MisterXService misterXService = new MisterXService();
        misterXService.addTicket(misterX, ticket);
    }
}
