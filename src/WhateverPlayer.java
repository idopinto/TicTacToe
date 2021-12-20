import java.util.Random;

/**
 * Bot - player implementation
 */
public class WhateverPlayer implements Player {

    @Override
    public void playTurn(Board board, Mark mark) {
        Random random = new Random();
        int coord_x = random.nextInt(Board.SIZE);
        int coord_y = random.nextInt(Board.SIZE);
        while (!board.putMark(mark, coord_x, coord_y)) {
            coord_x = random.nextInt(Board.SIZE);
            coord_y = random.nextInt(Board.SIZE);
        }
        board.putMark(mark, coord_x, coord_y);
    }

}
