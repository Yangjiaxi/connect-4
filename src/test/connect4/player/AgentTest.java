package connect4.player;

import connect4.board.Board;
import connect4.board.GridType;
import connect4.board.Position;
import org.junit.*;

import static org.junit.Assert.assertEquals;

public class AgentTest {
    Agent testObj;
    Player pa;
    Player pb;

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
        assertEquals(testObj.checkOneDir(null,0,1,GridType.PLAYER_A), false);
        assertEquals(testObj.checkOneDir(null,0,1,GridType.PLAYER_B), false);

        assertEquals(testObj.checkOneDir(null,1,0,GridType.PLAYER_A), false);
        assertEquals(testObj.checkOneDir(null,1,0,GridType.PLAYER_B), false);

        assertEquals(testObj.checkOneDir(null,1,1,GridType.PLAYER_A), false);
        assertEquals(testObj.checkOneDir(null,1,1,GridType.PLAYER_B), false);

        assertEquals(testObj.checkOneDir(null,-1,1,GridType.PLAYER_A), false);
        assertEquals(testObj.checkOneDir(null,-1,1,GridType.PLAYER_B), false);
    }

    @Test
    public void testCheckOneDir10() {
        //只落一子，横向未"connect"
        Board innerBoard = testObj.getBoard();
        Position lastMove = innerBoard.dropPiece(GridType.PLAYER_A, 0);
        assertEquals(testObj.checkOneDir(lastMove,0,1,GridType.PLAYER_A), false);
        assertEquals(testObj.checkOneDir(lastMove,0,1,GridType.PLAYER_B), false);
    }

    @Test
    public void testCheckOneDir11() {
        //落下数子，横向未"connect"
        Board innerBoard = testObj.getBoard();

        innerBoard.dropPiece(GridType.PLAYER_A, 0);
        innerBoard.dropPiece(GridType.PLAYER_B, 0);
        innerBoard.dropPiece(GridType.PLAYER_A, 1);
        innerBoard.dropPiece(GridType.PLAYER_B, 1);

        Position lastMove = innerBoard.dropPiece(GridType.PLAYER_A, 0);
        assertEquals(testObj.checkOneDir(lastMove,0,1,GridType.PLAYER_A), false);
        assertEquals(testObj.checkOneDir(lastMove,0,1,GridType.PLAYER_B), false);
    }

    @Test
    public void testCheckOneDir20() {
        //只落一子，纵向未"connect"
        Board innerBoard = testObj.getBoard();
        Position lastMove = innerBoard.dropPiece(GridType.PLAYER_A, 0);
        assertEquals(testObj.checkOneDir(lastMove,1,0,GridType.PLAYER_A), false);
        assertEquals(testObj.checkOneDir(lastMove,1,0,GridType.PLAYER_B), false);
    }

    @Test
    public void testCheckOneDir21() {
        //落下数子，纵向未"connect"
        Board innerBoard = testObj.getBoard();

        innerBoard.dropPiece(GridType.PLAYER_A, 0);
        innerBoard.dropPiece(GridType.PLAYER_B, 1);
        innerBoard.dropPiece(GridType.PLAYER_A, 0);
        innerBoard.dropPiece(GridType.PLAYER_B, 1);

        Position lastMove = innerBoard.dropPiece(GridType.PLAYER_A, 0);
        assertEquals(testObj.checkOneDir(lastMove,1,0,GridType.PLAYER_A), false);
        assertEquals(testObj.checkOneDir(lastMove,1,0,GridType.PLAYER_B), false);
    }

    @Test
    public void testCheckOneDir30() {
        //只落一子，斜向(仰斜)未"connect"
        Board innerBoard = testObj.getBoard();
        Position lastMove = innerBoard.dropPiece(GridType.PLAYER_A, 0);
        assertEquals(testObj.checkOneDir(lastMove,-1,1,GridType.PLAYER_A), false);
        assertEquals(testObj.checkOneDir(lastMove,-1,1,GridType.PLAYER_B), false);
    }

    @Test
    public void testCheckOneDir31() {
        //落下数子，斜向(仰斜)未"connect"
        Board innerBoard = testObj.getBoard();

        innerBoard.dropPiece(GridType.PLAYER_A, 0);
        innerBoard.dropPiece(GridType.PLAYER_B, 1);
        innerBoard.dropPiece(GridType.PLAYER_A, 0);
        innerBoard.dropPiece(GridType.PLAYER_B, 1);

        Position lastMove = innerBoard.dropPiece(GridType.PLAYER_A, 0);
        assertEquals(testObj.checkOneDir(lastMove,-1,1,GridType.PLAYER_A), false);
        assertEquals(testObj.checkOneDir(lastMove,-1,1,GridType.PLAYER_B), false);
    }

    @Test
    public void testCheckOneDir40() {
        //只落一子，斜向(俯斜)未"connect"
        Board innerBoard = testObj.getBoard();
        Position lastMove = innerBoard.dropPiece(GridType.PLAYER_A, 0);
        assertEquals(testObj.checkOneDir(lastMove,1,1,GridType.PLAYER_A), false);
        assertEquals(testObj.checkOneDir(lastMove,1,1,GridType.PLAYER_B), false);
    }

    @Test
    public void testCheckOneDir41() {
        //落下数子，斜向(俯斜)未"connect"
        Board innerBoard = testObj.getBoard();

        innerBoard.dropPiece(GridType.PLAYER_A, 0);
        innerBoard.dropPiece(GridType.PLAYER_B, 1);
        innerBoard.dropPiece(GridType.PLAYER_A, 0);
        innerBoard.dropPiece(GridType.PLAYER_B, 1);

        Position lastMove = innerBoard.dropPiece(GridType.PLAYER_A, 0);
        assertEquals(testObj.checkOneDir(lastMove,1,1,GridType.PLAYER_A), false);
        assertEquals(testObj.checkOneDir(lastMove,1,1,GridType.PLAYER_B), false);
    }


    @Test
    public void testReportInput1() {
    }



}
