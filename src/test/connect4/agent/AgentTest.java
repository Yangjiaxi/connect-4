package connect4.agent;

import connect4.BVT;
import connect4.EC;
import connect4.LC;
import connect4.board.Board;
import connect4.board.GridType;
import connect4.board.Position;
import connect4.player.BasePlayer;
import connect4.player.HumanPlayer;
import org.junit.After;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.experimental.categories.Category;

import static org.junit.Assert.*;

public class AgentTest {
    Agent testObj;
    BasePlayer pa;
    BasePlayer pb;

//    @BeforeClass
//    public static void beforeClass() throws Exception {
//        Player pa = new HumanPlayer();
//        Player pb = new HumanPlayer();
//        Agent agent = new Agent(pa,pb);
//    }
//
//    @AfterClass
//    public static void afterClass() throws Exception {
//    }

    @Before
    public void setUp() {
        pa = new HumanPlayer();
        pb = new HumanPlayer();
        testObj = new Agent(pa, pb);
        testObj.initBoard();
        testObj.initState();
    }

    @After
    public void tearDown() {
        pa = null;
        pb = null;
        testObj = null;
    }

    @Category(EC.class)
    @Test
    public void testCheckOneDir10() {
        //只落一子，横向未"connect"
        Board innerBoard = testObj.getBoard();
        Position lastMove = innerBoard.dropPiece(GridType.PLAYER_A, 0);
        assertFalse(testObj.checkOneDir(lastMove, 0, 1, GridType.PLAYER_A));
        assertFalse(testObj.checkOneDir(lastMove, 0, 1, GridType.PLAYER_B));
    }

    @Category(EC.class)
    @Test
    public void testCheckOneDir11() {
        //落下数子，横向未"connect"
        Board innerBoard = testObj.getBoard();

        innerBoard.dropPiece(GridType.PLAYER_A, 0);
        innerBoard.dropPiece(GridType.PLAYER_B, 0);
        innerBoard.dropPiece(GridType.PLAYER_A, 1);
        innerBoard.dropPiece(GridType.PLAYER_B, 1);

        Position lastMove = innerBoard.dropPiece(GridType.PLAYER_A, 0);
        assertFalse(testObj.checkOneDir(lastMove, 0, 1, GridType.PLAYER_A));
        assertFalse(testObj.checkOneDir(lastMove, 0, 1, GridType.PLAYER_B));
    }

    @Category({BVT.class, EC.class})
    @Test
    public void testCheckOneDir12() {
        //落下数子，横向"connect" (左下边界)
        Board innerBoard = testObj.getBoard();

        innerBoard.dropPiece(GridType.PLAYER_A, 3);
        innerBoard.dropPiece(GridType.PLAYER_B, 3);
        innerBoard.dropPiece(GridType.PLAYER_A, 1);
        innerBoard.dropPiece(GridType.PLAYER_B, 1);
        innerBoard.dropPiece(GridType.PLAYER_A, 2);
        innerBoard.dropPiece(GridType.PLAYER_B, 2);

        Position lastMove = innerBoard.dropPiece(GridType.PLAYER_A, 0);
        assertTrue(testObj.checkOneDir(lastMove, 0, 1, GridType.PLAYER_A));
        assertFalse(testObj.checkOneDir(lastMove, 0, 1, GridType.PLAYER_B));
    }

    @Category(BVT.class)
    @Test
    public void testCheckOneDir13() {
        //落下数子，横向"connect" (右下边界)
        Board innerBoard = testObj.getBoard();

        innerBoard.dropPiece(GridType.PLAYER_A, 3);
        innerBoard.dropPiece(GridType.PLAYER_B, 3);
        innerBoard.dropPiece(GridType.PLAYER_A, 4);
        innerBoard.dropPiece(GridType.PLAYER_B, 4);
        innerBoard.dropPiece(GridType.PLAYER_A, 5);
        innerBoard.dropPiece(GridType.PLAYER_B, 5);

        Position lastMove = innerBoard.dropPiece(GridType.PLAYER_A, 6);
        assertTrue(testObj.checkOneDir(lastMove, 0, 1, GridType.PLAYER_A));
        assertFalse(testObj.checkOneDir(lastMove, 0, 1, GridType.PLAYER_B));
    }

