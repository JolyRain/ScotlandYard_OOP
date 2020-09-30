package players;

public abstract class Player {
    private int taxiTickets;
    private int busTickets;
    private int undergroundTickets;

    Player(int taxiTickets, int busTickets, int undergroundTickets) {
        this.taxiTickets = taxiTickets;
        this.busTickets = busTickets;
        this.undergroundTickets = undergroundTickets;
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
}