package services.gameServices;

import game.*;
import graph.Vertex;
import players.Detective;
import players.MisterX;
import players.Player;
import services.playerOnGameServices.DetectiveOnGameService;
import services.playerOnGameServices.MisterXOnGameService;
import services.playerServices.DetectiveService;
import services.playerServices.MisterXService;

import java.util.*;

public class GameService {

    private MisterXService misterXService = new MisterXService();
    private DetectiveService detectiveService = new DetectiveService();
    private MisterXOnGameService misterXOnGameService = new MisterXOnGameService();
    private DetectiveOnGameService detectiveOnGameService = new DetectiveOnGameService();

    public void initGame(ScotlandYardGame game) {
        game.setMisterX(new MisterX());             //добавить граф
        initDetectives(game);
        setStartStations(game);
        ticketsDistribution(game);
        game.setStations(new LinkedList<>(game.getGraph().getVertices()));
        game.setGameState(GameState.PLAYING);
    }

    private void initDetectives(ScotlandYardGame game) {
        Set<Player> detectives = new HashSet<>();
        for (int i = 0; i < ScotlandYardGame.DETECTIVES_AMOUNT; i++) {
            detectives.add(new Detective(String.valueOf(i)));
        }
        game.setDetectives(detectives);
    }

    public void clear(ScotlandYardGame game) {
        game.setGraph(null);
        game.setDetectives(null);
        game.setMisterX(null);
        game.setPlayerVertexMap(null);
        game.setStations(null);
        game.setVertexPlayerMap(null);
    }

    private void setState(ScotlandYardGame game) {
        Map<Player, Vertex> playerVertexMap = game.getPlayerVertexMap();
        Vertex misterXStation = playerVertexMap.get(game.getMisterX());
        for (Map.Entry<Player, Vertex> playerVertexEntry : playerVertexMap.entrySet()) {
            Player currentPlayer = playerVertexEntry.getKey();
            Vertex currentVertex = playerVertexEntry.getValue();
            if (currentVertex.equals(misterXStation) && currentPlayer.getTYPE().equals(TypePlayer.DETECTIVE)) {
                game.setGameState(GameState.DETECTIVES_VICTORY);
            } else game.setGameState(GameState.PLAYING);
        }
    }

    public void play(ScotlandYardGame game) {
//        MisterX misterX = game.getMisterX();
//        Set<Player> detectives = game.getDetectives();

        for (int i = 0; i < ScotlandYardGame.MOVE_AMOUNT; i++) {
            misterXMove(game);
            detectivesMove(game);
            setState(game);
            if (game.getGameState().equals(GameState.DETECTIVES_VICTORY)) return;
        }

        if (game.getGameState().equals(GameState.PLAYING)) {
            game.setGameState(GameState.DETECTIVES_DEFEAT);
        }
    }

    private void detectivesMove(ScotlandYardGame game) {
        Set<Player> detectives = game.getDetectives();
        for (Player detective : detectives) {
            Ticket currentTicket = detectiveOnGameService.getCurrentTicket(detective);
            Vertex targetStation = detectiveOnGameService.setTargetStation(detective, game, currentTicket);
            detectiveService.moveTo(detective, game, targetStation, currentTicket);
        }
    }

    private void misterXMove(ScotlandYardGame game) {
        MisterX misterX = game.getMisterX();
        Ticket currentTicket = misterXOnGameService.getCurrentTicket(misterX);
        Vertex targetStation = misterXOnGameService.setTargetStation(misterX, game, currentTicket);
        misterXService.moveTo(misterX, game, targetStation, currentTicket);
    }

    public void setStartStations(ScotlandYardGame game) {
        int board = game.getStations().size();
        List<Vertex> stations = game.getStations();
        Collections.shuffle(stations);
        Set<Player> detectives = game.getDetectives();
        MisterX misterX = game.getMisterX();
        Vertex misterXStartStation = stations.get((int) (Math.random() * board));
        setStartStation(misterX, misterXStartStation, game);
        board -= 1;
        stations.removeIf(station -> station.equals(misterXStartStation));
        for (Player detective : detectives) {
            Vertex startStation = stations.get((int) (Math.random() * board));
            setStartStation(detective, startStation, game);
            board -= 1;
            stations.removeIf(station -> station.equals(startStation));
        }
    }

    private void setStartStation(Player player, Vertex startStation, ScotlandYardGame game) {
        Map<Player, Vertex> playerVertexMap = game.getPlayerVertexMap();
        Map<Vertex, Player> vertexPlayerMap = game.getVertexPlayerMap();
        playerVertexMap.put(player, startStation);
        vertexPlayerMap.put(startStation, player);
    }

    //оставить здесь
    public boolean isFreeStation(Vertex station, ScotlandYardGame game) {
        return game.getVertexPlayerMap().get(station) == null;
    }

    public void ticketsDistribution(ScotlandYardGame game) {
        MisterX misterX = game.getMisterX();
        Set<Player> detectives = game.getDetectives();
        giveTicketsToMisterX(misterX, game);
        for (Player player : detectives) {
            giveTicketsToDetective((Detective) player);
        }
    }

    private void giveTickets(List<Ticket> tickets, TypeTicket typeTicket, Integer ticketsAmount) {
        for (int i = 0; i < ticketsAmount; i++) {
            tickets.add(new Ticket(typeTicket));
        }
    }

    private void giveTicketsToMisterX(MisterX misterX, ScotlandYardGame game) {
        List<Ticket> misterXTickets = misterX.getTickets();
        Set<Player> detectives = game.getDetectives();
        giveTickets(misterXTickets, TypeTicket.TAXI, MisterX.AMOUNT_TAXI_TICKETS);
        giveTickets(misterXTickets, TypeTicket.BUS, MisterX.AMOUNT_BUS_TICKETS);
        giveTickets(misterXTickets, TypeTicket.METRO, MisterX.AMOUNT_METRO_TICKETS);     //нужно улучшить
        giveTickets(misterXTickets, TypeTicket.DOUBLE, MisterX.AMOUNT_DOUBLE_TICKETS);
        giveTickets(misterXTickets, TypeTicket.BLACK, detectives.size());
    }

    private void giveTicketsToDetective(Detective detective) {
        List<Ticket> detectiveTickets = detective.getTickets();
        giveTickets(detectiveTickets, TypeTicket.BUS, Detective.AMOUNT_BUS_TICKETS);
        giveTickets(detectiveTickets, TypeTicket.TAXI, Detective.AMOUNT_BUS_TICKETS);
        giveTickets(detectiveTickets, TypeTicket.METRO, Detective.AMOUNT_METRO_TICKETS);
    }

}
