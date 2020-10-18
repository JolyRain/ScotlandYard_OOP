package services.playerServices;

import game.ScotlandYardGame;
import game.Ticket;
import game.TypeTicket;
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
        Map<Vertex, TypeTicket> stepsMap = wayBill.getStepsMap();
        Integer stepsToShow = wayBill.getStepsToShow();
        stepsMap.put(targetStation, ticket.getType());
        removeTicket(misterX, ticket);
        stepsToShow++;
        wayBill.setStepsToShow(stepsToShow);      //где лучше хранить значение шагов
        Map<Player, Vertex> playerVertexMap = game.getPlayerVertexMap();
        Map<Vertex, Player> vertexPlayerMap = game.getVertexPlayerMap();
        playerVertexMap.put(misterX, targetStation);
        vertexPlayerMap.put(targetStation, misterX);
    }

    public void addTicket(MisterX misterX, Ticket ticket) {
        Map<TypeTicket, Integer> ticketsMap = misterX.getTicketsMap();
        Integer amountTickets = ticketsMap.get(ticket.getType());
        ticketsMap.put(ticket.getType(), amountTickets + 1);
        misterX.getTickets().add(ticket);
    }

    public void showCurrentStation(MisterX misterX, ScotlandYardGame game) {
        WayBill wayBill = misterX.getWayBill();
        Integer stepsToShow = wayBill.getStepsToShow();
        if (stepsToShow % 3 == 0) wayBill.setShowedStation(game.getPlayerVertexMap().get(misterX));
    }

    //говнокод
    public void doubleMove(MisterX misterX, ScotlandYardGame game, Vertex firstTargetStation, Vertex secondTargetStation) {
//            this.moveTo(misterX, game, targetStation, ticket);

//        Map<Ticket, Integer> ticketsMap = misterX.getTicketsMap();
//        ticketsMap.put(Ticket.DOUBLE, ticketsMap.get(Ticket.DOUBLE) - 1);
    }
}
