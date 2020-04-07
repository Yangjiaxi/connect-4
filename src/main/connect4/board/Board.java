package connect4.board;

import static connect4.Options.BOARD_COLUMNS;
import static connect4.Options.BOARD_ROWS;
import static connect4.board.GridType.*;

/**
 * @author chen
 * <p>
 * Board is the data holder of this game
 */
public class Board {
    private final Grid[][] data;
    private final int rows, cols;

    private int leftGrids;
    private final int[] firstAvailableRow;
    private boolean winnerFound, redWinFound, blackWinFound;
    public static final int[] INCREMENT = {0, 1, 4, 32, 128, 512};

    public Board(int nRow, int nCol) {
        rows = nRow;
        cols = nCol;
        leftGrids = nRow * nCol;
        firstAvailableRow = new int[BOARD_COLUMNS];
        data = new Grid[nRow][nCol];
        for (int i = 0; i < this.rows; ++i) {
            for (int j = 0; j < this.cols; ++j) {
                data[i][j] = new Grid();
            }
        }
    }

    public int getRows() {
        return rows;
    }

    public int getCols() {
        return cols;
    }


    public void leftGridsDec() {
        leftGrids -= 1;
    }

    public int getLeftGrids() {
        return leftGrids;
    }


    public Grid getGrid(int row, int col) {
        return data[row][col];
    }

    public Grid getGrid(Position pos) {
        return data[pos.getRow()][pos.getCol()];
    }

    public Position dropPiece(GridType player, int col) {
        int i;
        for (i = rows - 1; i >= 0; --i) {
            if (data[i][col].getType() == GridType.EMPTY) {
                data[i][col].setType(player);
                leftGridsDec();
                break;
            }
        }
        return new Position(i, col);
    }

    public boolean canPlace(int col) {
        return data[0][col].getType() == GridType.EMPTY;
    }

    public void print() {
        for (int i = 0; i < rows; ++i) {
            for (int j = 0; j < cols; ++j) {
                switch (data[i][j].getType()) {
                    case EMPTY:
                        System.out.print(" ·");
                        break;
                    case PLAYER_A:
                        System.out.print(" X");
                        break;
                    case PLAYER_B:
                        System.out.print(" O");
                        break;
                    default:
                }
            }
            System.out.println();
        }
    }

    public boolean redWinFound() {
        return redWinFound;
    }

    public boolean blackWinFound() {
        return blackWinFound;
    }

    public void unset(int col) throws IllegalArgumentException {
        int row = firstAvailableRow[col];
        if (row >= BOARD_ROWS) {
            throw new IllegalArgumentException(
                    "Column " + (col + 1) + " is already empty.");
        }
        row = ++firstAvailableRow[col];
        data[row][col].setType(EMPTY);
    }

    public int getHeuristicScore(GridType player, int col, int depth, int maxDepth) {
        int score = 0,
                row = firstAvailableRow[col] + 1,
                redCount, blackCount;
        redWinFound = blackWinFound = false;

        ///////////////////////////////////////////////////////////////////////
        // Check row
        ///////////////////////////////////////////////////////////////////////
        redCount = blackCount = 0;
        Grid[] boardRow = data[row];
        int cStart = col - 3,
                colStart = Math.max(cStart, 0),
                colEnd = BOARD_COLUMNS - 3 - (colStart - cStart);
        for (int c = colStart; c < colEnd; c++) {
            redCount = blackCount = 0;
            for (int val = 0; val < 4; val++) {
                Grid mark = boardRow[c + val];
                if (mark.getType() == PLAYER_A) {
                    redCount++;
                } else if (mark.getType() == PLAYER_B) {
                    blackCount++;
                }
            }
            if (redCount == 4) {
                redWinFound = true;
                if (depth <= 2) {
                    return Integer.MIN_VALUE + 1;
                }
            } else if (blackCount == 4) {
                blackWinFound = true;
                if (depth <= 2) {
                    return Integer.MAX_VALUE - 1;
                }
            }
            score += getScoreIncrement(redCount, blackCount, player);
        }

        ///////////////////////////////////////////////////////////////////////
        // Check column
        ///////////////////////////////////////////////////////////////////////
        redCount = blackCount = 0;
        int rowEnd = Math.min(BOARD_ROWS, row + 4);
        for (int r = row; r < rowEnd; r++) {
            Grid mark = data[r][col];
            if (mark.getType() == PLAYER_A) {
                redCount++;
            } else if (mark.getType() == PLAYER_B) {
                blackCount++;
            }
        }
        if (redCount == 4) {
            redWinFound = true;
            if (depth <= 2) {
                return Integer.MIN_VALUE + 1;
            }
        } else if (blackCount == 4) {
            blackWinFound = true;
            if (depth <= 2) {
                return Integer.MAX_VALUE - 1;
            }
        }
        score += getScoreIncrement(redCount, blackCount, player);

        ///////////////////////////////////////////////////////////////////////
        // Check major diagonal
        ///////////////////////////////////////////////////////////////////////
        int minValue = Math.min(row, col),
                rowStart = row - minValue;
        colStart = col - minValue;
        for (int r = rowStart, c = colStart; r <= BOARD_ROWS - 4 && c <= BOARD_COLUMNS - 4; r++, c++) {
            redCount = blackCount = 0;
            for (int val = 0; val < 4; val++) {
                Grid mark = data[r + val][c + val];
                if (mark.getType() == PLAYER_A) {
                    redCount++;
                } else if (mark.getType() == PLAYER_B) {
                    blackCount++;
                }
            }
            if (redCount == 4) {
                redWinFound = true;
                if (depth <= 2) {
                    return Integer.MIN_VALUE + 1;
                }
            } else if (blackCount == 4) {
                blackWinFound = true;
                if (depth <= 2) {
                    return Integer.MAX_VALUE - 1;
                }
            }
            score += getScoreIncrement(redCount, blackCount, player);
        }

        ///////////////////////////////////////////////////////////////////////
        // Check minor diagonal
        ///////////////////////////////////////////////////////////////////////
        minValue = Math.min(BOARD_ROWS - 1 - row, col);
        rowStart = row + minValue;
        colStart = col - minValue;
        for (int r = rowStart, c = colStart; r >= 3 && c <= BOARD_COLUMNS - 4; r--, c++) {
            redCount = blackCount = 0;
            for (int val = 0; val < 4; val++) {
                Grid mark = data[r - val][c + val];
                if (mark.getType() == PLAYER_A) {
                    redCount++;
                } else if (mark.getType() == PLAYER_B) {
                    blackCount++;
                }
            }
            if (redCount == 4) {
                redWinFound = true;
                if (depth <= 2) {
                    return Integer.MIN_VALUE + 1;
                }
            } else if (blackCount == 4) {
                blackWinFound = true;
                if (depth <= 2) {
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

    public static void main(String[] args) {
        Board obj = new Board(6, 7);
        obj.dropPiece(GridType.PLAYER_A, 3);
        obj.dropPiece(GridType.PLAYER_B, 2);
        obj.print();
    }
}
