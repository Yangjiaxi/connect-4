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
}
