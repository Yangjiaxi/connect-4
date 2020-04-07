package connect4.player.computerplayer;

import connect4.Options;
import connect4.Utils;
import connect4.board.Board;
import connect4.board.Grid;
import connect4.board.GridType;

import static connect4.Options.*;
import static connect4.Utils.randomInt;
import static connect4.board.GridType.PLAYER_A;
import static connect4.board.GridType.PLAYER_B;

/**
 * AI computer player
 * <p>
 * generate next move by MiniMax
 *
 * @author tian
 */
public class MiniMaxPlayer extends BaseComputerPlayer {
    private Board board;
    private int column;
    private boolean redWinFound, blackWinFound;
    private boolean redWinDetected, blackWinDetected;

    private static final int[] INCREMENT = {0, 1, 4, 32, 128, 512};
    private static final int MAX_TEST_STEPS = 4;
    private static final int THRESHOLD_DEPTH = 2;


    public int alphaBeta(GridType player, Board board) {
        this.board = board;
        redWinFound = blackWinFound = false;
        int maxDepth = Options.AI_DIFFICULTY;
        if (player == PLAYER_B) {
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
        int min = Integer.MAX_VALUE, score = 0;
        if (col != -1) {
            score = getHeuristicScore(PLAYER_B, col, depth);
            if (blackWinDetected) {
                blackWinFound = true;
                return score;
            }
        }
        if (depth == maxDepth) {
            return score;
        }
        for (int c = 0; c < BOARD_COLUMNS; c++) {
            if (board.canPlace(c)) {
                board.dropPiece(PLAYER_A, c);
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
        int max = Integer.MIN_VALUE, score = 0;
        if (col != -1) {
            score = getHeuristicScore(PLAYER_A, col, depth);
            if (redWinDetected) {
                redWinFound = true;
                return score;
            }
        }
        if (depth == maxDepth) {
            return score;
        }
        for (int c = 0; c < BOARD_COLUMNS; c++) {
            if (board.canPlace(c)) {
                board.dropPiece(PLAYER_B, c);
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


    private int getHeuristicScore(GridType player, int col, int depth) {
        int score = 0;
        int row = board.getFirstAvailableRow(col) + 1;
        int redCount, blackCount;

        redWinDetected = blackWinDetected = false;

        // row
        Grid[] boardRow = board.getWholeRow(row);
        int cStart = col - MAX_TEST_STEPS + 1,
                colStart = Math.max(cStart, 0),
                colEnd = MAX_TEST_STEPS - (colStart - cStart);
        for (int c = colStart; c < colEnd; c++) {
            redCount = blackCount = 0;
            for (int val = 0; val < MAX_TEST_STEPS; val++) {
                Grid mark = boardRow[c + val];
                if (mark.getType() == PLAYER_A) {
                    redCount++;
                } else if (mark.getType() == PLAYER_B) {
                    blackCount++;
                }
            }
            if (redCount == GOAL_TO_WIN) {
                redWinDetected = true;
                if (depth <= THRESHOLD_DEPTH) {
                    return Integer.MIN_VALUE + 1;
                }
            } else if (blackCount == GOAL_TO_WIN) {
                blackWinDetected = true;
                if (depth <= THRESHOLD_DEPTH) {
                    return Integer.MAX_VALUE - 1;
                }
            }
            score += getScoreIncrement(redCount, blackCount, player);
        }

        // column
        redCount = blackCount = 0;
        int rowEnd = Math.min(board.getRows(), row + 4);
        for (int r = row; r < rowEnd; r++) {
            Grid mark = board.getGrid(r, col);
            if (mark.getType() == PLAYER_A) {
                redCount++;
            } else if (mark.getType() == PLAYER_B) {
                blackCount++;
            }
        }
        if (redCount == GOAL_TO_WIN) {
            redWinDetected = true;
            if (depth <= THRESHOLD_DEPTH) {
                return Integer.MIN_VALUE + 1;
            }
        } else if (blackCount == GOAL_TO_WIN) {
            blackWinDetected = true;
            if (depth <= THRESHOLD_DEPTH) {
                return Integer.MAX_VALUE - 1;
            }
        }
        score += getScoreIncrement(redCount, blackCount, player);

        // 1st diag
        int minValue = Math.min(row, col),
                rowStart = row - minValue;
        colStart = col - minValue;
        for (int r = rowStart, c = colStart; r <= BOARD_ROWS - MAX_TEST_STEPS
                && c <= BOARD_COLUMNS - MAX_TEST_STEPS; r++, c++) {
            redCount = blackCount = 0;
            for (int val = 0; val < MAX_TEST_STEPS; val++) {
                Grid mark = board.getGrid(r + val, c + val);
                if (mark.getType() == PLAYER_A) {
                    redCount++;
                } else if (mark.getType() == PLAYER_B) {
                    blackCount++;
                }
            }
            if (redCount == GOAL_TO_WIN) {
                redWinDetected = true;
                if (depth <= THRESHOLD_DEPTH) {
                    return Integer.MIN_VALUE + 1;
                }
            } else if (blackCount == GOAL_TO_WIN) {
                blackWinDetected = true;
                if (depth <= THRESHOLD_DEPTH) {
                    return Integer.MAX_VALUE - 1;
                }
            }
            score += getScoreIncrement(redCount, blackCount, player);
        }

        // 2nd diag
        minValue = Math.min(BOARD_ROWS - 1 - row, col);
        rowStart = row + minValue;
        colStart = col - minValue;
        for (int r = rowStart, c = colStart; r >= MAX_TEST_STEPS - 1 && c <= BOARD_COLUMNS - MAX_TEST_STEPS; r--, c++) {
            redCount = blackCount = 0;
            for (int val = 0; val < MAX_TEST_STEPS; val++) {
                Grid mark = board.getGrid(r - val, c + val);
                if (mark.getType() == PLAYER_A) {
                    redCount++;
                } else if (mark.getType() == PLAYER_B) {
                    blackCount++;
                }
            }
            if (redCount == GOAL_TO_WIN) {
                redWinDetected = true;
                if (depth <= THRESHOLD_DEPTH) {
                    return Integer.MIN_VALUE + 1;
                }
            } else if (blackCount == GOAL_TO_WIN) {
                blackWinDetected = true;
                if (depth <= THRESHOLD_DEPTH) {
                    return Integer.MAX_VALUE - 1;
                }
            }
            score += getScoreIncrement(redCount, blackCount, player);
        }
        return score;
    }

    private int getScoreIncrement(int redCount, int blackCount, GridType player) {
        if (redCount == blackCount) {
            if (player == PLAYER_A) {
                return -1;
            }
            return 1;
        } else if (redCount < blackCount) {
            if (player == PLAYER_A) {
                return INCREMENT[blackCount] - INCREMENT[redCount];
            }
            return INCREMENT[blackCount + 1] - INCREMENT[redCount];
        } else {
            if (player == PLAYER_A) {
                return -INCREMENT[redCount + 1] + INCREMENT[blackCount];
            }
            return -INCREMENT[redCount] + INCREMENT[blackCount];
        }
    }

    @Override
    public void askNext(Board board) {
        Utils.service.execute(() -> {
            try {
                Thread.sleep(randomInt(COMPUTE_SLEEP_TIME_MAX_MS));
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            agent.reportComputerInput(alphaBeta(agent.getActivePlayer(), board));
        });
    }
}