package connect4.board;

/**
 * @author chen
 * Coordinate
 */
public class Position {
    final int row;
    final int col;

    public Position(int row, int col) {
        this.row = row;
        this.col = col;
    }

    public int getCol() {
        return col;
    }

    public int getRow() {
        return row;
    }

    /** Override equals method for JUnit test case Judging Position Object's value
     * @param obj object
     * @return equals or not
     */
    @Override
    public boolean equals(Object obj) {
        if (obj instanceof Position) {
            Position p = (Position) obj;
            return this.row == p.row && this.col == p.col;
        }
        return false;
    }
}
