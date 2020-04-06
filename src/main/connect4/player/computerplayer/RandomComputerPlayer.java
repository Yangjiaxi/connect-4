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
import static connect4.Options.COMPUTE_SLEEP_TIME_MAX_MS;
import static connect4.Utils.randomInt;

public class RandomComputerPlayer extends BaseComputerPlayer {

    private int innerGetNext(Board board) {
        ArrayList<Integer> columns = new ArrayList<>();
        for (int j = 0; j < board.getCols(); ++j) {
            if (board.canPlace(j)) {
                columns.add(j);
            }
        }
        return columns.get(new Random().nextInt(columns.size()));
    }

    @Override
    public void askNext(Board board) {
        Thread thread = new Thread(() -> {
            try {
                Thread.sleep(randomInt(COMPUTE_SLEEP_TIME_MAX_MS));
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            agent.reportInput(innerGetNext(board));
        });
        thread.start();
    }
}
