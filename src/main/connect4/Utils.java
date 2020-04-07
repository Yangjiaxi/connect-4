package connect4;

import java.util.Random;
import java.util.concurrent.*;
import java.util.logging.Logger;

import com.google.common.util.concurrent.ThreadFactoryBuilder;

/**
 * @author yang
 */
public class Utils {
    public static final Logger logger = Logger.getLogger("Connect-4");

    public static ExecutorService service = new ThreadPoolExecutor(2, 5, 0L,
            TimeUnit.MILLISECONDS, new LinkedBlockingQueue<>(1024), new ThreadFactoryBuilder()
            .setNameFormat("Thread-pool-%d").build(), new ThreadPoolExecutor.AbortPolicy());

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
