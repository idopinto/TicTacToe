/**
 * player implantation thשt is smarter than Ms. clever
 */

public class SnartypamtsPlayer implements Player {

    @Override
    public void playTurn(Board board, Mark mark) {

        if(tryMarkFromXtoY(board, mark, Board.SIZE / 2, Board.SIZE)) {return;} // try to mark from the middle column to the end
        tryMarkFromXtoY(board, mark, 0, Board.SIZE / 2);  // try to mark from the 0 column to the middle.
    }

    /*
        helper method.
     */
    private boolean tryMarkFromXtoY(Board board, Mark mark, int x, int y) {
        for (int i = x; i < y; i++) {
            for (int j = 0; j < Board.SIZE; j++) {
                if (board.putMark(mark, j, i)) {
                    return true;
                }
            }
        }
        return false;
    }
}