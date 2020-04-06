package connect4.player.computerplayer;

import connect4.agent.Agent;
import connect4.board.Board;
import connect4.player.BasePlayer;

/**
 * @author yang
 */
public abstract class BaseComputerPlayer extends BasePlayer {
    /**
     * askNext: give the current board, ask for player's next move
     *
     * @param board current board
     * @return column at which the player want to place the disc
     */
    public abstract void askNext(Board board);

    protected Agent agent;

    public void setAgent(Agent agent) {
        this.agent = agent;
    }
}
