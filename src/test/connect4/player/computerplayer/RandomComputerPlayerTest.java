package connect4.player.computerplayer;

import static org.junit.Assert.fail;

import connect4.BVT;
import connect4.EC;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.experimental.categories.Category;

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
    
    @Category({EC.class, BVT.class})
    @Test
    public void testAskNextMinv1() {
        //空盘时下一子随机出现在可能的7个位置中的1个

    }

    @Category({BVT.class})
    @Test
    public void testAskNextMinv2() {
        //落一子时下一子随机出现在可能的7个位置中的1个

    }

    @Category({BVT.class})
    @Test
    public void testAskNextMaxv1() {
        //剩一子时下一子出现在所剩的1个位置

    }

    @Category({BVT.class})
    @Test
    public void testAskNextMaxv2() {
        //满盘时下一子不出现

    }

}
