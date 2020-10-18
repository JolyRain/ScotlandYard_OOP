package players;

import game.TypePlayer;
import game.WayBill;

import java.util.HashMap;

public class MisterX extends Player {

    public static final Integer AMOUNT_TAXI_TICKETS = 4;
    public static final Integer AMOUNT_BUS_TICKETS = 3;
    public static final Integer AMOUNT_METRO_TICKETS = 3;
    public static final Integer AMOUNT_DOUBLE_TICKETS = 2;

    private WayBill wayBill;

    public MisterX() {
        super(TypePlayer.MISTER_X);
        wayBill = new WayBill(new HashMap<>());
    }

    public WayBill getWayBill() {
        return wayBill;
    }

    public void setWayBill(WayBill wayBill) {
        this.wayBill = wayBill;
    }
}
