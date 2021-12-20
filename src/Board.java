
/**
 * Represents the game board. (2D array)
 * API ->
 * putMark(mark,row,col)
 * getMark(row,col)
 * gameEnded()
 * getWinner()
 */
public class Board {
    /* Constants */
    public static int SIZE = 6;
    public static int WIN_STREAK = 4;

    /* Variable Declarations*/
    private final Mark[][] board;
    private Mark winner;
    private boolean isGameOver;
    private int BlankCells;


    /**
     * Constructor
     */
    public Board() {
        this.board = new Mark[SIZE][SIZE];
        this.BlankCells = SIZE * SIZE;
        this.isGameOver = false;
        this.winner = null;
        initBoard();
    }

    /**
     * this Method simply put Mark in given (row, col) coordinate.
     * In addition this method checks game status and updates who is is the winner.
     * There is validation check for invalid input.
     *
     * @param mark - (X, O, BLANK)
     * @param row  - ( 0<= row < SIZE)
     * @param col  - ( 0<= col < SIZE)
     * @return true for succsess. false otherwise.
     */
    public boolean putMark(Mark mark, int row, int col) {
        if ((coordinatesValidation(row, col)) || (isCellMarked(row, col))) {
            return false;
        }
        this.board[row][col] = mark;
        checkForWin(row, col, mark);
        checkForDraw();
        return true;
    }

    /**
     * this method simply return the Mark (X, O, BLANK) in the (row, col) coordinate in the board
     * There is validation check for invalid input.
     *
     * @param row ( 0<= row < SIZE)
     * @param col ( 0<= col < SIZE)
     * @return Mark Object
     */
    public Mark getMark(int row, int col) {
        if (coordinatesValidation(row, col)) {
            return Mark.BLANK;
        }
        return this.board[row][col];
    }

    /**
     * this method return true if the game ended. False otherwise.
     */
    public boolean gameEnded() {
        return this.isGameOver;
    }

    /**
     * this method returns the winner.
     */
    public Mark getWinner() {
        return this.winner;
    }


    /*
     * this method checks if cell (row, col) already marked with X or O
     * @return true if already marked, false otherwise
     */
    private boolean isCellMarked(int row, int col) {
        return this.board[row][col] != Mark.BLANK;

    }

    /*
     * this function initialize the board.
     * Puts BLANK in every cell.
     */
    private void initBoard() {
        for (int i = 0; i < this.board.length; i++) {
            for (int j = 0; j < this.board.length; j++) {
                this.board[i][j] = Mark.BLANK;
            }
        }
    }

    /*
     * this method checks if there is no more available cells in the board.
     * in that case updates that there's a tie and the game is over.
     */
    private void checkForDraw() {
        if (--this.BlankCells == 0) {
            this.winner = Mark.BLANK;
            this.isGameOver = true;
        }
    }

    /*
     * this method check for win by counting marks recursively.
     */
    private void checkForWin(int row, int col, Mark mark) {
        if (countMarksRec(row, col, mark)) {
            // this.isGameOver = true;
            if (mark == Mark.X) {
                this.winner = Mark.X;
            } else {
                this.winner = Mark.O;
            }
            this.isGameOver = true;
        }
    }

    /*
     * this method get (row,col) and direction (rowDelta,colDelta)
     * and return the sum of the given direction in both sides.
     * @return
     */
    private int sumDirection(int row, int col, int rowDelta, int colDelta, Mark mark) {
        return (countMarkInDirectionRec(row, col, rowDelta, colDelta, mark) +
                countMarkInDirectionRec(row, col, -rowDelta, -colDelta, mark));
    }

    /*
     * this method returns true if either one of the directions (col,row,diagonal) equals to WIN_STREAK and False otherwise.
     */
    private boolean countMarksRec(int row, int col, Mark mark) {

        return (((sumDirection(row, col, 1, 0, mark) - 1 == WIN_STREAK) ||
                ((sumDirection(row, col, 0, 1, mark) - 1 == WIN_STREAK)) ||
                ((sumDirection(row, col, 1, 1, mark) - 1 == WIN_STREAK)) ||
                ((sumDirection(row, col, -1, 1, mark) - 1 == WIN_STREAK))));
    }

    /*
     * this method returns streak length in specific direction. Recursively.
     */
    private int countMarkInDirectionRec(int row, int col, int rowDelta, int colDelta, Mark mark) {
        if (coordinatesValidation(row, col) || (this.board[row][col] != mark)) {
            return 0;
        }
        return 1 + countMarkInDirectionRec(row + rowDelta, col + colDelta, rowDelta, colDelta, mark);
    }

    /*
     * this function validates coordinates.
     * @return true if 0 <= row,col < SIZE. false otherwise
     */
    private boolean coordinatesValidation(int row, int col) {
        return (row < 0) || (row >= SIZE) || (col < 0) || (col >= SIZE);
    }

}
