package players;

//import Unnamed.Transport;

import Game.Transport;

public abstract class Player {
    private final Type TYPE;
    private int taxiTickets;
    private int busTickets;
    private int undergroundTickets;

    Player(int taxiTickets, int busTickets, int undergroundTickets, Type type) {
        this.taxiTickets = taxiTickets;
        this.busTickets = busTickets;
        this.undergroundTickets = undergroundTickets;
        this.TYPE = type;
    }

    public boolean isDetective() {
        return TYPE.equals(Type.DETECTIVE);   //спросить что лучше использовать '==' или 'equals'
    }

    public boolean isMisterX() {
        return TYPE.equals(Type.MISTER_X);
    }

    public boolean hasTaxiTickets() {
        return taxiTickets > 0;
    }

    public boolean hasBusTickets() {
        return busTickets > 0;
    }

    public boolean hasUndergroundTickets() {
        return undergroundTickets > 0;
    }

    public void removeTicket(Transport transport) {
        switch (transport) {
            case BUS:
                busTickets--;
                break;
            case TAXI:
                taxiTickets--;
                break;
            case UNDERGROUND:
                undergroundTickets--;
                break;
        }
    }

    public int getTaxiTickets() {
        return taxiTickets;
    }

    public void setTaxiTickets(int taxiTickets) {
        this.taxiTickets = taxiTickets;
    }

    public int getBusTickets() {
        return busTickets;
    }

    public void setBusTickets(int busTickets) {
        this.busTickets = busTickets;
    }

    public int getUndergroundTickets() {
        return undergroundTickets;
    }

    public void setUndergroundTickets(int undergroundTickets) {
        this.undergroundTickets = undergroundTickets;
    }

    @Override
    public String toString() {
        return "Player{" + TYPE + '}';
    }

    public enum Type {
        MISTER_X, DETECTIVE
    }
}