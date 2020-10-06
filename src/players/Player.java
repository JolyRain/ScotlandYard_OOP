package players;

import game.Ticket;

import java.util.Map;

public abstract class Player {
    private final Type TYPE;
    private Map<Ticket, Integer> ticketsMap;

    Player(Type type) {
        this.TYPE = type;
    }

    public Type getTYPE() {
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

    public enum Type {
        MISTER_X, DETECTIVE
    }
}