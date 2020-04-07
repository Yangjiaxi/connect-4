package connect4;

import java.util.Random;
import java.util.concurrent.*;

import com.google.common.util.concurrent.ThreadFactoryBuilder;

/**
 * @author yang
 */
public class Utils {
    private static final ThreadFactory NAMED_THREAD_FACTORY = new ThreadFactoryBuilder()
            .setNameFormat("Thread-pool-%d").build();

    public static ExecutorService service = new ThreadPoolExecutor(2, 5,
            0L, TimeUnit.MILLISECONDS,
            new LinkedBlockingQueue<>(1024), NAMED_THREAD_FACTORY, new ThreadPoolExecutor.AbortPolicy());

    public static int randomInt(int upperRange) {
        Random random = new Random();
        return random.nextInt(upperRange);
    }

    public static String strCenter(String s, int size) {
        return strCenter(s, size, ' ', ' ');
    }

    public static String strCenter(String s, int size, char pad) {
        return strCenter(s, size, pad, pad);
    }


    public static String strCenter(String s, int size, char leftPad, char rightPad) {
        if (s == null || size <= s.length()) {
            return s;
        }

        StringBuilder sb = new StringBuilder(size);
        for (int i = 0; i < (size - s.length()) >> 1; ++i) {
            sb.append(leftPad);
        }
        sb.append(s);
        while (sb.length() < size) {
            sb.append(rightPad);
        }
        return sb.toString();
    }
}
