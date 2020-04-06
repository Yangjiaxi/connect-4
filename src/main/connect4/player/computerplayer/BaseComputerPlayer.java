package connect4.player.computerplayer;

import connect4.board.Board;
import connect4.player.BasePlayer;

public abstract class BaseComputerPlayer extends BasePlayer {
    public abstract int askNext(Board board);
}
