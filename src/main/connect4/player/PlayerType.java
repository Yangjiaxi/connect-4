package connect4.player;

/**
 * @author yang
 * User select
 */
public enum PlayerType {
    // PVP
    Human,
    /**
     * PVC
     * Computer's decision is generated by
     * `Random.nextInt()`
     */
    RNG,
    /**
     * Not started yet
     * Generate by algorithm
     * a little bit smart
     */
    MiniMaxEasy,
    MiniMaxNormal,
    MiniMaxHard,
}
