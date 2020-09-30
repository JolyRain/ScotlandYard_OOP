package players;

public class MisterX extends Player {

    private static final int TAXI_TICKETS = 4;
    private static final int BUS_TICKETS = 3;
    private static final int UNDERGROUND_TICKETS = 3;

    private int doubleMoveCards = 2;
    private int blackCards = 5;


    public MisterX() {
        super(TAXI_TICKETS, BUS_TICKETS, UNDERGROUND_TICKETS, Type.MISTER_X);
    }

    public void removeDoubleMoveCard() {
        doubleMoveCards--;
    }

    public void removeBlackCard() {
        blackCards--;
    }

    public boolean hasDoubleMoveCards() {
        return doubleMoveCards > 0;
    }

    public boolean hasBlackCard() {
        return blackCards > 0;
    }
}
