package players;

import game.Ticket;
import game.TypePlayer;

import java.util.Map;

public abstract class Player {

    private final TypePlayer TYPE;
    private Map<Ticket, Integer> ticketsMap;

    Player(TypePlayer type) {
        this.TYPE = type;
    }

    public TypePlayer getTYPE() {
        return TYPE;
    }

    public Map<Ticket, Integer> getTicketsMap() {
        return ticketsMap;
    }

    public void setTicketsMap(Map<Ticket, Integer> ticketsMap) {
        this.ticketsMap = ticketsMap;
    }

    @Override
    public String toString() {
        return "Player{" + TYPE + '}';
    }


}