package connect4;

import java.util.Random;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @author yang
 */
public class Utils {
    public static ExecutorService service = Executors.newFixedThreadPool(2);

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
