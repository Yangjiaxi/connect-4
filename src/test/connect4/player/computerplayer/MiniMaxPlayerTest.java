package connect4.player.computerplayer;

import connect4.BVT;
import connect4.EC;
import org.junit.Test;
import org.junit.experimental.categories.Category;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

/**
 * 对数组打乱操作的测试
 * @author yang
 */
public class MiniMaxPlayerTest {
    @Category({BVT.class})
    @Test
    public void testShuffleArray00() {
        // 只有一个长度的数组打乱
        int[] test = MiniMaxPlayer.shuffleArray(1);
        assertEquals(1, test.length);
        assertEquals(0, test[0]);
    }

    @Category({BVT.class})
    @Test(expected = IndexOutOfBoundsException.class)
    public void testShuffleArray01() {
        // 空的数组打乱
        int[] test = MiniMaxPlayer.shuffleArray(0);
        assertEquals(0, test.length);
        assertEquals(0, test[0]);
    }

    private boolean fullCoverage(int[] arr) {
        int n = arr.length;
        boolean[] all = new boolean[n];
        for (int a : arr) {
            all[a] = true;
        }
        for (boolean b : all) {
            if (!b) return false;
        }
        return true;
    }

    private boolean isShuffled(int[] arr) {
        int n = arr.length;
        for (int i = 0; i < n; ++i) {
            if (i != arr[i]) return true;
        }
        return false;
    }

    @Category({EC.class})
    @Test
    public void testShuffleArray02() {
        // 含有两个元素的数组被打乱
        int[] test = MiniMaxPlayer.shuffleArray(2);
        assertEquals(2, test.length);
        assertTrue(fullCoverage(test));

        // 两个元素的数组要求每次都被打乱有点难
        // assertTrue(isShuffled(test));
    }

    @Category({EC.class})
    @Test
    public void testShuffleArray03() {
        // 含有两个元素的数组被打乱
        int[] test = MiniMaxPlayer.shuffleArray(3);
        assertEquals(3, test.length);

        assertTrue(fullCoverage(test));
        assertTrue(isShuffled(test));
    }

    @Category({EC.class})
    @Test
    public void testShuffleArray04() {
        // 含有两个元素的数组被打乱
        int[] test = MiniMaxPlayer.shuffleArray(4);
        assertEquals(4, test.length);

        assertTrue(fullCoverage(test));
        assertTrue(isShuffled(test));
    }

    @Category({EC.class})
    @Test
    public void testShuffleArray05() {
        // 含有两个元素的数组被打乱
        int[] test = MiniMaxPlayer.shuffleArray(5);
        assertEquals(5, test.length);

        assertTrue(fullCoverage(test));
        assertTrue(isShuffled(test));
    }

    @Category({EC.class})
    @Test
    public void testShuffleArray06() {
        // 含有两个元素的数组被打乱
        int[] test = MiniMaxPlayer.shuffleArray(6);
        assertEquals(6, test.length);

        assertTrue(fullCoverage(test));
        assertTrue(isShuffled(test));
    }

    @Category({EC.class, BVT.class})
    @Test
    public void testShuffleArray07() {
        // 含有两个元素的数组被打乱
        int[] test = MiniMaxPlayer.shuffleArray(7);
        assertEquals(7, test.length);

        assertTrue(fullCoverage(test));
        assertTrue(isShuffled(test));
    }
}
