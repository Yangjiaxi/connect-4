package connect4;

import connect4.render.MainFrame;

/**
 * MAIN ENTRY
 *
 * @author yang
 */
public class Main {
    public static void main(String[] args) {
        MainFrame frame = new MainFrame();
        frame.run();

        System.out.println("Process Exit.");
    }
}