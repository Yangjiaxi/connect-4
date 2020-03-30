package connect4.player.computerplayer;

import connect4.board.Board;
import connect4.player.Player;

public abstract class ComputerPlayer extends Player {
    public abstract int askNext(Board board);
}
