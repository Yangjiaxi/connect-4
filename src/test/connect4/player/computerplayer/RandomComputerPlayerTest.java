package connect4.player.computerplayer;

import connect4.BVT;
import connect4.EC;
import connect4.agent.Agent;
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

public class RandomComputerPlayerTest {
    RandomComputerPlayer testobj;
    BasePlayer pa;
    Agent agent;

    @Before
    public void setUp() {
        pa = new HumanPlayer();
        testobj = new RandomComputerPlayer();
        agent = new Agent(pa, testobj);
        testobj.setAgent(agent);
        agent.initBoard();
        agent.initState();
    }

    @After
    public void tearDown() {
        //释放对象
        pa = null;
        testobj = null;
        agent = null;
    }

    @Category({EC.class, BVT.class})
    @Test
    public void testAskNextType1_1() {
        //空盘时下一子随机出现在可能的7个位置中的1个
        Board innerBoard = agent.getBoard();

        Position actualPos = innerBoard.dropPiece(GridType.PLAYER_A, testobj.innerGetNext(innerBoard));
        boolean result = 5 == actualPos.getRow() && 0 <= actualPos.getCol() && actualPos.getCol() <= 6;
        assertTrue(result);
    }

    @Category({BVT.class})
    @Test
    public void testAskNextType1_2() {
        //仅落一子时下一子随机出现在可能的7个位置中的1个
        Board innerBoard = agent.getBoard();

        innerBoard.dropPiece(GridType.PLAYER_A, 0);

        Position actualPos = innerBoard.dropPiece(GridType.PLAYER_B, testobj.innerGetNext(innerBoard));
        boolean result = (5 == actualPos.getRow() && 1 <= actualPos.getCol() && actualPos.getCol() <= 6)
                || 4 == actualPos.getRow() && 0 == actualPos.getCol();
        assertTrue(result);
    }

    @Category({BVT.class})
    @Test
    public void testAskNextType1_3() {
        //有1列差一子满且其余列空时下一子随机出现在可能的7个位置中的1个
        Board innerBoard = agent.getBoard();

        innerBoard.dropPiece(GridType.PLAYER_A, 0);
        innerBoard.dropPiece(GridType.PLAYER_B, 0);
        innerBoard.dropPiece(GridType.PLAYER_A, 0);
        innerBoard.dropPiece(GridType.PLAYER_B, 0);
        innerBoard.dropPiece(GridType.PLAYER_A, 0);

        Position actualPos = innerBoard.dropPiece(GridType.PLAYER_B, testobj.innerGetNext(innerBoard));
        boolean result = (5 == actualPos.getRow() && 1 <= actualPos.getCol() && actualPos.getCol() <= 6)
                || 0 == actualPos.getRow() && 0 == actualPos.getCol();
        assertTrue(result);

    }

    @Category({EC.class, BVT.class})
    @Test
    public void testAskNextType2_1() {
        //有1列满且其余列空时下一子随机出现在可能的6个位置中的1个
        Board innerBoard = agent.getBoard();

        innerBoard.dropPiece(GridType.PLAYER_A, 0);
        innerBoard.dropPiece(GridType.PLAYER_B, 0);
        innerBoard.dropPiece(GridType.PLAYER_A, 0);
        innerBoard.dropPiece(GridType.PLAYER_B, 0);
        innerBoard.dropPiece(GridType.PLAYER_A, 0);
        innerBoard.dropPiece(GridType.PLAYER_B, 0);

        Position actualPos = innerBoard.dropPiece(GridType.PLAYER_A, testobj.innerGetNext(innerBoard));
        boolean result = 5 == actualPos.getRow() && 1 <= actualPos.getCol() && actualPos.getCol() <= 6;
        assertTrue(result);
    }

    @Category({BVT.class})
    @Test
    public void testAskNextType2_2() {
        //有1列满且其余列落一子时下一子随机出现在可能的6个位置中的1个
        Board innerBoard = agent.getBoard();

        innerBoard.dropPiece(GridType.PLAYER_A, 0);
        innerBoard.dropPiece(GridType.PLAYER_B, 0);
        innerBoard.dropPiece(GridType.PLAYER_A, 0);
        innerBoard.dropPiece(GridType.PLAYER_B, 0);
        innerBoard.dropPiece(GridType.PLAYER_A, 0);
        innerBoard.dropPiece(GridType.PLAYER_B, 0);
        innerBoard.dropPiece(GridType.PLAYER_A, 1);

        Position actualPos = innerBoard.dropPiece(GridType.PLAYER_B, testobj.innerGetNext(innerBoard));
        boolean result = 5 == actualPos.getRow() && 2 <= actualPos.getCol() && actualPos.getCol() <= 6
                || 4 == actualPos.getRow() && 1 == actualPos.getCol();
        assertTrue(result);
    }

    @Category({BVT.class})
    @Test
    public void testAskNextType2_3() {
        //有1列满、1列差一子满且其余列空时下一子随机出现在可能的6个位置中的1个
        Board innerBoard = agent.getBoard();

        innerBoard.dropPiece(GridType.PLAYER_A, 0);
        innerBoard.dropPiece(GridType.PLAYER_B, 0);
        innerBoard.dropPiece(GridType.PLAYER_A, 0);
        innerBoard.dropPiece(GridType.PLAYER_B, 0);
        innerBoard.dropPiece(GridType.PLAYER_A, 0);
        innerBoard.dropPiece(GridType.PLAYER_B, 0);
        innerBoard.dropPiece(GridType.PLAYER_A, 1);
        innerBoard.dropPiece(GridType.PLAYER_B, 1);
        innerBoard.dropPiece(GridType.PLAYER_A, 1);
        innerBoard.dropPiece(GridType.PLAYER_B, 1);
        innerBoard.dropPiece(GridType.PLAYER_A, 1);

        Position actualPos = innerBoard.dropPiece(GridType.PLAYER_B, testobj.innerGetNext(innerBoard));
        boolean result = 5 == actualPos.getRow() && 2 <= actualPos.getCol() && actualPos.getCol() <= 6
                || 0 == actualPos.getRow() && 1 == actualPos.getCol();
        assertTrue(result);
    }

    @Category({EC.class, BVT.class})
    @Test
    public void testAskNextType3_1() {
        //有2列满且其余列空时下一子随机出现在可能的5个位置中的1个
        Board innerBoard = agent.getBoard();

        innerBoard.dropPiece(GridType.PLAYER_A, 0);
        innerBoard.dropPiece(GridType.PLAYER_B, 0);
        innerBoard.dropPiece(GridType.PLAYER_A, 0);
        innerBoard.dropPiece(GridType.PLAYER_B, 0);
        innerBoard.dropPiece(GridType.PLAYER_A, 0);
        innerBoard.dropPiece(GridType.PLAYER_B, 0);
        innerBoard.dropPiece(GridType.PLAYER_A, 1);
        innerBoard.dropPiece(GridType.PLAYER_B, 1);
        innerBoard.dropPiece(GridType.PLAYER_A, 1);
        innerBoard.dropPiece(GridType.PLAYER_B, 1);
        innerBoard.dropPiece(GridType.PLAYER_A, 1);
        innerBoard.dropPiece(GridType.PLAYER_B, 1);

        Position actualPos = innerBoard.dropPiece(GridType.PLAYER_A, testobj.innerGetNext(innerBoard));
        boolean result = 5 == actualPos.getRow() && 2 <= actualPos.getCol() && actualPos.getCol() <= 6;
        assertTrue(result);
    }

