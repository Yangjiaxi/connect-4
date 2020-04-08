package connect4.board;

import connect4.BVT;
import connect4.EC;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.experimental.categories.Category;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

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
    	//在已落满6子的一列再次落子
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
    
    @Category(EC.class)
    @Test
    public void testPlayerPlacePiecesv1() {
    	//任一方玩家在任一列放置棋子后,对应的棋盘格应正确显示其格子类型(属于何方玩家)
    	//A在第二列落子，B紧随其后也在第二列落子
    	testobj.dropPiece(GridType.PLAYER_A, 1);
    	testobj.dropPiece(GridType.PLAYER_B, 1);
    	assertEquals(GridType.PLAYER_A, testobj.getGrid(5, 1).getType());
    	assertEquals(GridType.PLAYER_B, testobj.getGrid(4, 1).getType());
    }
    
    @Category(EC.class)
    @Test
    public void testPlayerPlacePiecesv2() {
    	//未落子的棋盘格应正确显示其格子类型
    	testobj.dropPiece(GridType.PLAYER_A, 1);
    	testobj.dropPiece(GridType.PLAYER_B, 5);
    	assertEquals(GridType.PLAYER_A, testobj.getGrid(5, 1).getType());
    	assertEquals(GridType.EMPTY, testobj.getGrid(1, 0).getType());
    	assertEquals(GridType.PLAYER_B, testobj.getGrid(5, 5).getType());
    }
    
    /**
     * connect-4-dev2 test contents↓
     */
    
    /**
     * 获取整行棋盘格信息
     */
    @Category(BVT.class)
    @Test
    public void testGetWholeRowMinEdgev1() {
    	//尚未落子时，获取任一行，其行内7列都应为空
    	Grid[] wholeRow = testobj.getWholeRow(5);
    	for(Grid cg: wholeRow) {
    		assertEquals(GridType.EMPTY, cg.getType());
    	}
    }
    
    @Category(BVT.class)
    @Test(expected = IndexOutOfBoundsException.class)
    public void testGetWholeRowMinEdgev2() {
    	//第7行不存在，抛出索引超出异常
    	Grid[] wholeRow = testobj.getWholeRow(6);
    	for(Grid cg: wholeRow) {
    		assertEquals(GridType.EMPTY, cg.getType());
    	}
    }
    
    @Category(EC.class)
    @Test
    public void testGetWholeRow() {
    	//在不同列落多子后，获取不同行的棋盘格
    	//于第1列PA,PB分别落两子
    	testobj.dropPiece(GridType.PLAYER_A, 0);
    	testobj.dropPiece(GridType.PLAYER_B, 0);
    	//于第4列PB落一子
    	testobj.dropPiece(GridType.PLAYER_B, 3);
    	Grid[] row_5 = testobj.getWholeRow(5);
    	Grid[] row_4 = testobj.getWholeRow(4);
    	//检测第六行第1与第4列的棋盘格类型
    	assertEquals(GridType.PLAYER_A, row_5[0].getType());
    	assertEquals(GridType.PLAYER_B, row_5[3].getType());
    	//检测第五行第1列的棋盘格类型
    	assertEquals(GridType.PLAYER_B, row_4[0].getType());
    }
   
    /**
     * 获取某列可用的下一空棋盘格所在行数
     */
    @Category(BVT.class)
    @Test
    public void testGetFirstAvailableRowMinEdgev1() {
    	//尚未落子时，任一列的可用下一空棋盘格都应位于第六行
    	assertEquals(5, testobj.getFirstAvailableRow(0));
    }
    
    @Category(BVT.class)
    @Test
    public void testGetFirstAvailableRowMinEdgev2() {
    	//在某列落一子后,下一空棋盘格应位于第五行
    	testobj.dropPiece(GridType.PLAYER_A, 0);
    	assertEquals(4, testobj.getFirstAvailableRow(0));
    }
    
    @Category(BVT.class)
    @Test
    public void testGetFirstAvailableRowMaxEdgev1() {
    	//在某列连续落子5次后,下一空棋盘格应位于第一行
    	for(int i=0; i<5;i++)
    		testobj.dropPiece(GridType.PLAYER_A, 0);
    	assertEquals(0, testobj.getFirstAvailableRow(0));
    }
    
    @Category(BVT.class)
    @Test
    public void testGetFirstAvailableRowMaxEdgev2() {
    	//某列落满子后,没有空棋盘格,且以-1标识
    	for(int i=0; i<6;i++)
    		testobj.dropPiece(GridType.PLAYER_A, 0);
    	assertEquals(-1, testobj.getFirstAvailableRow(0));
    }
    
    @Category(EC.class)
    @Test
    public void testGetFirstAvailableRow() {
    	//在不同列落子不同次数后，其每列的下一空棋盘格位置应不同
    	//在第二列落4子
    	for(int i = 0;i<4;i++)
    		testobj.dropPiece(GridType.PLAYER_A, 1);
    	//在第四列落1子
    	testobj.dropPiece(GridType.PLAYER_B, 3);
    	//检验第二列与第四列下一待落子的空棋盘格所在行数
    	assertEquals(1, testobj.getFirstAvailableRow(1));
    	assertEquals(4, testobj.getFirstAvailableRow(3));
    }
    
    /**
     * 撤销上一步的落子操作
     */
    @Category(BVT.class)
    @Test(expected = IndexOutOfBoundsException.class)
    public void testUnsetMinEdgev1() {
    	//尚未落子时，执行撤销，数据不发生变化(以第一列为例),且会抛出异常
    	testobj.unset(0);
    	assertEquals(42, testobj.getLeftGrids());
    	assertEquals(5, testobj.getFirstAvailableRow(0));
    	assertEquals(GridType.EMPTY, testobj.getGrid(5, 0).getType());
    }
    
    @Category(BVT.class)
    @Test
    public void testUnsetMinEdgev2() {
    	//在某一列落子后，执行撤销，数据被恢复
    	testobj.dropPiece(GridType.PLAYER_A, 0);
    	assertEquals(41, testobj.getLeftGrids());
    	assertEquals(4, testobj.getFirstAvailableRow(0));
    	assertEquals(GridType.PLAYER_A, testobj.getGrid(5, 0).getType());
    	
    	//执行撤销
    	testobj.unset(0);
    	assertEquals(42, testobj.getLeftGrids());
    	assertEquals(5, testobj.getFirstAvailableRow(0));
    	assertEquals(GridType.EMPTY, testobj.getGrid(5, 0).getType());
    }
    
    @Category(BVT.class)
    @Test
    public void testUnsetMaxEdge() {
    	//某一列落满子
    	for(int i=0;i<6;i++)
    		testobj.dropPiece(GridType.PLAYER_A, 0);
    	assertEquals(36, testobj.getLeftGrids());
    	assertEquals(-1, testobj.getFirstAvailableRow(0));
    	assertEquals(GridType.PLAYER_A, testobj.getGrid(0, 0).getType());
    	
    	//执行撤销
    	testobj.unset(0);
    	assertEquals(37, testobj.getLeftGrids());
    	assertEquals(0, testobj.getFirstAvailableRow(0));
    	assertEquals(GridType.EMPTY, testobj.getGrid(0, 0).getType());
    }
    
    @Category(EC.class)
    @Test
    public void testUnset() {
    	//在某一列两位不同玩家落子后，后落子的玩家选择撤销
    	//PA落子
    	testobj.dropPiece(GridType.PLAYER_A, 3);
    	assertEquals(41, testobj.getLeftGrids());
    	assertEquals(4, testobj.getFirstAvailableRow(3));
    	assertEquals(GridType.PLAYER_A, testobj.getGrid(5, 3).getType());
    	
    	//PB落子
    	testobj.dropPiece(GridType.PLAYER_B, 3);
    	assertEquals(40, testobj.getLeftGrids());
    	assertEquals(3, testobj.getFirstAvailableRow(3));
    	assertEquals(GridType.PLAYER_B, testobj.getGrid(4, 3).getType());
    	
    	//PB选择撤销
    	testobj.unset(3);
    	assertEquals(41, testobj.getLeftGrids());
    	assertEquals(4, testobj.getFirstAvailableRow(3));
    	assertEquals(GridType.EMPTY, testobj.getGrid(4, 3).getType());
    }
}
