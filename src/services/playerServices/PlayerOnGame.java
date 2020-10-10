package services.playerServices;

import game.ScotlandYardGame;
import game.Ticket;
import graph.Vertex;
import players.Player;

import java.util.Set;

public interface PlayerOnGame {
     Vertex getCurrentStation(Player player, ScotlandYardGame game);
     Set<Vertex> getAvailableStations(Player player, ScotlandYardGame game, Ticket ticket);
}