    @Category({BVT.class})
    @Test
    public void testAskNextType3_2() {
        //有2列满且其余列落一子时下一子随机出现在可能的5个位置中的1个
        Board innerBoard = agent.getBoard();

        innerBoard.dropPiece(GridType.PLAYER_A, 0);
        innerBoard.dropPiece(GridType.PLAYER_B, 0);
        innerBoard.dropPiece(GridType.PLAYER_A, 0);
        innerBoard.dropPiece(GridType.PLAYER_B, 0);
        innerBoard.dropPiece(GridType.PLAYER_A, 0);
        innerBoard.dropPiece(GridType.PLAYER_B, 0);
        innerBoard.dropPiece(GridType.PLAYER_A, 1);
        innerBoard.dropPiece(GridType.PLAYER_B, 1);
        innerBoard.dropPiece(GridType.PLAYER_A, 1);
        innerBoard.dropPiece(GridType.PLAYER_B, 1);
        innerBoard.dropPiece(GridType.PLAYER_A, 1);
        innerBoard.dropPiece(GridType.PLAYER_B, 1);
        innerBoard.dropPiece(GridType.PLAYER_A, 2);

        Position actualPos = innerBoard.dropPiece(GridType.PLAYER_B, testobj.innerGetNext(innerBoard));
        boolean result = 5 == actualPos.getRow() && 3 <= actualPos.getCol() && actualPos.getCol() <= 6
                || 4 == actualPos.getRow() && 2 == actualPos.getCol();
        assertTrue(result);
    }

    @Category({BVT.class})
    @Test
    public void testAskNextType3_3() {
        //有2列满、1列差一子满且其余列空时下一子随机出现在可能的5个位置中的1个
        Board innerBoard = agent.getBoard();

        innerBoard.dropPiece(GridType.PLAYER_A, 0);
        innerBoard.dropPiece(GridType.PLAYER_B, 0);
        innerBoard.dropPiece(GridType.PLAYER_A, 0);
        innerBoard.dropPiece(GridType.PLAYER_B, 0);
        innerBoard.dropPiece(GridType.PLAYER_A, 0);
        innerBoard.dropPiece(GridType.PLAYER_B, 0);
        innerBoard.dropPiece(GridType.PLAYER_A, 1);
        innerBoard.dropPiece(GridType.PLAYER_B, 1);
        innerBoard.dropPiece(GridType.PLAYER_A, 1);
        innerBoard.dropPiece(GridType.PLAYER_B, 1);
        innerBoard.dropPiece(GridType.PLAYER_A, 1);
        innerBoard.dropPiece(GridType.PLAYER_B, 1);
        innerBoard.dropPiece(GridType.PLAYER_A, 2);
        innerBoard.dropPiece(GridType.PLAYER_B, 2);
        innerBoard.dropPiece(GridType.PLAYER_A, 2);
        innerBoard.dropPiece(GridType.PLAYER_B, 2);
        innerBoard.dropPiece(GridType.PLAYER_A, 2);

        Position actualPos = innerBoard.dropPiece(GridType.PLAYER_B, testobj.innerGetNext(innerBoard));
        boolean result = 5 == actualPos.getRow() && 3 <= actualPos.getCol() && actualPos.getCol() <= 6
                || 0 == actualPos.getRow() && 2 == actualPos.getCol();
        assertTrue(result);
    }

    @Category({EC.class, BVT.class})
    @Test
    public void testAskNextType4_1() {
        //有3列满且其余列空时下一子随机出现在可能的4个位置中的1个
        Board innerBoard = agent.getBoard();

        innerBoard.dropPiece(GridType.PLAYER_A, 0);
        innerBoard.dropPiece(GridType.PLAYER_B, 1);
        innerBoard.dropPiece(GridType.PLAYER_A, 0);
        innerBoard.dropPiece(GridType.PLAYER_B, 1);
        innerBoard.dropPiece(GridType.PLAYER_A, 0);
        innerBoard.dropPiece(GridType.PLAYER_B, 1);
        innerBoard.dropPiece(GridType.PLAYER_A, 1);
        innerBoard.dropPiece(GridType.PLAYER_B, 0);
        innerBoard.dropPiece(GridType.PLAYER_A, 1);
        innerBoard.dropPiece(GridType.PLAYER_B, 0);
        innerBoard.dropPiece(GridType.PLAYER_A, 1);
        innerBoard.dropPiece(GridType.PLAYER_B, 0);
        innerBoard.dropPiece(GridType.PLAYER_A, 2);
        innerBoard.dropPiece(GridType.PLAYER_B, 2);
        innerBoard.dropPiece(GridType.PLAYER_A, 2);
        innerBoard.dropPiece(GridType.PLAYER_B, 2);
        innerBoard.dropPiece(GridType.PLAYER_A, 2);
        innerBoard.dropPiece(GridType.PLAYER_B, 2);

        Position actualPos = innerBoard.dropPiece(GridType.PLAYER_A, testobj.innerGetNext(innerBoard));
        boolean result = 5 == actualPos.getRow() && 3 <= actualPos.getCol() && actualPos.getCol() <= 6;
        assertTrue(result);
    }

    @Category({BVT.class})
    @Test
    public void testAskNextType4_2() {
        //有3列满且其余列落一子时下一子随机出现在可能的4个位置中的1个
        Board innerBoard = agent.getBoard();

        innerBoard.dropPiece(GridType.PLAYER_A, 0);
        innerBoard.dropPiece(GridType.PLAYER_B, 1);
        innerBoard.dropPiece(GridType.PLAYER_A, 0);
        innerBoard.dropPiece(GridType.PLAYER_B, 1);
        innerBoard.dropPiece(GridType.PLAYER_A, 0);
        innerBoard.dropPiece(GridType.PLAYER_B, 1);
        innerBoard.dropPiece(GridType.PLAYER_A, 1);
        innerBoard.dropPiece(GridType.PLAYER_B, 0);
        innerBoard.dropPiece(GridType.PLAYER_A, 1);
        innerBoard.dropPiece(GridType.PLAYER_B, 0);
        innerBoard.dropPiece(GridType.PLAYER_A, 1);
        innerBoard.dropPiece(GridType.PLAYER_B, 0);
        innerBoard.dropPiece(GridType.PLAYER_A, 2);
        innerBoard.dropPiece(GridType.PLAYER_B, 2);
        innerBoard.dropPiece(GridType.PLAYER_A, 2);
        innerBoard.dropPiece(GridType.PLAYER_B, 2);
        innerBoard.dropPiece(GridType.PLAYER_A, 2);
        innerBoard.dropPiece(GridType.PLAYER_B, 2);

        Position actualPos = innerBoard.dropPiece(GridType.PLAYER_A, testobj.innerGetNext(innerBoard));
        boolean result = 5 == actualPos.getRow() && 3 <= actualPos.getCol() && actualPos.getCol() <= 6;
        assertTrue(result);
    }

