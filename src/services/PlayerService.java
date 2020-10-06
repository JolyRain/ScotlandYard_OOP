package services;

import game.Ticket;
import graph.Vertex;
import players.Player;

public interface PlayerService {

    void moveTo(Player player, Vertex targetStation, Ticket ticket);

    void removeTicket(Player player, Ticket ticket);

}
