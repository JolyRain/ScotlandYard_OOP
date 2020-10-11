package players;

import game.TypePlayer;

public class Detective extends Player {

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
