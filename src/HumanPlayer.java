import java.util.Scanner;

/**
 * this class implements human player for Tic-Tac-Toe game.
 * API -> constructor and playTurn method.
 */

public class HumanPlayer implements Player {

    private static final String INPUT_MSG = "Player %s type coordinates: ";
    private static final String INVALID_INPUT_MSG = "Invalid coordinates, type again: ";

    /* Class Variables*/
    private static final Scanner scanner = new Scanner(System.in);

    /* Class public Methods -API */

    /**
     * this method responsible for getting input from the user and handle his turn.
     */
    @Override
    public void playTurn(Board board, Mark mark) {
        System.out.printf(INPUT_MSG, mark);
        int in = scanner.nextInt();
        while (!board.putMark(mark, (in / 10) - 1, (in % 10) - 1)) {
            System.out.println(INVALID_INPUT_MSG);
            in = scanner.nextInt();
        }
    }

}
