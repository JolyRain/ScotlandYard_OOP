package services.playerServices;

import game.ScotlandYardGame;
import game.Ticket;
import game.WayBill;
import graph.Vertex;
import players.MisterX;
import players.Player;

import java.util.Map;

public class MisterXService implements PlayerService {

    @Override
    public void moveTo(Player player, ScotlandYardGame game, Vertex targetStation, Ticket ticket) {
        MisterX misterX = (MisterX) player;
        WayBill wayBill = misterX.getWayBill();
        Map<Vertex, Ticket> stepsMap = wayBill.getStepsMap();
        Integer stepsToShow = wayBill.getStepsToShow();
        stepsMap.put(targetStation, ticket);
        removeTicket(misterX, ticket);
        stepsToShow++;
        game.setStepsToShow(stepsToShow);
        Map<Player, Vertex> playerVertexMap = game.getPlayerVertexMap();
        Map<Vertex, Player> vertexPlayerMap = game.getVertexPlayerMap();
        playerVertexMap.put(misterX, targetStation);
        vertexPlayerMap.put(targetStation, misterX);
    }

    public void addTicket(MisterX misterX, Ticket ticket) {
        Map<game.Ticket, Integer> ticketsMap = misterX.getTicketsMap();
        Integer amountTickets = ticketsMap.get(ticket);
        ticketsMap.put(ticket, amountTickets + 1);
    }

//    public void doubleMove(MisterX misterX, Vertex firstTarget, Vertex secondTarget) {
//
//    }
}
