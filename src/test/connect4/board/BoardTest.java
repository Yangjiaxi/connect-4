package connect4.board;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.experimental.categories.Category;

import connect4.BVT;

public class BoardTest {
	
	Board testobj;
	
	@Before
    public void setUp() throws Exception {
		//构造一个6行7列的基础棋盘对象
		testobj = new Board(6,7);
    }

    @After
    public void tearDown() throws Exception {
    	//释放对象
    	testobj = null;
    }
    
    @Category(BVT.class)
    @Test
    public void testDropPieceMinEdgev1() {
    	//在任意空的一列落子,如第一列
    	assertEquals(new Position(5, 0), testobj.dropPiece(GridType.PLAYER_A, 0));
    }
    
    @Category(BVT.class)
    @Test
    public void testDropPieceMinEdgev2() {
    	//在已落一子的一列再次落子
    	testobj.dropPiece(GridType.PLAYER_A, 0);
    	assertEquals(new Position(4, 0), testobj.dropPiece(GridType.PLAYER_A, 0));
    }
    
    @Category(BVT.class)
    @Test
    public void testDropPieceMaxEdgev1() {
    	//在已落5子的一列再次落子
    	for(int i = 0; i<5; i++)
    		testobj.dropPiece(GridType.PLAYER_A, 0);
    	assertEquals(new Position(0, 0), testobj.dropPiece(GridType.PLAYER_A, 0));
    }
    
    //测试棋盘能否正确放置棋子
    @Category(BVT.class)
    @Test
    public void testCanPlaceMinEdgev1() {
    	assertEquals(true, testobj.canPlace(0));
    }
    
    @Category(BVT.class)
    @Test
    public void testCanPlaceMinEdgev2() {
    	assertEquals(true, testobj.canPlace(6));
    }
    
    @Category(BVT.class)
    @Test
    public void testCanPlaceMaxEdgev1() {
    	//在一列中放置满6个棋子，再检测在该列是否还能放置棋子
    	for(int i = 0;i<=5;i++)
    		testobj.dropPiece(GridType.PLAYER_A, 0);
    	assertEquals(false, testobj.canPlace(0));
    }
    
    //@Ignore
    @Category(BVT.class)
    @Test
    public void testCanPlaceMaxEdgev2() {
    	//在一列中放置5个棋子直至只剩最后一个空位置
    	for(int i = 0;i<=4;i++)
    		testobj.dropPiece(GridType.PLAYER_A, 0);
    	assertEquals(true, testobj.canPlace(0));
    }
}
