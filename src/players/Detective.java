package players;

import game.TypePlayer;

public class Detective extends Player {

    public static final Integer AMOUNT_TAXI_TICKETS = 10;
    public static final Integer AMOUNT_BUS_TICKETS = 8;
    public static final Integer AMOUNT_METRO_TICKETS = 4;

    private String name;

    public Detective(String name) {
        super(TypePlayer.DETECTIVE);
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