    @Category({BVT.class})
    @Test
    public void testAskNextType4_3() {
        //有3列满、1列差一子满且其余列空时下一子随机出现在可能的4个位置中的1个
        Board innerBoard = agent.getBoard();

        innerBoard.dropPiece(GridType.PLAYER_A, 0);
        innerBoard.dropPiece(GridType.PLAYER_B, 1);
        innerBoard.dropPiece(GridType.PLAYER_A, 0);
        innerBoard.dropPiece(GridType.PLAYER_B, 1);
        innerBoard.dropPiece(GridType.PLAYER_A, 0);
        innerBoard.dropPiece(GridType.PLAYER_B, 1);
        innerBoard.dropPiece(GridType.PLAYER_A, 1);
        innerBoard.dropPiece(GridType.PLAYER_B, 0);
        innerBoard.dropPiece(GridType.PLAYER_A, 1);
        innerBoard.dropPiece(GridType.PLAYER_B, 0);
        innerBoard.dropPiece(GridType.PLAYER_A, 1);
        innerBoard.dropPiece(GridType.PLAYER_B, 0);
        innerBoard.dropPiece(GridType.PLAYER_A, 2);
        innerBoard.dropPiece(GridType.PLAYER_B, 3);
        innerBoard.dropPiece(GridType.PLAYER_A, 2);
        innerBoard.dropPiece(GridType.PLAYER_B, 3);
        innerBoard.dropPiece(GridType.PLAYER_A, 2);
        innerBoard.dropPiece(GridType.PLAYER_B, 3);
        innerBoard.dropPiece(GridType.PLAYER_A, 3);
        innerBoard.dropPiece(GridType.PLAYER_B, 2);
        innerBoard.dropPiece(GridType.PLAYER_A, 3);
        innerBoard.dropPiece(GridType.PLAYER_B, 2);
        innerBoard.dropPiece(GridType.PLAYER_A, 3);

        Position actualPos = innerBoard.dropPiece(GridType.PLAYER_B, testobj.innerGetNext(innerBoard));
        boolean result = 5 == actualPos.getRow() && 3 <= actualPos.getCol() && actualPos.getCol() <= 6
                || 0 == actualPos.getRow() && 2 == actualPos.getCol();
        assertTrue(result);
    }

    @Category({EC.class, BVT.class})
    @Test
    public void testAskNextType5_1() {
        //有4列满且其余列空时下一子随机出现在可能的3个位置中的1个
        Board innerBoard = agent.getBoard();

        innerBoard.dropPiece(GridType.PLAYER_A, 0);
        innerBoard.dropPiece(GridType.PLAYER_B, 1);
        innerBoard.dropPiece(GridType.PLAYER_A, 0);
        innerBoard.dropPiece(GridType.PLAYER_B, 1);
        innerBoard.dropPiece(GridType.PLAYER_A, 0);
        innerBoard.dropPiece(GridType.PLAYER_B, 1);
        innerBoard.dropPiece(GridType.PLAYER_A, 1);
        innerBoard.dropPiece(GridType.PLAYER_B, 0);
        innerBoard.dropPiece(GridType.PLAYER_A, 1);
        innerBoard.dropPiece(GridType.PLAYER_B, 0);
        innerBoard.dropPiece(GridType.PLAYER_A, 1);
        innerBoard.dropPiece(GridType.PLAYER_B, 0);
        innerBoard.dropPiece(GridType.PLAYER_A, 2);
        innerBoard.dropPiece(GridType.PLAYER_B, 3);
        innerBoard.dropPiece(GridType.PLAYER_A, 2);
        innerBoard.dropPiece(GridType.PLAYER_B, 3);
        innerBoard.dropPiece(GridType.PLAYER_A, 2);
        innerBoard.dropPiece(GridType.PLAYER_B, 3);
        innerBoard.dropPiece(GridType.PLAYER_A, 3);
        innerBoard.dropPiece(GridType.PLAYER_B, 2);
        innerBoard.dropPiece(GridType.PLAYER_A, 3);
        innerBoard.dropPiece(GridType.PLAYER_B, 2);
        innerBoard.dropPiece(GridType.PLAYER_A, 3);
        innerBoard.dropPiece(GridType.PLAYER_B, 2);

        Position actualPos = innerBoard.dropPiece(GridType.PLAYER_B, testobj.innerGetNext(innerBoard));
        boolean result = 5 == actualPos.getRow() && 4 <= actualPos.getCol() && actualPos.getCol() <= 6;
        assertTrue(result);
    }

    @Category({BVT.class})
    @Test
    public void testAskNextType5_2() {
        //有4列满且其余列落一子时下一子随机出现在可能的3个位置中的1个
        Board innerBoard = agent.getBoard();

        innerBoard.dropPiece(GridType.PLAYER_A, 0);
        innerBoard.dropPiece(GridType.PLAYER_B, 1);
        innerBoard.dropPiece(GridType.PLAYER_A, 0);
        innerBoard.dropPiece(GridType.PLAYER_B, 1);
        innerBoard.dropPiece(GridType.PLAYER_A, 0);
        innerBoard.dropPiece(GridType.PLAYER_B, 1);
        innerBoard.dropPiece(GridType.PLAYER_A, 1);
        innerBoard.dropPiece(GridType.PLAYER_B, 0);
        innerBoard.dropPiece(GridType.PLAYER_A, 1);
        innerBoard.dropPiece(GridType.PLAYER_B, 0);
        innerBoard.dropPiece(GridType.PLAYER_A, 1);
        innerBoard.dropPiece(GridType.PLAYER_B, 0);
        innerBoard.dropPiece(GridType.PLAYER_A, 2);
        innerBoard.dropPiece(GridType.PLAYER_B, 3);
        innerBoard.dropPiece(GridType.PLAYER_A, 2);
        innerBoard.dropPiece(GridType.PLAYER_B, 3);
        innerBoard.dropPiece(GridType.PLAYER_A, 2);
        innerBoard.dropPiece(GridType.PLAYER_B, 3);
        innerBoard.dropPiece(GridType.PLAYER_A, 3);
        innerBoard.dropPiece(GridType.PLAYER_B, 2);
        innerBoard.dropPiece(GridType.PLAYER_A, 3);
        innerBoard.dropPiece(GridType.PLAYER_B, 2);
        innerBoard.dropPiece(GridType.PLAYER_A, 3);
        innerBoard.dropPiece(GridType.PLAYER_B, 2);
        innerBoard.dropPiece(GridType.PLAYER_A, 4);

        Position actualPos = innerBoard.dropPiece(GridType.PLAYER_B, testobj.innerGetNext(innerBoard));
        boolean result = 5 == actualPos.getRow() && 5 <= actualPos.getCol() && actualPos.getCol() <= 6
                || 4 == actualPos.getRow() && 4 == actualPos.getCol();
        assertTrue(result);
    }