    @Category(BVT.class)
    @Test
    public void testCheckOneDir14() {
        //落下数子，横向"connect" (左上边界)
        Board innerBoard = testObj.getBoard();

        innerBoard.dropPiece(GridType.PLAYER_A, 0);
        innerBoard.dropPiece(GridType.PLAYER_B, 1);
        innerBoard.dropPiece(GridType.PLAYER_A, 0);
        innerBoard.dropPiece(GridType.PLAYER_B, 1);
        innerBoard.dropPiece(GridType.PLAYER_A, 0);
        innerBoard.dropPiece(GridType.PLAYER_B, 1);
        innerBoard.dropPiece(GridType.PLAYER_A, 2);
        innerBoard.dropPiece(GridType.PLAYER_B, 3);
        innerBoard.dropPiece(GridType.PLAYER_A, 2);
        innerBoard.dropPiece(GridType.PLAYER_B, 3);
        innerBoard.dropPiece(GridType.PLAYER_A, 2);
        innerBoard.dropPiece(GridType.PLAYER_B, 3);
        innerBoard.dropPiece(GridType.PLAYER_A, 1);
        innerBoard.dropPiece(GridType.PLAYER_B, 0);
        innerBoard.dropPiece(GridType.PLAYER_A, 1);
        innerBoard.dropPiece(GridType.PLAYER_B, 0);
        innerBoard.dropPiece(GridType.PLAYER_A, 3);
        innerBoard.dropPiece(GridType.PLAYER_B, 2);
        innerBoard.dropPiece(GridType.PLAYER_A, 3);
        innerBoard.dropPiece(GridType.PLAYER_B, 2);
        innerBoard.dropPiece(GridType.PLAYER_A, 3);
        innerBoard.dropPiece(GridType.PLAYER_B, 4);
        innerBoard.dropPiece(GridType.PLAYER_A, 1);
        innerBoard.dropPiece(GridType.PLAYER_B, 4);
        innerBoard.dropPiece(GridType.PLAYER_A, 2);
        innerBoard.dropPiece(GridType.PLAYER_B, 4);

        Position lastMove = innerBoard.dropPiece(GridType.PLAYER_A, 0);
        assertTrue(testObj.checkOneDir(lastMove, 0, 1, GridType.PLAYER_A));
        assertFalse(testObj.checkOneDir(lastMove, 0, 1, GridType.PLAYER_B));
    }

    @Category(BVT.class)
    @Test
    public void testCheckOneDir15() {
        //落下数子，横向"connect" (右上边界)
        Board innerBoard = testObj.getBoard();

        innerBoard.dropPiece(GridType.PLAYER_A, 6);
        innerBoard.dropPiece(GridType.PLAYER_B, 5);
        innerBoard.dropPiece(GridType.PLAYER_A, 6);
        innerBoard.dropPiece(GridType.PLAYER_B, 5);
        innerBoard.dropPiece(GridType.PLAYER_A, 6);
        innerBoard.dropPiece(GridType.PLAYER_B, 5);
        innerBoard.dropPiece(GridType.PLAYER_A, 4);
        innerBoard.dropPiece(GridType.PLAYER_B, 3);
        innerBoard.dropPiece(GridType.PLAYER_A, 4);
        innerBoard.dropPiece(GridType.PLAYER_B, 3);
        innerBoard.dropPiece(GridType.PLAYER_A, 4);
        innerBoard.dropPiece(GridType.PLAYER_B, 3);
        innerBoard.dropPiece(GridType.PLAYER_A, 5);
        innerBoard.dropPiece(GridType.PLAYER_B, 6);
        innerBoard.dropPiece(GridType.PLAYER_A, 5);
        innerBoard.dropPiece(GridType.PLAYER_B, 6);
        innerBoard.dropPiece(GridType.PLAYER_A, 3);
        innerBoard.dropPiece(GridType.PLAYER_B, 4);
        innerBoard.dropPiece(GridType.PLAYER_A, 3);
        innerBoard.dropPiece(GridType.PLAYER_B, 4);
        innerBoard.dropPiece(GridType.PLAYER_A, 3);
        innerBoard.dropPiece(GridType.PLAYER_B, 2);
        innerBoard.dropPiece(GridType.PLAYER_A, 4);
        innerBoard.dropPiece(GridType.PLAYER_B, 2);
        innerBoard.dropPiece(GridType.PLAYER_A, 5);
        innerBoard.dropPiece(GridType.PLAYER_B, 2);

        Position lastMove = innerBoard.dropPiece(GridType.PLAYER_A, 6);
        assertTrue(testObj.checkOneDir(lastMove, 0, 1, GridType.PLAYER_A));
        assertFalse(testObj.checkOneDir(lastMove, 0, 1, GridType.PLAYER_B));
    }

