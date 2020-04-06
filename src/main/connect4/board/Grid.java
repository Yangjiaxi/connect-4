package connect4.board;



/**
 * Grid of Board
 *
 * the basic unit of chess board
 *
 * @author chen
 */
public class Grid {
    GridType type;

    /**
     * Is this grid at the line that forms a winning situation?
     */
    boolean inTrace = false;

    public Grid() {
        type = GridType.EMPTY;
    }

    public GridType getType() {
        return type;
    }

    public void setInTrace(boolean is) {
        inTrace = is;
    }

    public boolean isWinTrace() {
        return inTrace;
    }

    void setType(GridType newState) {
        type = newState;
    }
}