    @Category({BVT.class})
    @Test
    public void testAskNextType5_3() {
        //有4列满、1列差一子满且其余列空时下一子随机出现在可能的3个位置中的1个
        Board innerBoard = agent.getBoard();

        innerBoard.dropPiece(GridType.PLAYER_A, 0);
        innerBoard.dropPiece(GridType.PLAYER_B, 1);
        innerBoard.dropPiece(GridType.PLAYER_A, 0);
        innerBoard.dropPiece(GridType.PLAYER_B, 1);
        innerBoard.dropPiece(GridType.PLAYER_A, 0);
        innerBoard.dropPiece(GridType.PLAYER_B, 1);
        innerBoard.dropPiece(GridType.PLAYER_A, 1);
        innerBoard.dropPiece(GridType.PLAYER_B, 0);
        innerBoard.dropPiece(GridType.PLAYER_A, 1);
        innerBoard.dropPiece(GridType.PLAYER_B, 0);
        innerBoard.dropPiece(GridType.PLAYER_A, 1);
        innerBoard.dropPiece(GridType.PLAYER_B, 0);
        innerBoard.dropPiece(GridType.PLAYER_A, 2);
        innerBoard.dropPiece(GridType.PLAYER_B, 3);
        innerBoard.dropPiece(GridType.PLAYER_A, 2);
        innerBoard.dropPiece(GridType.PLAYER_B, 3);
        innerBoard.dropPiece(GridType.PLAYER_A, 2);
        innerBoard.dropPiece(GridType.PLAYER_B, 3);
        innerBoard.dropPiece(GridType.PLAYER_A, 3);
        innerBoard.dropPiece(GridType.PLAYER_B, 2);
        innerBoard.dropPiece(GridType.PLAYER_A, 3);
        innerBoard.dropPiece(GridType.PLAYER_B, 2);
        innerBoard.dropPiece(GridType.PLAYER_A, 3);
        innerBoard.dropPiece(GridType.PLAYER_B, 2);
        innerBoard.dropPiece(GridType.PLAYER_A, 4);
        innerBoard.dropPiece(GridType.PLAYER_B, 4);
        innerBoard.dropPiece(GridType.PLAYER_A, 4);
        innerBoard.dropPiece(GridType.PLAYER_B, 4);
        innerBoard.dropPiece(GridType.PLAYER_A, 4);

        Position actualPos = innerBoard.dropPiece(GridType.PLAYER_B, testobj.innerGetNext(innerBoard));
        boolean result = 5 == actualPos.getRow() && 5 <= actualPos.getCol() && actualPos.getCol() <= 6
                || 0 == actualPos.getRow() && 4 == actualPos.getCol();
        assertTrue(result);
    }

    @Category({EC.class, BVT.class})
    @Test
    public void testAskNextType6_1() {
        //有5列满且其余列空时下一子随机出现在可能的2个位置中的1个
        Board innerBoard = agent.getBoard();

        innerBoard.dropPiece(GridType.PLAYER_A, 0);
        innerBoard.dropPiece(GridType.PLAYER_B, 1);
        innerBoard.dropPiece(GridType.PLAYER_A, 0);
        innerBoard.dropPiece(GridType.PLAYER_B, 1);
        innerBoard.dropPiece(GridType.PLAYER_A, 0);
        innerBoard.dropPiece(GridType.PLAYER_B, 1);
        innerBoard.dropPiece(GridType.PLAYER_A, 1);
        innerBoard.dropPiece(GridType.PLAYER_B, 0);
        innerBoard.dropPiece(GridType.PLAYER_A, 1);
        innerBoard.dropPiece(GridType.PLAYER_B, 0);
        innerBoard.dropPiece(GridType.PLAYER_A, 1);
        innerBoard.dropPiece(GridType.PLAYER_B, 0);
        innerBoard.dropPiece(GridType.PLAYER_A, 2);
        innerBoard.dropPiece(GridType.PLAYER_B, 3);
        innerBoard.dropPiece(GridType.PLAYER_A, 2);
        innerBoard.dropPiece(GridType.PLAYER_B, 3);
        innerBoard.dropPiece(GridType.PLAYER_A, 2);
        innerBoard.dropPiece(GridType.PLAYER_B, 3);
        innerBoard.dropPiece(GridType.PLAYER_A, 3);
        innerBoard.dropPiece(GridType.PLAYER_B, 2);
        innerBoard.dropPiece(GridType.PLAYER_A, 3);
        innerBoard.dropPiece(GridType.PLAYER_B, 2);
        innerBoard.dropPiece(GridType.PLAYER_A, 3);
        innerBoard.dropPiece(GridType.PLAYER_B, 2);
        innerBoard.dropPiece(GridType.PLAYER_A, 4);
        innerBoard.dropPiece(GridType.PLAYER_B, 4);
        innerBoard.dropPiece(GridType.PLAYER_A, 4);
        innerBoard.dropPiece(GridType.PLAYER_B, 4);
        innerBoard.dropPiece(GridType.PLAYER_A, 4);
        innerBoard.dropPiece(GridType.PLAYER_B, 4);

        Position actualPos = innerBoard.dropPiece(GridType.PLAYER_A, testobj.innerGetNext(innerBoard));
        boolean result = 5 == actualPos.getRow() && 5 <= actualPos.getCol() && actualPos.getCol() <= 6;
        assertTrue(result);
    }

