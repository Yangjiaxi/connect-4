package connect4.agent;

import connect4.BVT;
import connect4.EC;
import connect4.board.Board;
import connect4.board.GridType;
import connect4.board.Position;
import connect4.player.BasePlayer;
import connect4.player.HumanPlayer;
import org.junit.*;
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
    public void setUp() throws Exception {
        pa = new HumanPlayer();
        pb = new HumanPlayer();
        testObj = new Agent(pa,pb);
        testObj.initBoard();
        testObj.initState();
    }

    @After
    public void tearDown() throws Exception {
        pa = null;
        pb = null;
        testObj = null;
    }

    @Ignore
    @Test(expected=java.lang.NullPointerException.class) //checkOneDir()只有在下子之后才会触发，故未下子时move为空
    public void testCheckOneDir0() {
        //尚未落子，横、纵、斜向均未"connect"
        assertFalse(testObj.checkOneDir(null,0,1,GridType.PLAYER_A));
        assertFalse(testObj.checkOneDir(null,0,1,GridType.PLAYER_B));

        assertFalse(testObj.checkOneDir(null,1,0,GridType.PLAYER_A));
        assertFalse(testObj.checkOneDir(null,1,0,GridType.PLAYER_B));

        assertFalse(testObj.checkOneDir(null,1,1,GridType.PLAYER_A));
        assertFalse(testObj.checkOneDir(null,1,1,GridType.PLAYER_B));

        assertFalse(testObj.checkOneDir(null,-1,1,GridType.PLAYER_A));
        assertFalse(testObj.checkOneDir(null,-1,1,GridType.PLAYER_B));
    }

    @Category(EC.class)
    @Test
    public void testCheckOneDir10() {
        //只落一子，横向未"connect"
        Board innerBoard = testObj.getBoard();
        Position lastMove = innerBoard.dropPiece(GridType.PLAYER_A, 0);
        assertFalse(testObj.checkOneDir(lastMove,0,1,GridType.PLAYER_A));
        assertFalse(testObj.checkOneDir(lastMove,0,1,GridType.PLAYER_B));
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
        assertFalse(testObj.checkOneDir(lastMove,0,1,GridType.PLAYER_A));
        assertFalse(testObj.checkOneDir(lastMove,0,1,GridType.PLAYER_B));
    }

    @Category({BVT.class,EC.class})
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
        assertTrue(testObj.checkOneDir(lastMove,0,1,GridType.PLAYER_A));
        assertFalse(testObj.checkOneDir(lastMove,0,1,GridType.PLAYER_B));
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
        assertTrue(testObj.checkOneDir(lastMove,0,1,GridType.PLAYER_A));
        assertFalse(testObj.checkOneDir(lastMove,0,1,GridType.PLAYER_B));
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
        assertTrue(testObj.checkOneDir(lastMove,0,1,GridType.PLAYER_A));
        assertFalse(testObj.checkOneDir(lastMove,0,1,GridType.PLAYER_B));
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
        assertTrue(testObj.checkOneDir(lastMove,0,1,GridType.PLAYER_A));
        assertFalse(testObj.checkOneDir(lastMove,0,1,GridType.PLAYER_B));
    }

    @Category(EC.class)
    @Test
    public void testCheckOneDir20() {
        //只落一子，纵向未"connect"
        Board innerBoard = testObj.getBoard();
        Position lastMove = innerBoard.dropPiece(GridType.PLAYER_A, 0);
        assertFalse(testObj.checkOneDir(lastMove,1,0,GridType.PLAYER_A));
        assertFalse(testObj.checkOneDir(lastMove,1,0,GridType.PLAYER_B));
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
        assertFalse(testObj.checkOneDir(lastMove,1,0,GridType.PLAYER_A));
        assertFalse(testObj.checkOneDir(lastMove,1,0,GridType.PLAYER_B));
    }

    @Category({BVT.class,EC.class})
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
        assertTrue(testObj.checkOneDir(lastMove,1,0,GridType.PLAYER_A));
        assertFalse(testObj.checkOneDir(lastMove,1,0,GridType.PLAYER_B));
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
        assertTrue(testObj.checkOneDir(lastMove,1,0,GridType.PLAYER_A));
        assertFalse(testObj.checkOneDir(lastMove,1,0,GridType.PLAYER_B));
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
        assertTrue(testObj.checkOneDir(lastMove,1,0,GridType.PLAYER_A));
        assertFalse(testObj.checkOneDir(lastMove,1,0,GridType.PLAYER_B));
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
        assertTrue(testObj.checkOneDir(lastMove,1,0,GridType.PLAYER_A));
        assertFalse(testObj.checkOneDir(lastMove,1,0,GridType.PLAYER_B));
    }

    @Category(EC.class)
    @Test
    public void testCheckOneDir30() {
        //只落一子，斜向(仰斜)未"connect"
        Board innerBoard = testObj.getBoard();
        Position lastMove = innerBoard.dropPiece(GridType.PLAYER_A, 0);
        assertFalse(testObj.checkOneDir(lastMove,-1,1,GridType.PLAYER_A));
        assertFalse(testObj.checkOneDir(lastMove,-1,1,GridType.PLAYER_B));
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
        assertFalse(testObj.checkOneDir(lastMove,-1,1,GridType.PLAYER_A));
        assertFalse(testObj.checkOneDir(lastMove,-1,1,GridType.PLAYER_B));
    }

    @Category({BVT.class,EC.class})
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
        assertTrue(testObj.checkOneDir(lastMove,-1,1,GridType.PLAYER_A));
        assertFalse(testObj.checkOneDir(lastMove,-1,1,GridType.PLAYER_B));
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
        assertTrue(testObj.checkOneDir(lastMove,-1,1,GridType.PLAYER_A));
        assertFalse(testObj.checkOneDir(lastMove,-1,1,GridType.PLAYER_B));
    }

    @Category(EC.class)
    @Test
    public void testCheckOneDir40() {
        //只落一子，斜向(俯斜)未"connect"
        Board innerBoard = testObj.getBoard();
        Position lastMove = innerBoard.dropPiece(GridType.PLAYER_A, 0);
        assertFalse(testObj.checkOneDir(lastMove,1,1,GridType.PLAYER_A));
        assertFalse(testObj.checkOneDir(lastMove,1,1,GridType.PLAYER_B));
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
        assertFalse(testObj.checkOneDir(lastMove,1,1,GridType.PLAYER_A));
        assertFalse(testObj.checkOneDir(lastMove,1,1,GridType.PLAYER_B));
    }

    @Category({BVT.class,EC.class})
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
        assertTrue(testObj.checkOneDir(lastMove,1,1,GridType.PLAYER_A));
        assertFalse(testObj.checkOneDir(lastMove,1,1,GridType.PLAYER_B));
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
        assertTrue(testObj.checkOneDir(lastMove,1,1,GridType.PLAYER_A));
        assertFalse(testObj.checkOneDir(lastMove,1,1,GridType.PLAYER_B));
    }

    @Test
    public void testReportInput1() {
    }



}

