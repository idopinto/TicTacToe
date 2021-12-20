/**
 * Represents a single game instance of Tic-Tac-Toe.
 * API ->  constructor and run() method.
 */
public class Game {
    /* Instance variable */
    private final Renderer renderer;
    private final Player[] players;

    /**
     * Constructor
     *
     * @param playerX  - Player #1
     * @param playerO  - Player #2
     * @param renderer - the game renderer
     */
    public Game(Player playerX, Player playerO, Renderer renderer) {
        this.renderer = renderer;
        this.players = new Player[2];
        this.players[0] = playerX;
        this.players[1] = playerO;
    }

    /**
     * this method handles the game-loop of the Tic-Tac-Toe game.
     *
     * @return Mark enum value which represents the winner.
     */
    public Mark run() {
        // init new board
        Board board = new Board();
        Mark[] marks = {Mark.X, Mark.O};

        // game-loop
        renderer.renderBoard(board);
        for (int i = 0; ; i++) {
            this.players[i % 2].playTurn(board, marks[i % 2]);
            renderer.renderBoard(board);
            if (board.gameEnded()) {
                break;
            }
        }
        return board.getWinner();
    }

}
