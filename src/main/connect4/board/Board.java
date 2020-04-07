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
        for (int col = 0; col < BOARD_COLUMNS; col++) {
            firstAvailableRow[col] = BOARD_ROWS - 1;
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
        --firstAvailableRow[col];
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

    public Grid[] getWholeRow(int row) {
        return data[row];
    }

    public void unset(int col) {
        int row = ++firstAvailableRow[col];
        ++leftGrids;
        data[row][col].setType(EMPTY);
    }

    public int getFirstAvailableRow(int col) {
        return firstAvailableRow[col];
    }

    public static void main(String[] args) {
        Board obj = new Board(6, 7);
        obj.dropPiece(GridType.PLAYER_A, 3);
        obj.dropPiece(GridType.PLAYER_B, 2);
        obj.print();
    }
}
