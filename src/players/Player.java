package players;

import game.Ticket;
import game.TypeTicket;
import game.TypePlayer;

import java.util.List;
import java.util.Map;

public abstract class Player {

    private final TypePlayer TYPE;
    private Map<TypeTicket, Integer> ticketsMap;
    private List<Ticket> tickets;

    Player(TypePlayer type) {
        this.TYPE = type;
    }

    public TypePlayer getTYPE() {
        return TYPE;
    }

    public Map<TypeTicket, Integer> getTicketsMap() {
        return ticketsMap;
    }

    public void setTicketsMap(Map<TypeTicket, Integer> ticketsMap) {
        this.ticketsMap = ticketsMap;
    }

    public List<Ticket> getTickets() {
        return tickets;
    }

    public void setTickets(List<Ticket> tickets) {
        this.tickets = tickets;
    }

    @Override
    public String toString() {
        return "Player{" + TYPE + '}';
    }


}