    @Category(EC.class)
    @Test
    public void testCheckOneDir20() {
        //只落一子，纵向未"connect"
        Board innerBoard = testObj.getBoard();
        Position lastMove = innerBoard.dropPiece(GridType.PLAYER_A, 0);
        assertFalse(testObj.checkOneDir(lastMove, 1, 0, GridType.PLAYER_A));
        assertFalse(testObj.checkOneDir(lastMove, 1, 0, GridType.PLAYER_B));
    }

    @Category(EC.class)
    @Test
    public void testCheckOneDir21() {
        //落下数子，纵向未"connect"
        Board innerBoard = testObj.getBoard();

        innerBoard.dropPiece(GridType.PLAYER_A, 0);
        innerBoard.dropPiece(GridType.PLAYER_B, 1);
        innerBoard.dropPiece(GridType.PLAYER_A, 0);
        innerBoard.dropPiece(GridType.PLAYER_B, 1);

        Position lastMove = innerBoard.dropPiece(GridType.PLAYER_A, 0);
        assertFalse(testObj.checkOneDir(lastMove, 1, 0, GridType.PLAYER_A));
        assertFalse(testObj.checkOneDir(lastMove, 1, 0, GridType.PLAYER_B));
    }

    @Category({BVT.class, EC.class})
    @Test
    public void testCheckOneDir22() {
        //落下数子，纵向"connect" (左下边界)
        Board innerBoard = testObj.getBoard();

        innerBoard.dropPiece(GridType.PLAYER_A, 0);
        innerBoard.dropPiece(GridType.PLAYER_B, 1);
        innerBoard.dropPiece(GridType.PLAYER_A, 0);
        innerBoard.dropPiece(GridType.PLAYER_B, 1);
        innerBoard.dropPiece(GridType.PLAYER_A, 0);
        innerBoard.dropPiece(GridType.PLAYER_B, 1);

        Position lastMove = innerBoard.dropPiece(GridType.PLAYER_A, 0);
        assertTrue(testObj.checkOneDir(lastMove, 1, 0, GridType.PLAYER_A));
        assertFalse(testObj.checkOneDir(lastMove, 1, 0, GridType.PLAYER_B));
    }

    @Category(BVT.class)
    @Test
    public void testCheckOneDir23() {
        //落下数子，纵向"connect" (左上边界)
        Board innerBoard = testObj.getBoard();

        innerBoard.dropPiece(GridType.PLAYER_A, 0);
        innerBoard.dropPiece(GridType.PLAYER_B, 1);
        innerBoard.dropPiece(GridType.PLAYER_A, 1);
        innerBoard.dropPiece(GridType.PLAYER_B, 0);
        innerBoard.dropPiece(GridType.PLAYER_A, 0);
        innerBoard.dropPiece(GridType.PLAYER_B, 1);
        innerBoard.dropPiece(GridType.PLAYER_A, 0);
        innerBoard.dropPiece(GridType.PLAYER_B, 1);
        innerBoard.dropPiece(GridType.PLAYER_A, 0);
        innerBoard.dropPiece(GridType.PLAYER_B, 1);

        Position lastMove = innerBoard.dropPiece(GridType.PLAYER_A, 0);
        assertTrue(testObj.checkOneDir(lastMove, 1, 0, GridType.PLAYER_A));
        assertFalse(testObj.checkOneDir(lastMove, 1, 0, GridType.PLAYER_B));
    }

