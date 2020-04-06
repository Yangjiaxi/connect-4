package connect4.agent;

/**
 * @author yang
 * <p>
 * State of Agent,
 * key component of app's logic,
 * also is the render controller of VIEW
 */

public enum AgentState {
    /** When state machine get triggered
     *  or the other player's move is finished
     *
     *  READY means the app ready to receive the input,
     *  both from UI or from `askNext()`
    */
    READY,
    /**
     * WAITING `HumanPlayer` select input from UI
     */
    WAITING,
    /**
     * Winner has been judged
     * No input is accepted anymore
     */
    WIN,
    /**
     * Chess board is full but...
     * No input is accepted anymore
     */
    NO_WIN,
}