    @Category({BVT.class})
    @Test
    public void testAskNextType6_2() {
        //有5列满且其余列落一子时下一子随机出现在可能的2个位置中的1个
        Board innerBoard = agent.getBoard();

        innerBoard.dropPiece(GridType.PLAYER_A, 0);
        innerBoard.dropPiece(GridType.PLAYER_B, 1);
        innerBoard.dropPiece(GridType.PLAYER_A, 0);
        innerBoard.dropPiece(GridType.PLAYER_B, 1);
        innerBoard.dropPiece(GridType.PLAYER_A, 0);
        innerBoard.dropPiece(GridType.PLAYER_B, 1);
        innerBoard.dropPiece(GridType.PLAYER_A, 1);
        innerBoard.dropPiece(GridType.PLAYER_B, 0);
        innerBoard.dropPiece(GridType.PLAYER_A, 1);
        innerBoard.dropPiece(GridType.PLAYER_B, 0);
        innerBoard.dropPiece(GridType.PLAYER_A, 1);
        innerBoard.dropPiece(GridType.PLAYER_B, 0);
        innerBoard.dropPiece(GridType.PLAYER_A, 2);
        innerBoard.dropPiece(GridType.PLAYER_B, 3);
        innerBoard.dropPiece(GridType.PLAYER_A, 2);
        innerBoard.dropPiece(GridType.PLAYER_B, 3);
        innerBoard.dropPiece(GridType.PLAYER_A, 2);
        innerBoard.dropPiece(GridType.PLAYER_B, 3);
        innerBoard.dropPiece(GridType.PLAYER_A, 3);
        innerBoard.dropPiece(GridType.PLAYER_B, 2);
        innerBoard.dropPiece(GridType.PLAYER_A, 3);
        innerBoard.dropPiece(GridType.PLAYER_B, 2);
        innerBoard.dropPiece(GridType.PLAYER_A, 3);
        innerBoard.dropPiece(GridType.PLAYER_B, 2);
        innerBoard.dropPiece(GridType.PLAYER_A, 4);
        innerBoard.dropPiece(GridType.PLAYER_B, 4);
        innerBoard.dropPiece(GridType.PLAYER_A, 4);
        innerBoard.dropPiece(GridType.PLAYER_B, 4);
        innerBoard.dropPiece(GridType.PLAYER_A, 4);
        innerBoard.dropPiece(GridType.PLAYER_B, 4);
        innerBoard.dropPiece(GridType.PLAYER_A, 5);

        Position actualPos = innerBoard.dropPiece(GridType.PLAYER_B, testobj.innerGetNext(innerBoard));
        boolean result = 5 == actualPos.getRow() && actualPos.getCol() == 6
                || 4 == actualPos.getRow() && 5 == actualPos.getCol();
        assertTrue(result);
    }

    @Category({BVT.class})
    @Test
    public void testAskNextType6_3() {
        //有5列满、1列差一子满且其余列空时下一子随机出现在可能的2个位置中的1个
        Board innerBoard = agent.getBoard();

        innerBoard.dropPiece(GridType.PLAYER_A, 0);
        innerBoard.dropPiece(GridType.PLAYER_B, 1);
        innerBoard.dropPiece(GridType.PLAYER_A, 0);
        innerBoard.dropPiece(GridType.PLAYER_B, 1);
        innerBoard.dropPiece(GridType.PLAYER_A, 0);
        innerBoard.dropPiece(GridType.PLAYER_B, 1);
        innerBoard.dropPiece(GridType.PLAYER_A, 1);
        innerBoard.dropPiece(GridType.PLAYER_B, 0);
        innerBoard.dropPiece(GridType.PLAYER_A, 1);
        innerBoard.dropPiece(GridType.PLAYER_B, 0);
        innerBoard.dropPiece(GridType.PLAYER_A, 1);
        innerBoard.dropPiece(GridType.PLAYER_B, 0);
        innerBoard.dropPiece(GridType.PLAYER_A, 2);
        innerBoard.dropPiece(GridType.PLAYER_B, 3);
        innerBoard.dropPiece(GridType.PLAYER_A, 2);
        innerBoard.dropPiece(GridType.PLAYER_B, 3);
        innerBoard.dropPiece(GridType.PLAYER_A, 2);
        innerBoard.dropPiece(GridType.PLAYER_B, 3);
        innerBoard.dropPiece(GridType.PLAYER_A, 3);
        innerBoard.dropPiece(GridType.PLAYER_B, 2);
        innerBoard.dropPiece(GridType.PLAYER_A, 3);
        innerBoard.dropPiece(GridType.PLAYER_B, 2);
        innerBoard.dropPiece(GridType.PLAYER_A, 3);
        innerBoard.dropPiece(GridType.PLAYER_B, 2);
        innerBoard.dropPiece(GridType.PLAYER_A, 4);
        innerBoard.dropPiece(GridType.PLAYER_B, 5);
        innerBoard.dropPiece(GridType.PLAYER_A, 4);
        innerBoard.dropPiece(GridType.PLAYER_B, 5);
        innerBoard.dropPiece(GridType.PLAYER_A, 4);
        innerBoard.dropPiece(GridType.PLAYER_B, 5);
        innerBoard.dropPiece(GridType.PLAYER_A, 5);
        innerBoard.dropPiece(GridType.PLAYER_B, 4);
        innerBoard.dropPiece(GridType.PLAYER_A, 5);
        innerBoard.dropPiece(GridType.PLAYER_B, 4);
        innerBoard.dropPiece(GridType.PLAYER_A, 5);

        Position actualPos = innerBoard.dropPiece(GridType.PLAYER_B, testobj.innerGetNext(innerBoard));
        boolean result = 5 == actualPos.getRow() && actualPos.getCol() == 6
                || 0 == actualPos.getRow() && 4 == actualPos.getCol();
        assertTrue(result);
    }

    @Category({EC.class, BVT.class})
    @Test
    public void testAskNextType7_1() {
        //有6列满且其余列空时下一子随机出现在确定的位置（未满列第一行）
        Board innerBoard = agent.getBoard();

        innerBoard.dropPiece(GridType.PLAYER_A, 0);
        innerBoard.dropPiece(GridType.PLAYER_B, 1);
        innerBoard.dropPiece(GridType.PLAYER_A, 0);
        innerBoard.dropPiece(GridType.PLAYER_B, 1);
        innerBoard.dropPiece(GridType.PLAYER_A, 0);
        innerBoard.dropPiece(GridType.PLAYER_B, 1);
        innerBoard.dropPiece(GridType.PLAYER_A, 1);
        innerBoard.dropPiece(GridType.PLAYER_B, 0);
        innerBoard.dropPiece(GridType.PLAYER_A, 1);
        innerBoard.dropPiece(GridType.PLAYER_B, 0);
        innerBoard.dropPiece(GridType.PLAYER_A, 1);
        innerBoard.dropPiece(GridType.PLAYER_B, 0);
        innerBoard.dropPiece(GridType.PLAYER_A, 2);
        innerBoard.dropPiece(GridType.PLAYER_B, 3);
        innerBoard.dropPiece(GridType.PLAYER_A, 2);
        innerBoard.dropPiece(GridType.PLAYER_B, 3);
        innerBoard.dropPiece(GridType.PLAYER_A, 2);
        innerBoard.dropPiece(GridType.PLAYER_B, 3);
        innerBoard.dropPiece(GridType.PLAYER_A, 3);
        innerBoard.dropPiece(GridType.PLAYER_B, 2);
        innerBoard.dropPiece(GridType.PLAYER_A, 3);
        innerBoard.dropPiece(GridType.PLAYER_B, 2);
        innerBoard.dropPiece(GridType.PLAYER_A, 3);
        innerBoard.dropPiece(GridType.PLAYER_B, 2);
        innerBoard.dropPiece(GridType.PLAYER_A, 4);
        innerBoard.dropPiece(GridType.PLAYER_B, 5);
        innerBoard.dropPiece(GridType.PLAYER_A, 4);
        innerBoard.dropPiece(GridType.PLAYER_B, 5);
        innerBoard.dropPiece(GridType.PLAYER_A, 4);
        innerBoard.dropPiece(GridType.PLAYER_B, 5);
        innerBoard.dropPiece(GridType.PLAYER_A, 5);
        innerBoard.dropPiece(GridType.PLAYER_B, 4);
        innerBoard.dropPiece(GridType.PLAYER_A, 5);
        innerBoard.dropPiece(GridType.PLAYER_B, 4);
        innerBoard.dropPiece(GridType.PLAYER_A, 5);
        innerBoard.dropPiece(GridType.PLAYER_B, 4);

        Position actualPos = innerBoard.dropPiece(GridType.PLAYER_A, testobj.innerGetNext(innerBoard));
        boolean result = 5 == actualPos.getRow() && actualPos.getCol() == 6;
        assertTrue(result);
    }

