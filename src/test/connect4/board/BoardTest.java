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
    
    //测试棋盘格子剩余数是否正确
    //6*7共42个grid
    @Category(BVT.class)
    @Test
    public void testLeftGridsDecMinEdgev1() {
    	//尚未落子时棋盘剩余格数
    	assertEquals(42, testobj.getLeftGrids(), 0);
    }
    
    @Category(BVT.class)
    @Test
    public void testLeftGridsDecMinEdgev2() {
    	//落一子后棋盘剩余格数
    	testobj.leftGridsDec();
    	assertEquals(41, testobj.getLeftGrids(), 0);
    }
    
    @Category(BVT.class)
    @Test
    public void testLeftGridsDecMaxEdgev1() {
    	//落子41次后，棋盘剩余格数
    	for(int i = 0; i<41; i++)
    		testobj.leftGridsDec();
    	assertEquals(1, testobj.getLeftGrids(), 0);
    }
    
    @Category(BVT.class)
    @Test
    public void testLeftGridsDecMaxEdgev2() {
    	//落满42子后，棋盘剩余格数
    	for(int i = 0; i<42; i++)
    		testobj.leftGridsDec();
    	assertEquals(0, testobj.getLeftGrids(), 0);
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
    
    //@Ignore
    @Category(BVT.class)
    @Test
    public void testDropPieceMaxEdgev2() {
    	//在已落满6子的一列再次落子[]
    	for(int i = 0; i<6; i++)
    		testobj.dropPiece(GridType.PLAYER_A, 0);
    	assertNotEquals(new Position(0, 0), testobj.dropPiece(GridType.PLAYER_A, 0));
    }
    
    //测试棋盘能否判断放置棋子的可行性
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
    
    @Category(BVT.class)
    @Test
    public void testCanPlaceMaxEdgev2() {
    	//在一列中放置5个棋子直至只剩最后一个空位置
    	for(int i = 0;i<=4;i++)
    		testobj.dropPiece(GridType.PLAYER_A, 0);
    	assertEquals(true, testobj.canPlace(0));
    }
}
