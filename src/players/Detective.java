package players;

public class Detective extends Player {

    private static final int TAXI_TICKETS = 10;
    private static final int BUS_TICKETS = 8;
    private static final int UNDERGROUND_TICKETS = 4;

    Detective() {
        super(TAXI_TICKETS, BUS_TICKETS, UNDERGROUND_TICKETS, Type.DETECTIVE);
    }

}
