package connect4;

/**
 * Gaming options
 *
 * @author yang
 */
public class Options {
    public static final int BOARD_ROWS = 6;
    public static final int BOARD_COLUMNS = 7;
    public static final int GOAL_TO_WIN = 4;

    public static int COMPUTE_SLEEP_TIME_MIN_MS = 300;
    public static int COMPUTE_SLEEP_TIME_MAX_MS = 1000;
    public static int COMPUTE_SLEEP_TIME_RANDOM_MS = COMPUTE_SLEEP_TIME_MAX_MS - COMPUTE_SLEEP_TIME_MIN_MS;

    public static int EASY_AI_DEPTH = 2;
    public static int NORMAL_AI_DEPTH = 5;
    public static int HARD_AI_DEPTH = 10;


    public static double EASY_AI_ERROR_RATE = 0.3;
    public static double NORMAL_AI_ERROR_RATE = 0.1;
    public static double HARD_AI_ERROR_RATE = 0.05;
}