    @Category(BVT.class)
    @Test
    public void testCheckOneDir24() {
        //落下数子，纵向"connect" (右下边界)
        Board innerBoard = testObj.getBoard();

        innerBoard.dropPiece(GridType.PLAYER_A, 6);
        innerBoard.dropPiece(GridType.PLAYER_B, 5);
        innerBoard.dropPiece(GridType.PLAYER_A, 6);
        innerBoard.dropPiece(GridType.PLAYER_B, 5);
        innerBoard.dropPiece(GridType.PLAYER_A, 6);
        innerBoard.dropPiece(GridType.PLAYER_B, 5);

        Position lastMove = innerBoard.dropPiece(GridType.PLAYER_A, 6);
        assertTrue(testObj.checkOneDir(lastMove, 1, 0, GridType.PLAYER_A));
        assertFalse(testObj.checkOneDir(lastMove, 1, 0, GridType.PLAYER_B));
    }

    @Category(BVT.class)
    @Test
    public void testCheckOneDir25() {
        //落下数子，纵向"connect" (右上边界)
        Board innerBoard = testObj.getBoard();

        innerBoard.dropPiece(GridType.PLAYER_A, 6);
        innerBoard.dropPiece(GridType.PLAYER_B, 5);
        innerBoard.dropPiece(GridType.PLAYER_A, 5);
        innerBoard.dropPiece(GridType.PLAYER_B, 6);
        innerBoard.dropPiece(GridType.PLAYER_A, 6);
        innerBoard.dropPiece(GridType.PLAYER_B, 5);
        innerBoard.dropPiece(GridType.PLAYER_A, 6);
        innerBoard.dropPiece(GridType.PLAYER_B, 5);
        innerBoard.dropPiece(GridType.PLAYER_A, 6);
        innerBoard.dropPiece(GridType.PLAYER_B, 5);

        Position lastMove = innerBoard.dropPiece(GridType.PLAYER_A, 6);
        assertTrue(testObj.checkOneDir(lastMove, 1, 0, GridType.PLAYER_A));
        assertFalse(testObj.checkOneDir(lastMove, 1, 0, GridType.PLAYER_B));
    }

    @Category(EC.class)
    @Test
    public void testCheckOneDir30() {
        //只落一子，斜向(仰斜)未"connect"
        Board innerBoard = testObj.getBoard();
        Position lastMove = innerBoard.dropPiece(GridType.PLAYER_A, 0);
        assertFalse(testObj.checkOneDir(lastMove, -1, 1, GridType.PLAYER_A));
        assertFalse(testObj.checkOneDir(lastMove, -1, 1, GridType.PLAYER_B));
    }

    @Category(EC.class)
    @Test
    public void testCheckOneDir31() {
        //落下数子，斜向(仰斜)未"connect"
        Board innerBoard = testObj.getBoard();

        innerBoard.dropPiece(GridType.PLAYER_A, 0);
        innerBoard.dropPiece(GridType.PLAYER_B, 1);
        innerBoard.dropPiece(GridType.PLAYER_A, 0);
        innerBoard.dropPiece(GridType.PLAYER_B, 1);

        Position lastMove = innerBoard.dropPiece(GridType.PLAYER_A, 0);
        assertFalse(testObj.checkOneDir(lastMove, -1, 1, GridType.PLAYER_A));
        assertFalse(testObj.checkOneDir(lastMove, -1, 1, GridType.PLAYER_B));
    }

    @Category({BVT.class, EC.class})
    @Test
    public void testCheckOneDir32() {
        //落下数子，斜向(仰斜)"connect" (左下边界)
        Board innerBoard = testObj.getBoard();

        innerBoard.dropPiece(GridType.PLAYER_A, 0);
        innerBoard.dropPiece(GridType.PLAYER_B, 1);
        innerBoard.dropPiece(GridType.PLAYER_A, 2);
        innerBoard.dropPiece(GridType.PLAYER_B, 3);
        innerBoard.dropPiece(GridType.PLAYER_A, 1);
        innerBoard.dropPiece(GridType.PLAYER_B, 2);
        innerBoard.dropPiece(GridType.PLAYER_A, 2);
        innerBoard.dropPiece(GridType.PLAYER_B, 3);
        innerBoard.dropPiece(GridType.PLAYER_A, 3);
        innerBoard.dropPiece(GridType.PLAYER_B, 0);

        Position lastMove = innerBoard.dropPiece(GridType.PLAYER_A, 3);
        assertTrue(testObj.checkOneDir(lastMove, -1, 1, GridType.PLAYER_A));
        assertFalse(testObj.checkOneDir(lastMove, -1, 1, GridType.PLAYER_B));
    }

