package connect4.board;

public class Grid {
    GridType type;

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

