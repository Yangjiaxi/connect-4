package connect4.player.computerplayer;

import connect4.board.Board;

import java.util.ArrayList;
import java.util.Random;

/**
 * Random computer player
 *
 * generate next move by `Random.nextInt`
 *
 * @author tian
 */
public class RandomComputerPlayer extends BaseComputerPlayer {

    @Override
    public int askNext(Board board) {
        ArrayList<Integer> columns = new ArrayList<>();
        for (int j = 0; j < board.getCols(); ++j) {
            if (board.canPlace(j)) {
                columns.add(j);
            }
        }
        return columns.get(new Random().nextInt(columns.size()));
    }
}