    @Test
    public void testAskNextType7_2() {
        //有6列满且其余列落一子时下一子随机出现在确定的位置（未满列第二行）
        Board innerBoard = agent.getBoard();

        innerBoard.dropPiece(GridType.PLAYER_A, 0);
        innerBoard.dropPiece(GridType.PLAYER_B, 1);
        innerBoard.dropPiece(GridType.PLAYER_A, 0);
        innerBoard.dropPiece(GridType.PLAYER_B, 1);
        innerBoard.dropPiece(GridType.PLAYER_A, 0);
        innerBoard.dropPiece(GridType.PLAYER_B, 1);
        innerBoard.dropPiece(GridType.PLAYER_A, 1);
        innerBoard.dropPiece(GridType.PLAYER_B, 0);
        innerBoard.dropPiece(GridType.PLAYER_A, 1);
        innerBoard.dropPiece(GridType.PLAYER_B, 0);
        innerBoard.dropPiece(GridType.PLAYER_A, 1);
        innerBoard.dropPiece(GridType.PLAYER_B, 0);
        innerBoard.dropPiece(GridType.PLAYER_A, 2);
        innerBoard.dropPiece(GridType.PLAYER_B, 3);
        innerBoard.dropPiece(GridType.PLAYER_A, 2);
        innerBoard.dropPiece(GridType.PLAYER_B, 3);
        innerBoard.dropPiece(GridType.PLAYER_A, 2);
        innerBoard.dropPiece(GridType.PLAYER_B, 3);
        innerBoard.dropPiece(GridType.PLAYER_A, 3);
        innerBoard.dropPiece(GridType.PLAYER_B, 2);
        innerBoard.dropPiece(GridType.PLAYER_A, 3);
        innerBoard.dropPiece(GridType.PLAYER_B, 2);
        innerBoard.dropPiece(GridType.PLAYER_A, 3);
        innerBoard.dropPiece(GridType.PLAYER_B, 2);
        innerBoard.dropPiece(GridType.PLAYER_A, 4);
        innerBoard.dropPiece(GridType.PLAYER_B, 5);
        innerBoard.dropPiece(GridType.PLAYER_A, 4);
        innerBoard.dropPiece(GridType.PLAYER_B, 5);
        innerBoard.dropPiece(GridType.PLAYER_A, 4);
        innerBoard.dropPiece(GridType.PLAYER_B, 5);
        innerBoard.dropPiece(GridType.PLAYER_A, 5);
        innerBoard.dropPiece(GridType.PLAYER_B, 4);
        innerBoard.dropPiece(GridType.PLAYER_A, 5);
        innerBoard.dropPiece(GridType.PLAYER_B, 4);
        innerBoard.dropPiece(GridType.PLAYER_A, 5);
        innerBoard.dropPiece(GridType.PLAYER_B, 4);
        innerBoard.dropPiece(GridType.PLAYER_A, 6);

        Position actualPos = innerBoard.dropPiece(GridType.PLAYER_B, testobj.innerGetNext(innerBoard));
        boolean result = 4 == actualPos.getRow() && actualPos.getCol() == 6;
        assertTrue(result);
    }

    @Test
    public void testAskNextType7_3() {
        //有6列满且其余列落二子时下一子随机出现在确定的位置（未满列第三行）
        Board innerBoard = agent.getBoard();

        innerBoard.dropPiece(GridType.PLAYER_A, 0);
        innerBoard.dropPiece(GridType.PLAYER_B, 1);
        innerBoard.dropPiece(GridType.PLAYER_A, 0);
        innerBoard.dropPiece(GridType.PLAYER_B, 1);
        innerBoard.dropPiece(GridType.PLAYER_A, 0);
        innerBoard.dropPiece(GridType.PLAYER_B, 1);
        innerBoard.dropPiece(GridType.PLAYER_A, 1);
        innerBoard.dropPiece(GridType.PLAYER_B, 0);
        innerBoard.dropPiece(GridType.PLAYER_A, 1);
        innerBoard.dropPiece(GridType.PLAYER_B, 0);
        innerBoard.dropPiece(GridType.PLAYER_A, 1);
        innerBoard.dropPiece(GridType.PLAYER_B, 0);
        innerBoard.dropPiece(GridType.PLAYER_A, 2);
        innerBoard.dropPiece(GridType.PLAYER_B, 3);
        innerBoard.dropPiece(GridType.PLAYER_A, 2);
        innerBoard.dropPiece(GridType.PLAYER_B, 3);
        innerBoard.dropPiece(GridType.PLAYER_A, 2);
        innerBoard.dropPiece(GridType.PLAYER_B, 3);
        innerBoard.dropPiece(GridType.PLAYER_A, 3);
        innerBoard.dropPiece(GridType.PLAYER_B, 2);
        innerBoard.dropPiece(GridType.PLAYER_A, 3);
        innerBoard.dropPiece(GridType.PLAYER_B, 2);
        innerBoard.dropPiece(GridType.PLAYER_A, 3);
        innerBoard.dropPiece(GridType.PLAYER_B, 2);
        innerBoard.dropPiece(GridType.PLAYER_A, 4);
        innerBoard.dropPiece(GridType.PLAYER_B, 5);
        innerBoard.dropPiece(GridType.PLAYER_A, 4);
        innerBoard.dropPiece(GridType.PLAYER_B, 5);
        innerBoard.dropPiece(GridType.PLAYER_A, 4);
        innerBoard.dropPiece(GridType.PLAYER_B, 5);
        innerBoard.dropPiece(GridType.PLAYER_A, 5);
        innerBoard.dropPiece(GridType.PLAYER_B, 4);
        innerBoard.dropPiece(GridType.PLAYER_A, 5);
        innerBoard.dropPiece(GridType.PLAYER_B, 4);
        innerBoard.dropPiece(GridType.PLAYER_A, 5);
        innerBoard.dropPiece(GridType.PLAYER_B, 4);
        innerBoard.dropPiece(GridType.PLAYER_A, 6);
        innerBoard.dropPiece(GridType.PLAYER_B, 6);

        Position actualPos = innerBoard.dropPiece(GridType.PLAYER_A, testobj.innerGetNext(innerBoard));
        boolean result = 3 == actualPos.getRow() && actualPos.getCol() == 6;
        assertTrue(result);
    }

