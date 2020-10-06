package players;

import game.WayBill;

import java.util.HashMap;

public class MisterX extends Player {

    private WayBill wayBill;

    MisterX() {
        super(Type.MISTER_X);
        wayBill = new WayBill(new HashMap<>());
    }

    public WayBill getWayBill() {
        return wayBill;
    }

    public void setWayBill(WayBill wayBill) {
        this.wayBill = wayBill;
    }
}
