package connect4.player;

import connect4.board.Board;
import connect4.board.Grid;
import connect4.board.GridType;
import connect4.board.Position;
import connect4.player.computerplayer.ComputerPlayer;
import connect4.render.view.GameView;
import connect4.Options;

import java.util.ArrayList;

public class Agent {
    private final Board board;

    private final Player player_A;
    private final Player player_B;
    private GridType nextColor;

    private final int goal;

    //    AgentState state = AgentState.A_STEP;
    private AgentState state;
    private GameView view;

    public Agent(Player pa, Player pb) {
        board = new Board(Options.BOARD_ROWS, Options.BOARD_COLUMNS);

        goal = Options.GOAL_TO_WIN;
        player_A = pa;
        player_B = pb;
    }

    public Grid getGrid(int row, int col) {
        return board.getGrid(row, col);
    }

    public Board getBoard() { return board; }

    public GridType getActivePlayer() {
        return nextColor;
    }

    public void connect(GameView gameView) {
        view = gameView;
    }

    public AgentState getState() {
        return state;
    }

    public void start() {
        state = AgentState.READY;
        nextColor = GridType.PLAYER_A;
        stepInto();
    }

    private Position doComputerStep(ComputerPlayer player, GridType type) {
        int decide = player.askNext(board);
        return board.dropPiece(type, decide);
    }

    private boolean validPosition(int row, int col) {
        return row >= 0 && row < board.getRows()
                && col >= 0 && col < board.getCols();
    }

    public boolean checkOneDir(Position move, int dRow, int dCol, GridType type) {
        int count = 0;
        ArrayList<Position> tmpTrace = new ArrayList<>();
        int tmpRow = move.getRow();
        int tmpCol = move.getCol();

        // increment direction
        while (validPosition(tmpRow, tmpCol)) {
            if (!getGrid(tmpRow, tmpCol).getType().equals(type)) break;
            tmpTrace.add(new Position(tmpRow, tmpCol));
            tmpRow += dRow;
            tmpCol += dCol;
            ++count;
        }

        // versa
        tmpRow = move.getRow() - dRow;
        tmpCol = move.getCol() - dCol;

        while (validPosition(tmpRow, tmpCol)) {
            if (!getGrid(tmpRow, tmpCol).getType().equals(type)) break;
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

    public void reportInput(int column) {
        if (state != AgentState.WAITING) {
            return;
        }
        if (!board.canPlace(column)) return;

        Position lastMove = board.dropPiece(nextColor, column);
        checkBoard(nextColor, lastMove);
        view.updateComponents();
        stepInto();
    }

    private void stepInto() {
        Player player = nextColor == GridType.PLAYER_A ? player_A : player_B;
        if (state == AgentState.READY) {
            if (board.getLeftGrids() <= 0) {
                state = AgentState.NO_WIN;
            }
            if (player instanceof ComputerPlayer) {
                Position lastMove = doComputerStep((ComputerPlayer) player, nextColor);
                checkBoard(nextColor, lastMove);
                view.updateComponents();
                stepInto();
            } else if (player instanceof HumanPlayer) {
                state = AgentState.WAITING;
            }
        } else if (state == AgentState.WIN) {
            System.out.println("Winner : " + (nextColor == GridType.PLAYER_A ? "player_A" : "player_B"));
        }

        view.updateComponents();
    }


}