    @Category(BVT.class)
    @Test
    public void testCheckOneDir33() {
        //落下数子，斜向(仰斜)"connect" (右上边界)
        Board innerBoard = testObj.getBoard();

        innerBoard.dropPiece(GridType.PLAYER_A, 3);
        innerBoard.dropPiece(GridType.PLAYER_B, 4);
        innerBoard.dropPiece(GridType.PLAYER_A, 5);
        innerBoard.dropPiece(GridType.PLAYER_B, 6);
        innerBoard.dropPiece(GridType.PLAYER_A, 3);
        innerBoard.dropPiece(GridType.PLAYER_B, 4);
        innerBoard.dropPiece(GridType.PLAYER_A, 5);
        innerBoard.dropPiece(GridType.PLAYER_B, 6);
        innerBoard.dropPiece(GridType.PLAYER_A, 3);
        innerBoard.dropPiece(GridType.PLAYER_B, 4);
        innerBoard.dropPiece(GridType.PLAYER_A, 5);
        innerBoard.dropPiece(GridType.PLAYER_B, 6);
        innerBoard.dropPiece(GridType.PLAYER_A, 4);
        innerBoard.dropPiece(GridType.PLAYER_B, 3);
        innerBoard.dropPiece(GridType.PLAYER_A, 6);
        innerBoard.dropPiece(GridType.PLAYER_B, 5);
        innerBoard.dropPiece(GridType.PLAYER_A, 5);
        innerBoard.dropPiece(GridType.PLAYER_B, 6);

        Position lastMove = innerBoard.dropPiece(GridType.PLAYER_A, 6);
        assertTrue(testObj.checkOneDir(lastMove, -1, 1, GridType.PLAYER_A));
        assertFalse(testObj.checkOneDir(lastMove, -1, 1, GridType.PLAYER_B));
    }

    @Category(EC.class)
    @Test
    public void testCheckOneDir40() {
        //只落一子，斜向(俯斜)未"connect"
        Board innerBoard = testObj.getBoard();
        Position lastMove = innerBoard.dropPiece(GridType.PLAYER_A, 0);
        assertFalse(testObj.checkOneDir(lastMove, 1, 1, GridType.PLAYER_A));
        assertFalse(testObj.checkOneDir(lastMove, 1, 1, GridType.PLAYER_B));
    }

    @Category(EC.class)
    @Test
    public void testCheckOneDir41() {
        //落下数子，斜向(俯斜)未"connect"
        Board innerBoard = testObj.getBoard();

        innerBoard.dropPiece(GridType.PLAYER_A, 0);
        innerBoard.dropPiece(GridType.PLAYER_B, 1);
        innerBoard.dropPiece(GridType.PLAYER_A, 0);
        innerBoard.dropPiece(GridType.PLAYER_B, 1);

        Position lastMove = innerBoard.dropPiece(GridType.PLAYER_A, 0);
        assertFalse(testObj.checkOneDir(lastMove, 1, 1, GridType.PLAYER_A));
        assertFalse(testObj.checkOneDir(lastMove, 1, 1, GridType.PLAYER_B));
    }

