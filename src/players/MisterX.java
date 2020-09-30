package players;

public class MisterX extends Player {

    private static final int TAXI_TICKETS = 4;
    private static final int BUS_TICKETS = 3;
    private static final int UNDERGROUND_TICKETS = 3;

    private int doubleTickets = 2;
    private int blackCards;


    public MisterX(int blackCards) {
        super(TAXI_TICKETS, BUS_TICKETS, UNDERGROUND_TICKETS);
        this.blackCards = blackCards;
    }

}
