package connect4.board;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.experimental.categories.Category;

import connect4.BVT;

public class BoardTest {
	
	Board testobj;
	
	@Before
    public void setUp() throws Exception {
    }

    @After
    public void tearDown() throws Exception {
    }
    
    @Category(BVT.class)
    @Test
    public void dropPieceMinTotalLock1() {
    	testobj = new Board(6,7);
    	//TODO: write test cases according to class Board's logic
    }
    
    @Category(BVT.class)
    @Test
    public void dropPieceMinTotalLock2() {
    	testobj = new Board(6,7);
    }
}
