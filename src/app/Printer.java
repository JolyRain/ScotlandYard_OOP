package app;

import game.ScotlandYardGame;
import game.Ticket;
import game.TypePlayer;
import graph.Vertex;
import players.MisterX;
import players.Player;

import java.util.Map;
import java.util.Set;

public class Printer implements Printable {

    public void printGameState(ScotlandYardGame game) {
        printLongSeparator();
        print(game.getState().toString(), ANSI_GREEN);
        printLongSeparator();
    }

    private void printPlayer(Player player) {
        String playerString = player.toString().concat(player.ticketsToString());
        if (player.getTYPE().equals(TypePlayer.DETECTIVE)) {
            print(playerString, ANSI_BLUE);
        }
        if (player.getTYPE().equals(TypePlayer.MISTER_X)) {
            print(playerString, ANSI_RED);
        }
    }


    public void printPlayers(ScotlandYardGame game) {
        print("PLAYERS", ANSI_CYAN);
        MisterX misterX = game.getMisterX();
        Set<Player> detectives = game.getDetectives();
        printPlayer(misterX);
        for (Player detective : detectives) {
            printPlayer(detective);
        }
        printShortSeparator();
    }

    public void printMove(Player player, Vertex targetStation, Ticket ticket) {
        print("MOVE", ANSI_CYAN);
        print(player.toString() + " ---" + ticket.getType() + "---> " + targetStation, ANSI_RESET);
        printShortSeparator();
    }

    public void printLocations(ScotlandYardGame game) {
        print("CURRENT LOCATIONS", ANSI_CYAN);
        Map<Player, Vertex> locationsMap = game.getPlayerVertexMap();
        for (Map.Entry<Player, Vertex> location : locationsMap.entrySet()) {
            Player player = location.getKey();
            Vertex station = location.getValue();
            print(player.toString() + " <---> " + station, ANSI_RESET);
        }
        printShortSeparator();
    }

    private void printLongSeparator() {
        print("-----------------------------------------------------------------------------" +
                "---------------------------------------------------------", ANSI_BLACK);
    }

    private void printShortSeparator() {
        print("----------------------------------", ANSI_WHITE);
    }

}