    @Test
    public void testAskNextType7_4() {
        //有6列满且其余列落三子时下一子随机出现在确定的位置（未满列第四行）
        Board innerBoard = agent.getBoard();

        innerBoard.dropPiece(GridType.PLAYER_A, 0);
        innerBoard.dropPiece(GridType.PLAYER_B, 1);
        innerBoard.dropPiece(GridType.PLAYER_A, 0);
        innerBoard.dropPiece(GridType.PLAYER_B, 1);
        innerBoard.dropPiece(GridType.PLAYER_A, 0);
        innerBoard.dropPiece(GridType.PLAYER_B, 1);
        innerBoard.dropPiece(GridType.PLAYER_A, 1);
        innerBoard.dropPiece(GridType.PLAYER_B, 0);
        innerBoard.dropPiece(GridType.PLAYER_A, 1);
        innerBoard.dropPiece(GridType.PLAYER_B, 0);
        innerBoard.dropPiece(GridType.PLAYER_A, 1);
        innerBoard.dropPiece(GridType.PLAYER_B, 0);
        innerBoard.dropPiece(GridType.PLAYER_A, 2);
        innerBoard.dropPiece(GridType.PLAYER_B, 3);
        innerBoard.dropPiece(GridType.PLAYER_A, 2);
        innerBoard.dropPiece(GridType.PLAYER_B, 3);
        innerBoard.dropPiece(GridType.PLAYER_A, 2);
        innerBoard.dropPiece(GridType.PLAYER_B, 3);
        innerBoard.dropPiece(GridType.PLAYER_A, 3);
        innerBoard.dropPiece(GridType.PLAYER_B, 2);
        innerBoard.dropPiece(GridType.PLAYER_A, 3);
        innerBoard.dropPiece(GridType.PLAYER_B, 2);
        innerBoard.dropPiece(GridType.PLAYER_A, 3);
        innerBoard.dropPiece(GridType.PLAYER_B, 2);
        innerBoard.dropPiece(GridType.PLAYER_A, 4);
        innerBoard.dropPiece(GridType.PLAYER_B, 5);
        innerBoard.dropPiece(GridType.PLAYER_A, 4);
        innerBoard.dropPiece(GridType.PLAYER_B, 5);
        innerBoard.dropPiece(GridType.PLAYER_A, 4);
        innerBoard.dropPiece(GridType.PLAYER_B, 5);
        innerBoard.dropPiece(GridType.PLAYER_A, 5);
        innerBoard.dropPiece(GridType.PLAYER_B, 4);
        innerBoard.dropPiece(GridType.PLAYER_A, 5);
        innerBoard.dropPiece(GridType.PLAYER_B, 4);
        innerBoard.dropPiece(GridType.PLAYER_A, 5);
        innerBoard.dropPiece(GridType.PLAYER_B, 4);
        innerBoard.dropPiece(GridType.PLAYER_A, 6);
        innerBoard.dropPiece(GridType.PLAYER_B, 6);
        innerBoard.dropPiece(GridType.PLAYER_A, 6);

        Position actualPos = innerBoard.dropPiece(GridType.PLAYER_B, testobj.innerGetNext(innerBoard));
        boolean result = 2 == actualPos.getRow() && actualPos.getCol() == 6;
        assertTrue(result);
    }

    @Test
    public void testAskNextType7_5() {
        //有6列满且其余列落四子时下一子随机出现在确定的位置（未满列第五行）
        Board innerBoard = agent.getBoard();

        innerBoard.dropPiece(GridType.PLAYER_A, 0);
        innerBoard.dropPiece(GridType.PLAYER_B, 1);
        innerBoard.dropPiece(GridType.PLAYER_A, 0);
        innerBoard.dropPiece(GridType.PLAYER_B, 1);
        innerBoard.dropPiece(GridType.PLAYER_A, 0);
        innerBoard.dropPiece(GridType.PLAYER_B, 1);
        innerBoard.dropPiece(GridType.PLAYER_A, 1);
        innerBoard.dropPiece(GridType.PLAYER_B, 0);
        innerBoard.dropPiece(GridType.PLAYER_A, 1);
        innerBoard.dropPiece(GridType.PLAYER_B, 0);
        innerBoard.dropPiece(GridType.PLAYER_A, 1);
        innerBoard.dropPiece(GridType.PLAYER_B, 0);
        innerBoard.dropPiece(GridType.PLAYER_A, 2);
        innerBoard.dropPiece(GridType.PLAYER_B, 3);
        innerBoard.dropPiece(GridType.PLAYER_A, 2);
        innerBoard.dropPiece(GridType.PLAYER_B, 3);
        innerBoard.dropPiece(GridType.PLAYER_A, 2);
        innerBoard.dropPiece(GridType.PLAYER_B, 3);
        innerBoard.dropPiece(GridType.PLAYER_A, 3);
        innerBoard.dropPiece(GridType.PLAYER_B, 2);
        innerBoard.dropPiece(GridType.PLAYER_A, 3);
        innerBoard.dropPiece(GridType.PLAYER_B, 2);
        innerBoard.dropPiece(GridType.PLAYER_A, 3);
        innerBoard.dropPiece(GridType.PLAYER_B, 2);
        innerBoard.dropPiece(GridType.PLAYER_A, 4);
        innerBoard.dropPiece(GridType.PLAYER_B, 5);
        innerBoard.dropPiece(GridType.PLAYER_A, 4);
        innerBoard.dropPiece(GridType.PLAYER_B, 5);
        innerBoard.dropPiece(GridType.PLAYER_A, 4);
        innerBoard.dropPiece(GridType.PLAYER_B, 5);
        innerBoard.dropPiece(GridType.PLAYER_A, 5);
        innerBoard.dropPiece(GridType.PLAYER_B, 4);
        innerBoard.dropPiece(GridType.PLAYER_A, 5);
        innerBoard.dropPiece(GridType.PLAYER_B, 4);
        innerBoard.dropPiece(GridType.PLAYER_A, 5);
        innerBoard.dropPiece(GridType.PLAYER_B, 4);
        innerBoard.dropPiece(GridType.PLAYER_A, 6);
        innerBoard.dropPiece(GridType.PLAYER_B, 6);
        innerBoard.dropPiece(GridType.PLAYER_A, 6);
        innerBoard.dropPiece(GridType.PLAYER_B, 6);

        Position actualPos = innerBoard.dropPiece(GridType.PLAYER_A, testobj.innerGetNext(innerBoard));
        boolean result = 1 == actualPos.getRow() && actualPos.getCol() == 6;
        assertTrue(result);
    }