    @Category({BVT.class, EC.class})
    @Test
    public void testCheckOneDir42() {
        //落下数子，斜向(俯斜)"connect" (左上边界)
        Board innerBoard = testObj.getBoard();

        innerBoard.dropPiece(GridType.PLAYER_A, 3);
        innerBoard.dropPiece(GridType.PLAYER_B, 2);
        innerBoard.dropPiece(GridType.PLAYER_A, 1);
        innerBoard.dropPiece(GridType.PLAYER_B, 0);
        innerBoard.dropPiece(GridType.PLAYER_A, 3);
        innerBoard.dropPiece(GridType.PLAYER_B, 2);
        innerBoard.dropPiece(GridType.PLAYER_A, 1);
        innerBoard.dropPiece(GridType.PLAYER_B, 0);
        innerBoard.dropPiece(GridType.PLAYER_A, 3);
        innerBoard.dropPiece(GridType.PLAYER_B, 2);
        innerBoard.dropPiece(GridType.PLAYER_A, 1);
        innerBoard.dropPiece(GridType.PLAYER_B, 0);
        innerBoard.dropPiece(GridType.PLAYER_A, 2);
        innerBoard.dropPiece(GridType.PLAYER_B, 1);
        innerBoard.dropPiece(GridType.PLAYER_A, 0);
        innerBoard.dropPiece(GridType.PLAYER_B, 0);
        innerBoard.dropPiece(GridType.PLAYER_A, 1);
        innerBoard.dropPiece(GridType.PLAYER_B, 1);

        Position lastMove = innerBoard.dropPiece(GridType.PLAYER_A, 0);
        assertTrue(testObj.checkOneDir(lastMove, 1, 1, GridType.PLAYER_A));
        assertFalse(testObj.checkOneDir(lastMove, 1, 1, GridType.PLAYER_B));
    }

    @Category(BVT.class)
    @Test
    public void testCheckOneDir43() {
        //落下数子，斜向(俯斜)"connect" (右下边界)
        Board innerBoard = testObj.getBoard();

        innerBoard.dropPiece(GridType.PLAYER_A, 6);
        innerBoard.dropPiece(GridType.PLAYER_B, 5);
        innerBoard.dropPiece(GridType.PLAYER_A, 4);
        innerBoard.dropPiece(GridType.PLAYER_B, 3);
        innerBoard.dropPiece(GridType.PLAYER_A, 3);
        innerBoard.dropPiece(GridType.PLAYER_B, 4);
        innerBoard.dropPiece(GridType.PLAYER_A, 5);
        innerBoard.dropPiece(GridType.PLAYER_B, 6);
        innerBoard.dropPiece(GridType.PLAYER_A, 6);
        innerBoard.dropPiece(GridType.PLAYER_B, 5);
        innerBoard.dropPiece(GridType.PLAYER_A, 4);
        innerBoard.dropPiece(GridType.PLAYER_B, 3);

        Position lastMove = innerBoard.dropPiece(GridType.PLAYER_A, 3);
        assertTrue(testObj.checkOneDir(lastMove, 1, 1, GridType.PLAYER_A));
        assertFalse(testObj.checkOneDir(lastMove, 1, 1, GridType.PLAYER_B));
    }

    //白盒测试: checkBoard()
    //对判定的测试
    @Ignore
    @Test(expected = java.lang.NullPointerException.class) //checkBoard()只有在下子之后才会触发，故未下子时lastMove为空
    //未下子时,游戏尚未结束,board状态为初始值(ready)
    public void testCheckBoard0() {
        testObj.checkBoard(GridType.PLAYER_A, null);
        assertEquals(testObj.getState(), AgentState.READY);
        testObj.checkBoard(GridType.PLAYER_B, null);
        assertEquals(testObj.getState(), AgentState.READY);
    }

    @Category(LC.class)
    @Test
    public void testCheckBoard1() {
        //一方落下某子后,游戏尚未结束(ready)
        Board innerBoard = testObj.getBoard();

        innerBoard.dropPiece(GridType.PLAYER_A, 0);
        innerBoard.dropPiece(GridType.PLAYER_B, 1);
        innerBoard.dropPiece(GridType.PLAYER_A, 2);
        Position lastMove = innerBoard.dropPiece(GridType.PLAYER_B, 3);

        testObj.checkBoard(GridType.PLAYER_B, lastMove);
        assertEquals(testObj.getState(), AgentState.READY);
    }

