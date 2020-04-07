package connect4.player.computerplayer;

import static org.junit.Assert.fail;

import connect4.BVT;
import connect4.EC;
import org.junit.After;
import org.junit.Before;
import org.junit.Ignore;
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
    public void testAskNextType1_1() {
        //空盘时下一子随机出现在可能的7个位置中的1个

    }

    @Category({BVT.class})
    @Test
    public void testAskNextType1_2() {
        //仅落一子时下一子随机出现在可能的7个位置中的1个

    }

    @Category({BVT.class})
    @Test
    public void testAskNextType1_3() {
        //有1列差一子满且其余列空时下一子随机出现在可能的7个位置中的1个

    }

    @Category({EC.class, BVT.class})
    @Test
    public void testAskNextType2_1() {
        //有1列满且其余列空时下一子随机出现在可能的6个位置中的1个

    }

    @Category({BVT.class})
    @Test
    public void testAskNextType2_2() {
        //有1列满且其余列落一子时下一子随机出现在可能的6个位置中的1个

    }

    @Category({BVT.class})
    @Test
    public void testAskNextType2_3() {
        //有2列差一子满且其余列空时下一子随机出现在可能的6个位置中的1个

    }

    @Category({EC.class, BVT.class})
    @Test
    public void testAskNextType3_1() {
        //有2列满且其余列空时下一子随机出现在可能的5个位置中的1个

    }

    @Category({BVT.class})
    @Test
    public void testAskNextType3_2() {
        //有2列满且其余列落一子时下一子随机出现在可能的5个位置中的1个

    }

    @Category({BVT.class})
    @Test
    public void testAskNextType3_3() {
        //有3列差一子满且其余列空时下一子随机出现在可能的5个位置中的1个

    }

    @Category({EC.class, BVT.class})
    @Test
    public void testAskNextType4_1() {
        //有3列满且其余列空时下一子随机出现在可能的4个位置中的1个

    }

    @Category({BVT.class})
    @Test
    public void testAskNextType4_2() {
        //有3列满且其余列落一子时下一子随机出现在可能的4个位置中的1个

    }

    @Category({BVT.class})
    @Test
    public void testAskNextType4_3() {
        //有4列差一子满且其余列空时下一子随机出现在可能的4个位置中的1个

    }

    @Category({EC.class, BVT.class})
    @Test
    public void testAskNextType5_1() {
        //有4列满且其余列空时下一子随机出现在可能的3个位置中的1个

    }

    @Category({BVT.class})
    @Test
    public void testAskNextType5_2() {
        //有4列满且其余列落一子时下一子随机出现在可能的3个位置中的1个

    }

    @Category({BVT.class})
    @Test
    public void testAskNextType5_3() {
        //有5列差一子满且其余列空时下一子随机出现在可能的3个位置中的1个

    }

    @Category({EC.class, BVT.class})
    @Test
    public void testAskNextType6_1() {
        //有5列满且其余列空时下一子随机出现在可能的2个位置中的1个

    }

    @Category({BVT.class})
    @Test
    public void testAskNextType6_2() {
        //有5列满且其余列落一子时下一子随机出现在可能的2个位置中的1个

    }

    @Category({BVT.class})
    @Test
    public void testAskNextType6_3() {
        //有6列差一子满且其余列空时下一子随机出现在可能的2个位置中的1个

    }

    @Category({EC.class, BVT.class})
    @Test
    public void testAskNextType7_1() {
        //有6列满且其余列空时下一子随机出现在确定的位置（未满列第一行）

    }

    @Test
    public void testAskNextType7_2() {
        //有6列满且其余列落一子时下一子随机出现在确定的位置（未满列第二行）

    }

    @Test
    public void testAskNextType7_3() {
        //有6列满且其余列落二子时下一子随机出现在确定的位置（未满列第三行）

    }

    @Test
    public void testAskNextType7_4() {
        //有6列满且其余列落三子时下一子随机出现在确定的位置（未满列第四行）

    }

    @Test
    public void testAskNextType7_5() {
        //有6列满且其余列落四子时下一子随机出现在确定的位置（未满列第五行）

    }

    @Category({BVT.class})
    @Test
    public void testAskNextType7_6() {
        //有6列满且其余列落五子时下一子随机出现在确定的位置（未满列第六行）

    }

    @Category({EC.class, BVT.class})
    @Test
    public void testAskNextType8() {
        //满盘时下一子不出现

    }

}
