package services;

import game.Ticket;
import game.WayBill;
import graph.TypeRoad;
import graph.Vertex;
import players.MisterX;
import players.Player;

import java.util.Map;
import java.util.Timer;

public class MisterXService implements PlayerService {

    @Override
    public void moveTo(Player player, Vertex targetStation, Ticket ticket) {
        MisterX misterX = (MisterX) player;
        WayBill wayBill = misterX.getWayBill();
        Map<Vertex, Ticket> stepsMap = wayBill.getStepsMap();
        Integer stepsToShow = wayBill.getStepsToShow();
        stepsMap.put(targetStation, ticket);
        removeTicket(misterX, ticket);
        stepsToShow++;                                      //исправить

    }

    @Override
    public void removeTicket(Player player, Ticket ticket) {
        Map<Ticket, Integer> ticketsMap = player.getTicketsMap();
        Integer amountTickets = player.getTicketsMap().get(ticket);
        ticketsMap.put(ticket, amountTickets - 1);
    }

    public void addTicket(MisterX misterX, Ticket ticket) {
        Map<game.Ticket, Integer> ticketsMap = misterX.getTicketsMap();
        Integer amountTickets = ticketsMap.get(ticket);
        ticketsMap.put(ticket, amountTickets + 1);
    }

    public void doubleMove(MisterX misterX, Vertex firstTarget, Vertex secondTarget) {

    }
}