    @Category(LC.class)
    @Test
    public void testCheckBoard2() {
        //一方落下某子后,横向识别出结束条件,游戏结束(win)
        Board innerBoard = testObj.getBoard();

        innerBoard.dropPiece(GridType.PLAYER_A, 3);
        innerBoard.dropPiece(GridType.PLAYER_B, 3);
        innerBoard.dropPiece(GridType.PLAYER_A, 1);
        innerBoard.dropPiece(GridType.PLAYER_B, 1);
        innerBoard.dropPiece(GridType.PLAYER_A, 2);
        innerBoard.dropPiece(GridType.PLAYER_B, 2);

        Position lastMove = innerBoard.dropPiece(GridType.PLAYER_A, 0);

        testObj.checkBoard(GridType.PLAYER_A, lastMove);
        assertEquals(testObj.getState(), AgentState.WIN);
    }

    @Category(LC.class)
    @Test
    public void testCheckBoard3() {
        //一方落下某子后,纵向识别出结束条件,游戏结束(win)
        Board innerBoard = testObj.getBoard();

        innerBoard.dropPiece(GridType.PLAYER_A, 0);
        innerBoard.dropPiece(GridType.PLAYER_B, 1);
        innerBoard.dropPiece(GridType.PLAYER_A, 0);
        innerBoard.dropPiece(GridType.PLAYER_B, 1);
        innerBoard.dropPiece(GridType.PLAYER_A, 0);
        innerBoard.dropPiece(GridType.PLAYER_B, 1);
        Position lastMove = innerBoard.dropPiece(GridType.PLAYER_A, 0);

        testObj.checkBoard(GridType.PLAYER_A, lastMove);
        assertEquals(testObj.getState(), AgentState.WIN);
    }

    @Category(LC.class)
    @Test
    public void testCheckBoard4() {
        //一方落下某子后,斜向(仰斜)识别出结束条件,游戏结束(win)
        Board innerBoard = testObj.getBoard();

        innerBoard.dropPiece(GridType.PLAYER_A, 0);
        innerBoard.dropPiece(GridType.PLAYER_B, 1);
        innerBoard.dropPiece(GridType.PLAYER_A, 2);
        innerBoard.dropPiece(GridType.PLAYER_B, 3);
        innerBoard.dropPiece(GridType.PLAYER_A, 1);
        innerBoard.dropPiece(GridType.PLAYER_B, 2);
        innerBoard.dropPiece(GridType.PLAYER_A, 2);
        innerBoard.dropPiece(GridType.PLAYER_B, 3);
        innerBoard.dropPiece(GridType.PLAYER_A, 3);
        innerBoard.dropPiece(GridType.PLAYER_B, 0);
        Position lastMove = innerBoard.dropPiece(GridType.PLAYER_A, 3);

        testObj.checkBoard(GridType.PLAYER_A, lastMove);
        assertEquals(testObj.getState(), AgentState.WIN);
    }

    @Category(LC.class)
    @Test
    public void testCheckBoard5() {
        //一方落下某子后,斜向(俯斜)识别出结束条件,游戏结束(win)
        Board innerBoard = testObj.getBoard();

        innerBoard.dropPiece(GridType.PLAYER_A, 6);
        innerBoard.dropPiece(GridType.PLAYER_B, 5);
        innerBoard.dropPiece(GridType.PLAYER_A, 4);
        innerBoard.dropPiece(GridType.PLAYER_B, 3);
        innerBoard.dropPiece(GridType.PLAYER_A, 3);
        innerBoard.dropPiece(GridType.PLAYER_B, 4);
        innerBoard.dropPiece(GridType.PLAYER_A, 5);
        innerBoard.dropPiece(GridType.PLAYER_B, 6);
        innerBoard.dropPiece(GridType.PLAYER_A, 6);
        innerBoard.dropPiece(GridType.PLAYER_B, 5);
        innerBoard.dropPiece(GridType.PLAYER_A, 4);
        innerBoard.dropPiece(GridType.PLAYER_B, 3);
        Position lastMove = innerBoard.dropPiece(GridType.PLAYER_A, 3);

        testObj.checkBoard(GridType.PLAYER_A, lastMove);
        assertEquals(testObj.getState(), AgentState.WIN);
    }

}

