package services.playerServices;

import game.Ticket;
import graph.Vertex;
import players.Player;

import java.util.Map;

public interface PlayerService {

    void moveTo(Player player, Vertex targetStation, Ticket ticket);

    default void removeTicket(Player player, Ticket ticket) {
        Map<Ticket, Integer> ticketsMap = player.getTicketsMap();
        Integer amountTickets = player.getTicketsMap().get(ticket);
        ticketsMap.put(ticket, amountTickets - 1);
    }

}
