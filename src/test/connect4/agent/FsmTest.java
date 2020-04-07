package connect4.agent;

import connect4.BVT;
import connect4.EC;
import connect4.Options;
import connect4.board.GridType;
import connect4.player.BasePlayer;
import connect4.player.HumanPlayer;
import connect4.player.computerplayer.RandomComputerPlayer;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.experimental.categories.Category;

import static org.junit.Assert.assertEquals;

public class FsmTest {
    Agent testAgent;

    @Before
    public void setUp() throws Exception {

    }

    @After
    public void tearDown() throws Exception {
        if (testAgent != null) {
            testAgent.stop();
        }
        testAgent = null;
    }

    private void init(BasePlayer a, BasePlayer b) {
        testAgent = new Agent(a, b);
        testAgent.initBoard();
        testAgent.initState();
        testAgent.initPlayer();
    }

    @Category({BVT.class})
    @Test
    public void testStartState01() {
        // 测试初始状态
        // -> READY, A
        init(new HumanPlayer(), new RandomComputerPlayer());
        assertEquals(testAgent.getActivePlayer(), GridType.PLAYER_A);
        assertEquals(testAgent.getState(), AgentState.READY);
    }

    @Category({BVT.class, EC.class})
    @Test
    public void testStateTrans02() {
        // 测试初始由Human的转移
        // -> WAITING_HUMAN, A
        init(new HumanPlayer(), new RandomComputerPlayer());
        testAgent.start();
        assertEquals(testAgent.getActivePlayer(), GridType.PLAYER_A);
        assertEquals(testAgent.getState(), AgentState.WAITING_HUMAN);
    }

    @Category({BVT.class, EC.class})
    @Test
    public void testStateTrans03() {
        // 测试初始由Computer的转移
        // -> WAITING_COMPUTER
        init(new RandomComputerPlayer(), new RandomComputerPlayer());
        testAgent.start();
        assertEquals(testAgent.getActivePlayer(), GridType.PLAYER_A);
        assertEquals(testAgent.getState(), AgentState.WAITING_COMPUTER);
    }

    @Category({BVT.class, EC.class})
    @Test
    public void testStateTrans04() {
        // 测试等待Computer时触发report的转移，未获胜
        // -> WAITING_HUMAN, B
        init(new RandomComputerPlayer(), new HumanPlayer());
        testAgent.start();
        testAgent.reportInput(0);
        assertEquals(testAgent.getActivePlayer(), GridType.PLAYER_B);
        assertEquals(testAgent.getState(), AgentState.WAITING_HUMAN);
    }

    @Category({BVT.class, EC.class})
    @Test
    public void testStateTrans05() {
        // 测试等待Computer时触发report的转移，获胜
        // -> WIN, A
        init(new RandomComputerPlayer(), new HumanPlayer());
        for (int i = 0; i < 3; ++i) {
            testAgent.reportInput(i);
            testAgent.reportInput(i);
        }
        assertEquals(testAgent.getActivePlayer(), GridType.PLAYER_A);
        assertEquals(testAgent.getState(), AgentState.WAITING_COMPUTER);

        testAgent.reportInput(3);
        assertEquals(testAgent.getActivePlayer(), GridType.PLAYER_A);
        assertEquals(testAgent.getState(), AgentState.WIN);
    }

    @Category({BVT.class, EC.class})
    @Test
    public void testStateTrans06() {
        // 测试等待Human时触发report的转移，未获胜
        // -> WAITING_COMPUTER, B
        init(new HumanPlayer(), new RandomComputerPlayer());
        testAgent.start();
        testAgent.reportInput(0);
        assertEquals(testAgent.getActivePlayer(), GridType.PLAYER_B);
        assertEquals(testAgent.getState(), AgentState.WAITING_COMPUTER);
    }

    @Category({BVT.class, EC.class})
    @Test
    public void testStateTrans07() {
        // 测试等待Human时触发report的转移，获胜
        // -> WIN, A
        init(new HumanPlayer(), new RandomComputerPlayer());
        for (int i = 0; i < 3; ++i) {
            testAgent.reportInput(i);
            testAgent.reportInput(i);
        }
        assertEquals(testAgent.getActivePlayer(), GridType.PLAYER_A);
        assertEquals(testAgent.getState(), AgentState.WAITING_HUMAN);

        testAgent.reportInput(3);
        assertEquals(testAgent.getActivePlayer(), GridType.PLAYER_A);
        assertEquals(testAgent.getState(), AgentState.WIN);
    }


    @Category({BVT.class, EC.class})
    @Test
    public void testStateHold08() {
        // 测试胜利后的转移
        // WIN -> WIN
        init(new HumanPlayer(), new RandomComputerPlayer());
        for (int i = 0; i < 3; ++i) {
            testAgent.reportInput(i);
            testAgent.reportInput(i);
        }
        testAgent.reportInput(3);
        assertEquals(testAgent.getActivePlayer(), GridType.PLAYER_A);
        assertEquals(testAgent.getState(), AgentState.WIN);
        testAgent.pseudoStepInto();
        assertEquals(testAgent.getActivePlayer(), GridType.PLAYER_A);
        assertEquals(testAgent.getState(), AgentState.WIN);
    }


    @Category({BVT.class, EC.class})
    @Test
    public void testStateTrans09() {
        // 测试棋盘占满却没有胜利者时的转移
        // -> NO_WIN
        init(new HumanPlayer(), new RandomComputerPlayer());
        for (int i = 0; i < Options.BOARD_COLUMNS * Options.BOARD_ROWS; i++) {
            testAgent.getBoard().leftGridsDec();
        }
        testAgent.pseudoStepInto();
        assertEquals(AgentState.NO_WIN, testAgent.getState());
    }

    @Category({BVT.class, EC.class})
    @Test
    public void testStateHold10() {
        // 测试无胜利者后的转移
        // NO_WIN -> NO_WIN
        init(new HumanPlayer(), new RandomComputerPlayer());
        testAgent.pseudoSetState(AgentState.NO_WIN, testAgent.getActivePlayer());
        assertEquals(AgentState.NO_WIN, testAgent.getState());
        testAgent.pseudoStepInto();
        assertEquals(AgentState.NO_WIN, testAgent.getState());
    }
}

