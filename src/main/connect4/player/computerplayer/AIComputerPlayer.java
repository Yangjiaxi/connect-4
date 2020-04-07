package connect4.player.computerplayer;

import connect4.Utils;
import connect4.board.Board;
import connect4.board.GridType;

import static connect4.Options.BOARD_COLUMNS;
import static connect4.Options.COMPUTE_SLEEP_TIME_MAX_MS;
import static connect4.Utils.randomInt;

/**
 *  AI computer player
 *
 *  generate next move by Minimax
 *
 *  @author tian
 */
public class AIComputerPlayer extends BaseComputerPlayer {
    private final Board board;
    private int column, boardsAnalyzed, maxDepth;
    private boolean redWinFound, blackWinFound;

    public AIComputerPlayer(Board board, int maxDepth) {
        this.board = board;
        this.boardsAnalyzed = 0;
        this.maxDepth = maxDepth;
    }

    public int getBoardsAnalyzed() {
        return boardsAnalyzed;
    }

    public int alphaBeta(GridType player) {
        redWinFound = blackWinFound = false;
        if (player == GridType.PLAYER_B) {
            evaluateBlackMove(0, 1, -1, Integer.MIN_VALUE + 1,
                    Integer.MAX_VALUE - 1);
            if (blackWinFound) {
                return column;
            }
            redWinFound = blackWinFound = false;
            evaluateRedMove(0, 1, -1, Integer.MIN_VALUE + 1,
                    Integer.MAX_VALUE - 1);
            if (redWinFound) {
                return column;
            }
            evaluateBlackMove(0, maxDepth, -1, Integer.MIN_VALUE + 1,
                    Integer.MAX_VALUE - 1);
        } else {
            evaluateRedMove(0, 1, -1, Integer.MIN_VALUE + 1,
                    Integer.MAX_VALUE - 1);
            if (redWinFound) {
                return column;
            }
            redWinFound = blackWinFound = false;
            evaluateBlackMove(0, 1, -1, Integer.MIN_VALUE + 1,
                    Integer.MAX_VALUE - 1);
            if (blackWinFound) {
                return column;
            }
            evaluateRedMove(0, maxDepth, -1, Integer.MIN_VALUE + 1,
                    Integer.MAX_VALUE - 1);
        }
        return column;
    }

    private int evaluateRedMove(int depth, int maxDepth, int col, int alpha, int beta) {
        boardsAnalyzed++;
        int min = Integer.MAX_VALUE, score = 0;
        if (col != -1) {
            score = board.getHeuristicScore(GridType.PLAYER_B, col, depth, maxDepth);
            if (board.blackWinFound()) {
                blackWinFound = true;
                return score;
            }
        }
        if (depth == maxDepth) {
            return score;
        }
        for (int c = 0; c < BOARD_COLUMNS; c++) {
            if (board.canPlace(c)) {
                board.dropPiece(GridType.PLAYER_A, c);
                int value = evaluateBlackMove(depth + 1, maxDepth, c, alpha, beta);
                board.unset(c);
                if (value < min) {
                    min = value;
                    if (depth == 0) {
                        column = c;
                    }
                }
                if (value < beta) {
                    beta = value;
                }
                if (alpha >= beta) {
                    return beta;
                }
            }
        }

        if (min == Integer.MAX_VALUE) {
            return 0;
        }
        return min;
    }

    private int evaluateBlackMove(int depth, int maxDepth, int col, int alpha, int beta) {
        boardsAnalyzed++;
        int max = Integer.MIN_VALUE, score = 0;
        if (col != -1) {
            score = board.getHeuristicScore(GridType.PLAYER_A, col, depth, maxDepth);
            if (board.redWinFound()) {
                redWinFound = true;
                return score;
            }
        }
        if (depth == maxDepth) {
            return score;
        }
        for (int c = 0; c < BOARD_COLUMNS; c++) {
            if (board.canPlace(c)) {
                board.dropPiece(GridType.PLAYER_B, c);
                int value = evaluateRedMove(depth + 1, maxDepth, c, alpha, beta);
                board.unset(c);
                if (value > max) {
                    max = value;
                    if (depth == 0) {
                        column = c;
                    }
                }
                if (value > alpha) {
                    alpha = value;
                }
                if (alpha >= beta) {
                    return alpha;
                }
            }
        }
        if (max == Integer.MIN_VALUE) {
            return 0;
        }
        return max;
    }

    @Override
    public void askNext(Board board) {
        AIComputerPlayer AI = new AIComputerPlayer(board, 8);
        Utils.service.execute(() -> {
            try {
                Thread.sleep(randomInt(COMPUTE_SLEEP_TIME_MAX_MS));
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            agent.reportComputerInput(alphaBeta(AI.agent.getActivePlayer()));
        });
    }
}
