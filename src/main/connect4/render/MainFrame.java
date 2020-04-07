package connect4.render;

import connect4.player.PlayerType;
import connect4.render.view.*;
import org.hexworks.zircon.api.component.Button;
import org.hexworks.zircon.api.grid.TileGrid;
import org.hexworks.zircon.api.uievent.MouseEventType;
import org.hexworks.zircon.api.uievent.UIEventResponse;
import org.hexworks.zircon.api.view.base.BaseView;

/**
 * Main frame of app
 * <p>
 * The entry of swing renderer
 *
 * @author yang
 */
public class MainFrame {
    WelcomeView welcome;
    AboutView about;
    GameView game;
    SelectPlayerView player;
    SelectCharView character;

    public MainFrame() {
        TileGrid tileGrid = UiGlobal.startTileGrid("Connect-4");

        welcome = new WelcomeView(tileGrid, UiGlobal.THEME_ORIGIN);
        about = new AboutView(tileGrid, UiGlobal.THEME_ORIGIN);
        player = new SelectPlayerView(tileGrid, UiGlobal.THEME_ORIGIN);
        character = new SelectCharView(tileGrid, UiGlobal.THEME_ORIGIN);
        game = new GameView(tileGrid, UiGlobal.THEME_ORIGIN);
    }

    private void install() {
        // Welcome -[Start]-> Select Char
        clickAtThen(welcome.newGameButton, character);
        // Select Char -[Back]-> Welcome
        clickAtThen(character.backButton, welcome);
        //Select Char -[Start]-> Select Player
        clickAtThen(character.startButton, player);
        // Select Player -[Back]-> Welcome
        clickAtThen(player.backButton, welcome);
        // Game -[Back]-> Welcome
        clickAtThen(game.backButton, welcome);
        // Welcome -[About]-> About Us
        clickAtThen(welcome.aboutButton, about);
        // About -[Back]-> Welcome
        clickAtThen(about.backButton, welcome);
        // Welcome -[Exit]->
        welcome.exitButton.handleMouseEvents(MouseEventType.MOUSE_RELEASED, ((event, phase) -> {
            System.exit(0);
            return UIEventResponse.processed();
        }));
        // Select Player -[Start]-> Game
        player.startButton.handleMouseEvents(MouseEventType.MOUSE_RELEASED, ((event, phase) -> {
            if (player.groupA.getSelectedButton().isEmpty() || player.groupB.getSelectedButton().isEmpty()) {
                System.out.println("Pass");
                return UIEventResponse.pass();
            }
            String keyA = player.groupA.getSelectedButton().get().getKey();
            String keyB = player.groupB.getSelectedButton().get().getKey();
            game.loadGame(keyToType(keyA), keyToType(keyB));
            game.dock();
            return UIEventResponse.processed();
        }));
    }

    private PlayerType keyToType(String key) {
        switch (key) {
            case "a":
                return PlayerType.Human;
            case "b":
                return PlayerType.RNG;
            case "c":
                return PlayerType.MiniMax;
            default:
        }
        return PlayerType.Human;
    }

    public void clickAtThen(Button obj, BaseView target) {
        obj.handleMouseEvents(MouseEventType.MOUSE_RELEASED, ((event, phase) -> {
            target.dock();
            return UIEventResponse.processed();
        }));
    }

    public void run() {
        install();
        welcome.dock();
    }
}