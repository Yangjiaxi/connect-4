package connect4.board;

public class Position {
    int row;
    int col;

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
    
    //Override equals method for JUnit test case Judging Position Object's value
    @Override
    public boolean equals(Object obj) {
    	if(obj instanceof Position) {
    		Position p = (Position)obj;
        	if(this.row == p.row && this.col == p.col)
        		return true;
    	}
    	return false;
    }
}