    @Category({BVT.class})
    @Test
    public void testAskNextType7_6() {
        //有6列满且其余列落五子时下一子随机出现在确定的位置（未满列第六行）
        Board innerBoard = agent.getBoard();

        innerBoard.dropPiece(GridType.PLAYER_A, 0);
        innerBoard.dropPiece(GridType.PLAYER_B, 1);
        innerBoard.dropPiece(GridType.PLAYER_A, 0);
        innerBoard.dropPiece(GridType.PLAYER_B, 1);
        innerBoard.dropPiece(GridType.PLAYER_A, 0);
        innerBoard.dropPiece(GridType.PLAYER_B, 1);
        innerBoard.dropPiece(GridType.PLAYER_A, 1);
        innerBoard.dropPiece(GridType.PLAYER_B, 0);
        innerBoard.dropPiece(GridType.PLAYER_A, 1);
        innerBoard.dropPiece(GridType.PLAYER_B, 0);
        innerBoard.dropPiece(GridType.PLAYER_A, 1);
        innerBoard.dropPiece(GridType.PLAYER_B, 0);
        innerBoard.dropPiece(GridType.PLAYER_A, 2);
        innerBoard.dropPiece(GridType.PLAYER_B, 3);
        innerBoard.dropPiece(GridType.PLAYER_A, 2);
        innerBoard.dropPiece(GridType.PLAYER_B, 3);
        innerBoard.dropPiece(GridType.PLAYER_A, 2);
        innerBoard.dropPiece(GridType.PLAYER_B, 3);
        innerBoard.dropPiece(GridType.PLAYER_A, 3);
        innerBoard.dropPiece(GridType.PLAYER_B, 2);
        innerBoard.dropPiece(GridType.PLAYER_A, 3);
        innerBoard.dropPiece(GridType.PLAYER_B, 2);
        innerBoard.dropPiece(GridType.PLAYER_A, 3);
        innerBoard.dropPiece(GridType.PLAYER_B, 2);
        innerBoard.dropPiece(GridType.PLAYER_A, 4);
        innerBoard.dropPiece(GridType.PLAYER_B, 5);
        innerBoard.dropPiece(GridType.PLAYER_A, 4);
        innerBoard.dropPiece(GridType.PLAYER_B, 5);
        innerBoard.dropPiece(GridType.PLAYER_A, 4);
        innerBoard.dropPiece(GridType.PLAYER_B, 5);
        innerBoard.dropPiece(GridType.PLAYER_A, 5);
        innerBoard.dropPiece(GridType.PLAYER_B, 4);
        innerBoard.dropPiece(GridType.PLAYER_A, 5);
        innerBoard.dropPiece(GridType.PLAYER_B, 4);
        innerBoard.dropPiece(GridType.PLAYER_A, 5);
        innerBoard.dropPiece(GridType.PLAYER_B, 4);
        innerBoard.dropPiece(GridType.PLAYER_A, 6);
        innerBoard.dropPiece(GridType.PLAYER_B, 6);
        innerBoard.dropPiece(GridType.PLAYER_A, 6);
        innerBoard.dropPiece(GridType.PLAYER_B, 6);
        innerBoard.dropPiece(GridType.PLAYER_A, 6);

        Position actualPos = innerBoard.dropPiece(GridType.PLAYER_B, testobj.innerGetNext(innerBoard));
        boolean result = 0 == actualPos.getRow() && actualPos.getCol() == 6;
        assertTrue(result);
    }

    @Category({EC.class, BVT.class})
    @Ignore
    @Test
    public void testAskNextType8() {
        //满盘时下一子不出现
        Board innerBoard = agent.getBoard();

        innerBoard.dropPiece(GridType.PLAYER_A, 0);
        innerBoard.dropPiece(GridType.PLAYER_B, 1);
        innerBoard.dropPiece(GridType.PLAYER_A, 0);
        innerBoard.dropPiece(GridType.PLAYER_B, 1);
        innerBoard.dropPiece(GridType.PLAYER_A, 0);
        innerBoard.dropPiece(GridType.PLAYER_B, 1);
        innerBoard.dropPiece(GridType.PLAYER_A, 1);
        innerBoard.dropPiece(GridType.PLAYER_B, 0);
        innerBoard.dropPiece(GridType.PLAYER_A, 1);
        innerBoard.dropPiece(GridType.PLAYER_B, 0);
        innerBoard.dropPiece(GridType.PLAYER_A, 1);
        innerBoard.dropPiece(GridType.PLAYER_B, 0);
        innerBoard.dropPiece(GridType.PLAYER_A, 2);
        innerBoard.dropPiece(GridType.PLAYER_B, 3);
        innerBoard.dropPiece(GridType.PLAYER_A, 2);
        innerBoard.dropPiece(GridType.PLAYER_B, 3);
        innerBoard.dropPiece(GridType.PLAYER_A, 2);
        innerBoard.dropPiece(GridType.PLAYER_B, 3);
        innerBoard.dropPiece(GridType.PLAYER_A, 3);
        innerBoard.dropPiece(GridType.PLAYER_B, 2);
        innerBoard.dropPiece(GridType.PLAYER_A, 3);
        innerBoard.dropPiece(GridType.PLAYER_B, 2);
        innerBoard.dropPiece(GridType.PLAYER_A, 3);
        innerBoard.dropPiece(GridType.PLAYER_B, 2);
        innerBoard.dropPiece(GridType.PLAYER_A, 4);
        innerBoard.dropPiece(GridType.PLAYER_B, 5);
        innerBoard.dropPiece(GridType.PLAYER_A, 4);
        innerBoard.dropPiece(GridType.PLAYER_B, 5);
        innerBoard.dropPiece(GridType.PLAYER_A, 4);
        innerBoard.dropPiece(GridType.PLAYER_B, 5);
        innerBoard.dropPiece(GridType.PLAYER_A, 5);
        innerBoard.dropPiece(GridType.PLAYER_B, 4);
        innerBoard.dropPiece(GridType.PLAYER_A, 5);
        innerBoard.dropPiece(GridType.PLAYER_B, 4);
        innerBoard.dropPiece(GridType.PLAYER_A, 5);
        innerBoard.dropPiece(GridType.PLAYER_B, 4);
        innerBoard.dropPiece(GridType.PLAYER_A, 6);
        innerBoard.dropPiece(GridType.PLAYER_B, 6);
        innerBoard.dropPiece(GridType.PLAYER_A, 6);
        innerBoard.dropPiece(GridType.PLAYER_B, 6);
        innerBoard.dropPiece(GridType.PLAYER_A, 6);
        innerBoard.dropPiece(GridType.PLAYER_B, 6);
    }

}
