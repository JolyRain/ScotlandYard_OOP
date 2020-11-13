import game.ScotlandYardGame;
import services.gameServices.GameService;

public class Main {
    public static void main(String[] args) {

        GameService gameService = new GameService();
        ScotlandYardGame game = new ScotlandYardGame();
        gameService.initGame(game);
        gameService.play(game);
    }
}
