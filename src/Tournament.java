/**
 * Represents Tournament between two players.
 * API - constructor and playTournament() method
 */

public class Tournament {

    /* Constants */
    private static final int TABLE_SIZE = 3;
    private static final int ARG_NUM = 4;
    private static final int ARG_ROUNDS = 0;
    private static final int ARG_RENDERER = 1;
    private static final int ARG_PLAYER1 = 2;
    private static final int ARG_PLAYER2 = 3;
    private static final String TABLE_STRING = "=== player 1: %d | player 2: %d | Draws: %d ===\r";
    private static final String USAGE_MESSAGE = "Usage: java Tournament [round count > 0]" +
            " [render target: console/none]" +
            " [player: human/clever/whatever/snartypamts] X2";
    /* Variable Declarations*/
    private final int rounds;
    private final int[] pointsTable;
    private final Player[] players;
    private final Renderer renderer;

    /**
     * Creates new tournament.
     *
     * @param rounds   -> number of rounds to play.
     * @param renderer -> renders the game.
     * @param players  -> array of Player of size 2.
     */
    Tournament(int rounds, Renderer renderer, Player[] players) {
        this.rounds = rounds;
        this.renderer = renderer;
        this.players = players;
        this.pointsTable = new int[TABLE_SIZE];
    }


    /**
     * this method handles one tournament.
     */
    public void playTournament() {
        for (int i = 0; i < this.rounds; i++) {
            Mark winner = runSingleGame(i);
            updatePointsTable(winner, i);
        }
        printPointsTable();
    }

    /*
        this method runs single game and returns the winner.
     */
    private Mark runSingleGame(int playerIndex) {
        Game game = new Game(this.players[playerIndex % 2], this.players[(1 + playerIndex) % 2], this.renderer);
        return game.run();
    }

    /*
        this method updates the points table according to the winner Mark and given playerIndex
     */
    private void updatePointsTable(Mark winner, int playerIndex) {
        if (winner == Mark.X) {
            ++this.pointsTable[playerIndex % 2];
        } else if (winner == Mark.O) {
            ++this.pointsTable[(1 + playerIndex) % 2];
        } else if (winner == Mark.BLANK) {
            ++this.pointsTable[2];
        }
    }

    /*
        this method prints the points table nicely.
     */
    private void printPointsTable() {
        System.out.printf(TABLE_STRING, this.pointsTable[0], this.pointsTable[1], this.pointsTable[2]);
    }

    public static void main(String[] args) {

        /* # of arguments validation */
        if (args.length != ARG_NUM) {
            System.err.println(USAGE_MESSAGE);
            return;
        }

        /* Factory initialization */
        PlayerFactory playerFactory = new PlayerFactory();
        RendererFactory rendererFactory = new RendererFactory();

        /* parsing args array */
        int rounds = Integer.parseInt(args[ARG_ROUNDS]);
        Renderer renderer = rendererFactory.buildRenderer(args[ARG_RENDERER]);
        Player[] players = {playerFactory.buildPlayer(args[ARG_PLAYER1]), playerFactory.buildPlayer(args[ARG_PLAYER2])};

        /* Arguments validation */
        if ((rounds <= 0) || (renderer == null) || (players[0] == null) || (players[1] == null)) {
            System.err.println(USAGE_MESSAGE);
            return;
        }

        /* Running new tournament*/
        Tournament tournament = new Tournament(rounds, renderer, players);
        tournament.playTournament();

    }
}
