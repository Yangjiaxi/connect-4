package connect4.player.computerplayer;

import connect4.Utils;
import connect4.board.Board;

import java.util.ArrayList;

import static connect4.Options.*;
import static connect4.Utils.randomInt;


/**
 * Random computer player
 * <p>
 * generate next move by `Random.nextInt`
 *
 * @author tian
 */
public class RandomComputerPlayer extends BaseComputerPlayer {

    public int innerGetNext(Board board) {
        ArrayList<Integer> columns = new ArrayList<>();
        for (int j = 0; j < board.getCols(); ++j) {
            if (board.canPlace(j)) {
                columns.add(j);
            }
        }
        return columns.get(randomInt(columns.size()));
    }

    @Override
    public void askNext(Board board) {
        Utils.THREAD_SERVICE.execute(() -> {
            try {
                Thread.sleep(COMPUTE_SLEEP_TIME_MIN_MS + randomInt(COMPUTE_SLEEP_TIME_RANDOM_MS));
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            agent.reportComputerInput(innerGetNext(board));
        });
    }
}
