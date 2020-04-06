package connect4.agent;

import connect4.Options;
import connect4.board.Board;
import connect4.board.Grid;
import connect4.board.GridType;
import connect4.board.Position;
import connect4.player.BasePlayer;
import connect4.player.HumanPlayer;
import connect4.player.computerplayer.BaseComputerPlayer;
import connect4.render.view.GameView;

import java.util.ArrayList;

/**
 * Driver of logic
 * <p>
 * Agent contains a FSM(finite state machine)
 * states:
 *
 * @author yang
 * @see AgentState
 */
public class Agent {
    private Board board;

    private BasePlayer playerA;
    private BasePlayer playerB;
    private GridType nextColor;

    private final int goal;

    private AgentState state;
    private GameView view;

    public Agent(BasePlayer pa, BasePlayer pb) {
        goal = Options.GOAL_TO_WIN;
        playerA = pa;
        playerB = pb;
    }

    public Board getBoard() {
        return board;
    }

    public Grid getGrid(int row, int col) {
        return board.getGrid(row, col);
    }

    public GridType getActivePlayer() {
        return nextColor;
    }

    public AgentState getState() {
        return state;
    }

    private void installAgent(BasePlayer player) {
        if (player instanceof BaseComputerPlayer) {
            ((BaseComputerPlayer) player).setAgent(this);
        }
    }

    public void initBoard() {
        board = new Board(Options.BOARD_ROWS, Options.BOARD_COLUMNS);
    }

    public void initState() {
        state = AgentState.READY;
        nextColor = GridType.PLAYER_A;
    }

    public void initPlayer() {
        installAgent(playerA);
        installAgent(playerB);
    }

    public void start() {
        initState();
        initBoard();
        initPlayer();
        stepInto();
    }

    public void stop() {
        playerA = null;
        playerB = null;
        state = AgentState.READY;
    }

    public void connect(GameView gameView) {
        view = gameView;
    }

    private void notifyViewComponent() {
        if (view == null) {
            return;
        }
        view.updateComponents();
    }

    private boolean validPosition(int row, int col) {
        return row >= 0 && row < board.getRows()
                && col >= 0 && col < board.getCols();
    }

    /**
     * check whether the last move forms a winning state in certain direction
     * <p>
     * direction is specified by dRow and dCol
     * <p>
     * (dRow, dCol):
     * - Horizontal : (0, 1)
     * - Vertical   : (1, 0)
     * - 1st Diag   : (1, 1)
     * - 2nd Diag   : (-1 ,1)
     * <p>
     * if goal is reached, discs will be marked as `inWinTrace`
     *
     * @param move Position of last move
     * @param dRow {0, -1, 1}
     * @param dCol {0, -1, 1}
     * @param type who makes the last move
     * @return is reached the goal?
     * @author luo
     */
    public boolean checkOneDir(Position move, int dRow, int dCol, GridType type) {
        int count = 0;
        ArrayList<Position> tmpTrace = new ArrayList<>();
        int tmpRow = move.getRow();
        int tmpCol = move.getCol();

        // increment direction
        while (validPosition(tmpRow, tmpCol)) {
            if (!getGrid(tmpRow, tmpCol).getType().equals(type)) {
                break;
            }
            tmpTrace.add(new Position(tmpRow, tmpCol));
            tmpRow += dRow;
            tmpCol += dCol;
            ++count;
        }

        // versa
        tmpRow = move.getRow() - dRow;
        tmpCol = move.getCol() - dCol;

        while (validPosition(tmpRow, tmpCol)) {
            if (!getGrid(tmpRow, tmpCol).getType().equals(type)) {
                break;
            }
            tmpTrace.add(new Position(tmpRow, tmpCol));
            tmpRow -= dRow;
            tmpCol -= dCol;
            ++count;
        }

        if (count < goal) {
            tmpTrace.clear();
            return false;
        }
        for (Position pos : tmpTrace) {
            board.getGrid(pos).setInTrace(true);
        }
        return true;

    }

    /**
     * check the board:
     * 1. is the last move forms a winning state
     * 2. if so, switch the FSM to `WINNING`
     * 3. if not, switch the FSM for next player
     *
     * @param gridType player's type of last move
     * @param lastMove position of last move
     * @author luo
     */
    public void checkBoard(GridType gridType, Position lastMove) {
        boolean horizontal = checkOneDir(lastMove, 0, 1, gridType);
        boolean vertical = checkOneDir(lastMove, 1, 0, gridType);
        boolean diag1st = checkOneDir(lastMove, 1, 1, gridType);
        boolean diag2nd = checkOneDir(lastMove, -1, 1, gridType);

        // WIN
        if (horizontal || vertical || diag1st || diag2nd) {
            state = AgentState.WIN;
        } else {
            nextColor = (nextColor == GridType.PLAYER_A) ? GridType.PLAYER_B : GridType.PLAYER_A;
            state = AgentState.READY;
        }
    }

    private BasePlayer getCurrentPlayer() {
        return nextColor == GridType.PLAYER_A ? playerA : playerB;
    }

    private boolean isCurrentHuman() {
        return getCurrentPlayer() instanceof HumanPlayer;
    }

    private boolean isCurrentComputer() {
        return getCurrentPlayer() instanceof BaseComputerPlayer;
    }

    public void reportComputerInput(int column) {
        boolean needComputer = state == AgentState.WAITING_COMPUTER && isCurrentComputer();
        if (!needComputer) {
            return;
        }
        reportInput(column);
    }

    public void reportUiInput(int column) {
        boolean needHuman = state == AgentState.WAITING_HUMAN && isCurrentHuman();
        if (!needHuman) {
            return;
        }
        reportInput(column);
    }

    public void reportInput(int column) {
        if (!board.canPlace(column)) {
            return;
        }
        Position lastMove = board.dropPiece(nextColor, column);
        checkBoard(nextColor, lastMove);
        notifyViewComponent();
        stepInto();
    }

    /**
     * core of FSM
     *
     * @author chengli
     * @see AgentState
     */
    private void stepInto() {
        BasePlayer player = getCurrentPlayer();
        if (state == AgentState.READY) {
            if (board.getLeftGrids() <= 0) {
                state = AgentState.NO_WIN;
            }
            if (player instanceof BaseComputerPlayer) {
                state = AgentState.WAITING_COMPUTER;
                ((BaseComputerPlayer) player).askNext(board);
            } else if (player instanceof HumanPlayer) {
                state = AgentState.WAITING_HUMAN;
            }
        } else if (state == AgentState.WIN) {
            System.out.println("Winner : " + (nextColor == GridType.PLAYER_A ? "player_A" : "player_B"));
        }

        notifyViewComponent();
    }

    /**
     * FSM testing
     * <p>
     * JUST FOR TESTING
     *
     * @param state state to set
     * @param next  next color to set
     * @author yang
     */
    public void pseudoSetState(AgentState state, GridType next) {
        this.state = state;
        nextColor = next;
    }

    /**
     * FSM testing, fake method
     * <p>
     * JUST FOR TESTING
     *
     * @author yang
     */
    public void pseudoStepInto() {
        stepInto();
    }
}
