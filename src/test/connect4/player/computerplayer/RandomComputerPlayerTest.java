package connect4.player.computerplayer;

import static org.junit.Assert.fail;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class RandomComputerPlayerTest {
	RandomComputerPlayer testobj;
	
	@Before
    public void setUp() throws Exception {
		testobj = new RandomComputerPlayer();
    }

    @After
    public void tearDown() throws Exception {
    	//释放对象
    	testobj = null;
    }
    
    @Test
    public void test() {
    	fail("test method incompleted...");
    }
}
