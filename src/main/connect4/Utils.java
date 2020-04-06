package connect4;

import java.util.Random;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @author yang
 */
public class Utils {

    public static ExecutorService service = Executors.newSingleThreadExecutor();

    public static int randomInt(int upperRange) {
        Random random = new Random();
        return random.nextInt(upperRange);
    }

    public static String strCenter(String s, int size) {
        return strCenter(s, size, ' ');
    }

    public static String strCenter(String s, int size, char pad) {
        if (s == null || size <= s.length()) {
            return s;
        }

        StringBuilder sb = new StringBuilder(size);
        for (int i = 0; i < (size - s.length()) >> 1; ++i) {
            sb.append(pad);
        }
        sb.append(s);
        while (sb.length() < size) {
            sb.append(pad);
        }
        return sb.toString();
    }
}
