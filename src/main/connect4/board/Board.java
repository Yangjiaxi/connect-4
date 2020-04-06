package connect4.board;

/**
 * @author chen
 * <p>
 * Board is the data holder of this game
 */
public class Board {
    private final Grid[][] data;
    private final int rows, cols;

    private int leftGrids;

    public Board(int nRow, int nCol) {
        rows = nRow;
        cols = nCol;
        leftGrids = nRow * nCol;
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

    public static void main(String[] args) {
        Board obj = new Board(6, 7);
        obj.dropPiece(GridType.PLAYER_A, 3);
        obj.dropPiece(GridType.PLAYER_B, 2);
        obj.print();
    }
}
