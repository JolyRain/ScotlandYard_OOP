package services.gameServices;

import app.Printer;
import game.ScotlandYardGame;
import game.Ticket;
import game.TypePlayer;
import game.TypeTicket;
import graph.Edge;
import graph.Graph;
import graph.TypeRoad;
import graph.Vertex;
import players.Detective;
import players.MisterX;
import players.Player;
import services.graphServices.GraphService;
import services.playerOnGameServices.DetectiveOnGameService;
import services.playerOnGameServices.MisterXOnGameService;
import services.playerServices.DetectiveService;
import services.playerServices.MisterXService;

import java.util.*;

import static game.GameState.*;

public class GameService {

    private MisterXService misterXService = new MisterXService();
    private DetectiveService detectiveService = new DetectiveService();
    private MisterXOnGameService misterXOnGameService = new MisterXOnGameService();
    private DetectiveOnGameService detectiveOnGameService = new DetectiveOnGameService();
    private GraphService graphService = new GraphService();
    private Printer printer = new Printer();

    public void initGame(ScotlandYardGame game) {
        initGraph(game);
        game.setMisterX(new MisterX());             //добавить граф
        initDetectives(game);
        setLocationMaps(game);
        setStartStations(game);
        ticketsDistribution(game);
        game.setStations(new LinkedList<>(game.getGraph().getVertices()));
        game.setState(PLAYING);
    }

    private void initGraph(ScotlandYardGame game) {
        Graph graph = new Graph();

        for (int i = 0; i < 11; i++) {
            graphService.addVertex(graph, new Vertex());
        }

        for (int i = 0; i < 11; i++) {
            Vertex current = graphService.getVertex(graph, String.valueOf(i));
            Vertex next = graphService.getVertex(graph, String.valueOf(i != 10 ? i + 1 : 0));
            Edge edge1 = new Edge(current, next, TypeRoad.METRO);
            Edge edge2 = new Edge(current, next, TypeRoad.BUS);
            Edge edge3 = new Edge(current, next, TypeRoad.TAXI);
            graphService.addEdge(graph, edge1);
            graphService.addEdge(graph, edge2);
            graphService.addEdge(graph, edge3);

        }
        game.setGraph(graph);
        game.setStations(new LinkedList<>(graph.getVertices()));
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
                game.setState(DETECTIVES_VICTORY);
                return;
            } else game.setState(PLAYING);
        }
    }

    public void play(ScotlandYardGame game) {
//        MisterX misterX = game.getMisterX();
//        Set<Player> detectives = game.getDetectives();

        for (int i = 0; i < ScotlandYardGame.MOVE_AMOUNT; i++) {
            printer.printGameState(game);
            printer.printPlayers(game);
            printer.printLocations(game);
            misterXMove(game);
            detectivesMove(game);
            setState(game);
            if (game.getState().equals(DETECTIVES_VICTORY)) {
                printer.printGameState(game);
                return;
            }
        }
        if (game.getState().equals(PLAYING)) {
            game.setState(MISTER_X_VICTORY);
            printer.printGameState(game);
        }
    }

    private void detectivesMove(ScotlandYardGame game) {
        Set<Player> detectives = game.getDetectives();
        for (Player detective : detectives) {
            if (!detectiveService.hasTickets(detective)) continue;
            Ticket currentTicket = detectiveOnGameService.getCurrentTicket(detective);
            Vertex targetStation = detectiveOnGameService.setTargetStation(detective, game, currentTicket);
            if (targetStation == null) continue; //???
            detectiveService.moveTo(detective, game, targetStation, currentTicket);
        }
    }

    private void misterXMove(ScotlandYardGame game) {
        MisterX misterX = game.getMisterX();
        if (!misterXService.hasTickets(misterX)) return;
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

    private void setLocationMaps(ScotlandYardGame game) {
        game.setPlayerVertexMap(new HashMap<>());
        game.setVertexPlayerMap(new HashMap<>());
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

    private void giveTickets(List<Ticket> tickets, Map<TypeTicket, Integer> ticketsMap, TypeTicket typeTicket, Integer ticketsAmount) {
        for (int i = 0; i < ticketsAmount; i++) {
            tickets.add(new Ticket(typeTicket));
            ticketsMap.put(typeTicket, ticketsMap.get(typeTicket) == null ? 1 : ticketsMap.get(typeTicket) + 1);
        }

    }

    private void giveTicketsToMisterX(MisterX misterX, ScotlandYardGame game) {
        List<Ticket> misterXTickets = misterX.getTickets();
        Map<TypeTicket, Integer> ticketsMap = misterX.getTicketsMap();
        Set<Player> detectives = game.getDetectives();
        giveTickets(misterXTickets, ticketsMap, TypeTicket.TAXI, MisterX.AMOUNT_TAXI_TICKETS);
        giveTickets(misterXTickets, ticketsMap, TypeTicket.BUS, MisterX.AMOUNT_BUS_TICKETS);
        giveTickets(misterXTickets, ticketsMap, TypeTicket.METRO, MisterX.AMOUNT_METRO_TICKETS);     //нужно улучшить
        giveTickets(misterXTickets, ticketsMap, TypeTicket.DOUBLE, MisterX.AMOUNT_DOUBLE_TICKETS);
        giveTickets(misterXTickets, ticketsMap, TypeTicket.BLACK, detectives.size());
        misterXOnGameService.shuffleTickets(misterX);
    }

    private void giveTicketsToDetective(Detective detective) {
        List<Ticket> detectiveTickets = detective.getTickets();
        Map<TypeTicket, Integer> ticketsMap = detective.getTicketsMap();
        giveTickets(detectiveTickets, ticketsMap, TypeTicket.BUS, Detective.AMOUNT_BUS_TICKETS);
        giveTickets(detectiveTickets, ticketsMap, TypeTicket.TAXI, Detective.AMOUNT_TAXI_TICKETS);
        giveTickets(detectiveTickets, ticketsMap, TypeTicket.METRO, Detective.AMOUNT_METRO_TICKETS);
        detectiveOnGameService.shuffleTickets(detective);

    }

}
