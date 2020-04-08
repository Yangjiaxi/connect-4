package connect4.player.computerplayer;

import connect4.Utils;
import connect4.board.Board;
import connect4.board.Grid;
import connect4.board.GridType;

import java.util.stream.IntStream;

import static connect4.Options.*;
import static connect4.Utils.randomDouble;
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
    private boolean winFoundA, winFoundB;
    private boolean winDetectedA, winDetectedB;

    /**
     * another optional:
     * private static final int[] INCREMENT = {0, 1, 100, 10000, 1000000, 100000000};
     */
    private static final int[] INCREMENT = {0, 1, 4, 32, 128, 512};
    private static final int MAX_TEST_STEPS = 4;
    private static final int THRESHOLD_DEPTH = 2;

    private final int maxDepth;
    private final double heuristicProb;

    public MiniMaxPlayer(int thinkingDepth, double acceptProb) {
        maxDepth = thinkingDepth;
        heuristicProb = acceptProb;
    }

    public int alphaBeta(GridType player, Board board) {
        this.board = board;
        winFoundA = winFoundB = false;
        if (player == PLAYER_B) {
            evaluateMoveB(0, 1, -1, Integer.MIN_VALUE + 1,
                    Integer.MAX_VALUE - 1);
            if (winFoundB) {
                return column;
            }
            winFoundA = winFoundB = false;
            evaluateMoveA(0, 1, -1, Integer.MIN_VALUE + 1,
                    Integer.MAX_VALUE - 1);
            if (winFoundA) {
                return column;
            }
            evaluateMoveB(0, maxDepth, -1, Integer.MIN_VALUE + 1,
                    Integer.MAX_VALUE - 1);
        } else {
            evaluateMoveA(0, 1, -1, Integer.MIN_VALUE + 1,
                    Integer.MAX_VALUE - 1);
            if (winFoundA) {
                return column;
            }
            winFoundA = winFoundB = false;
            evaluateMoveB(0, 1, -1, Integer.MIN_VALUE + 1,
                    Integer.MAX_VALUE - 1);
            if (winFoundB) {
                return column;
            }
            evaluateMoveA(0, maxDepth, -1, Integer.MIN_VALUE + 1,
                    Integer.MAX_VALUE - 1);
        }
        return column;
    }

    public static int[] shuffleArray(int upperBound) {
        int[] ar = IntStream.range(0, upperBound).toArray();
        for (int i = ar.length - 1; i > 0; i--) {
            int index = randomInt(i + 1);
            int a = ar[index];
            ar[index] = ar[i];
            ar[i] = a;
        }
        return ar;
    }

    private int evaluateMoveA(int depth, int maxDepth, int col, int alpha, int beta) {
        int min = Integer.MAX_VALUE, score = 0;
        if (col != -1) {
            score = getHeuristicScore(PLAYER_B, col, depth);
            if (winDetectedB) {
                winFoundB = true;
                return score;
            }
        }
        if (depth == maxDepth) {
            return score;
        }
        for (int c : shuffleArray(BOARD_COLUMNS)) {
            if (board.canPlace(c)) {
                board.dropPiece(PLAYER_A, c);
                int value = evaluateMoveB(depth + 1, maxDepth, c, alpha, beta);
                board.unset(c);
                if (value < min) {
                    min = value;
                    if (depth == 0) {
                        column = c;
                    }
                }
                if (value < beta || randomDouble() < heuristicProb) {
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

    private int evaluateMoveB(int depth, int maxDepth, int col, int alpha, int beta) {
        int max = Integer.MIN_VALUE, score = 0;
        if (col != -1) {
            score = getHeuristicScore(PLAYER_A, col, depth);
            if (winDetectedA) {
                winFoundA = true;
                return score;
            }
        }
        if (depth == maxDepth) {
            return score;
        }
        for (int c : shuffleArray(BOARD_COLUMNS)) {
            if (board.canPlace(c)) {
                board.dropPiece(PLAYER_B, c);
                int value = evaluateMoveA(depth + 1, maxDepth, c, alpha, beta);
                board.unset(c);
                if (value > max) {
                    max = value;
                    if (depth == 0) {
                        column = c;
                    }
                }
                if (value > alpha || randomDouble() < heuristicProb) {
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

    private Pair commonResult(int countA, int countB, int depth) {
        if (countA >= GOAL_TO_WIN) {
            winDetectedA = true;
            if (depth <= THRESHOLD_DEPTH) {
                return new Pair(true, Integer.MIN_VALUE + 1);
            }
        } else if (countB >= GOAL_TO_WIN) {
            winDetectedB = true;
            if (depth <= THRESHOLD_DEPTH) {
                return new Pair(true, Integer.MAX_VALUE - 1);
            }
        }
        return new Pair();
    }

    private Pair calcRowScore(GridType player, int col, int depth) {

        int row = board.getFirstAvailableRow(col) + 1;
        Grid[] boardRow = board.getWholeRow(row);
        int countA, countB;

        int score = 0;

        int cStart = col - MAX_TEST_STEPS + 1;
        int colStart = Math.max(cStart, 0);
        int colEnd = MAX_TEST_STEPS - (colStart - cStart);
        for (int c = colStart; c < colEnd; c++) {
            countA = countB = 0;
            for (int val = 0; val < MAX_TEST_STEPS; val++) {
                Grid mark = boardRow[c + val];
                if (mark.getType() == PLAYER_A) {
                    countA++;
                } else if (mark.getType() == PLAYER_B) {
                    countB++;
                }
            }
            Pair tmp = commonResult(countA, countB, depth);
            if (tmp.finished) {
                return tmp;
            }
            score += getScoreIncrement(countA, countB, player);
        }
        return new Pair(false, score);
    }

    private Pair calcColScore(GridType player, int col, int depth) {
        int row = board.getFirstAvailableRow(col) + 1;
        int score = 0;
        int countA = 0, countB = 0;

        int rowEnd = Math.min(board.getRows(), row + 4);
        for (int r = row; r < rowEnd; r++) {
            Grid mark = board.getGrid(r, col);
            if (mark.getType() == PLAYER_A) {
                countA++;
            } else if (mark.getType() == PLAYER_B) {
                countB++;
            }
        }
        Pair tmp = commonResult(countA, countB, depth);
        if (tmp.finished) {
            return tmp;
        }
        score += getScoreIncrement(countA, countB, player);
        return new Pair(false, score);
    }


    private Pair calcDiag1stScore(GridType player, int col, int depth) {
        int row = board.getFirstAvailableRow(col) + 1;
        int score = 0;
        int countA, countB;


        // 1st diag
        int minValue = Math.min(row, col);
        int rowStart = row - minValue;
        int colStart = col - minValue;

        for (int r = rowStart, c = colStart; r <= BOARD_ROWS - MAX_TEST_STEPS
                && c <= BOARD_COLUMNS - MAX_TEST_STEPS; r++, c++) {
            countA = countB = 0;
            for (int val = 0; val < MAX_TEST_STEPS; val++) {
                Grid mark = board.getGrid(r + val, c + val);
                if (mark.getType() == PLAYER_A) {
                    countA++;
                } else if (mark.getType() == PLAYER_B) {
                    countB++;
                }
            }
            Pair tmp = commonResult(countA, countB, depth);
            if (tmp.finished) {
                return tmp;
            }
            score += getScoreIncrement(countA, countB, player);
        }
        return new Pair(false, score);
    }

    private Pair calcDiag2ndScore(GridType player, int col, int depth) {
        int row = board.getFirstAvailableRow(col) + 1;
        int score = 0;
        int countA, countB;

        int minValue = Math.min(BOARD_ROWS - 1 - row, col);
        int rowStart = row + minValue;
        int colStart = col - minValue;
        for (int r = rowStart, c = colStart; r >= MAX_TEST_STEPS - 1 && c <= BOARD_COLUMNS - MAX_TEST_STEPS; r--, c++) {
            countA = countB = 0;
            for (int val = 0; val < MAX_TEST_STEPS; val++) {
                Grid mark = board.getGrid(r - val, c + val);
                if (mark.getType() == PLAYER_A) {
                    countA++;
                } else if (mark.getType() == PLAYER_B) {
                    countB++;
                }
            }
            Pair tmp = commonResult(countA, countB, depth);
            if (tmp.finished) {
                return tmp;
            }
            score += getScoreIncrement(countA, countB, player);
        }
        return new Pair(false, score);
    }

    private int getHeuristicScore(GridType player, int col, int depth) {
        int score = 0;
        winDetectedA = winDetectedB = false;

        Pair rowResult = calcRowScore(player, col, depth);
        if (rowResult.finished) {
            return rowResult.res;
        }
        score += rowResult.res;

        Pair colResult = calcColScore(player, col, depth);
        if (colResult.finished) {
            return colResult.res;
        }
        score += colResult.res;

        Pair diag1stResult = calcDiag1stScore(player, col, depth);
        if (diag1stResult.finished) {
            return diag1stResult.res;
        }
        score += diag1stResult.res;

        Pair diag2ndResult = calcDiag2ndScore(player, col, depth);
        if (diag2ndResult.finished) {
            return diag2ndResult.res;
        }
        score += diag2ndResult.res;

        return score;
    }

    private int getScoreIncrement(int countA, int countB, GridType player) {
        if (countA == countB) {
            if (player == PLAYER_A) {
                return -1;
            }
            return 1;
        } else if (countA < countB) {
            if (player == PLAYER_A) {
                return INCREMENT[countB] - INCREMENT[countA];
            }
            return INCREMENT[countB + 1] - INCREMENT[countA];
        } else {
            if (player == PLAYER_A) {
                return -INCREMENT[countA + 1] + INCREMENT[countB];
            }
            return -INCREMENT[countA] + INCREMENT[countB];
        }
    }

    @Override
    public void askNext(Board board) {
        Utils.service.execute(() -> {
            try {
                Thread.sleep(COMPUTE_SLEEP_TIME_MIN_MS + randomInt(COMPUTE_SLEEP_TIME_RANDOM_MS));
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            agent.reportComputerInput(alphaBeta(agent.getActivePlayer(), board));
        });
    }
}

class Pair {
    final boolean finished;
    int res;

    Pair() {
        finished = false;
    }

    Pair(boolean isFinished, int data) {
        finished = isFinished;
        res = data;
    }
}