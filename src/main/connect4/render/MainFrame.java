package connect4.render;

import connect4.player.PlayerBMode;
import connect4.render.view.AboutView;
import connect4.render.view.GameView;
import connect4.render.view.SelectView;
import connect4.render.view.WelcomeView;
import org.hexworks.zircon.api.component.Button;
import org.hexworks.zircon.api.grid.TileGrid;
import org.hexworks.zircon.api.uievent.MouseEventType;
import org.hexworks.zircon.api.uievent.UIEventResponse;
import org.hexworks.zircon.api.view.base.BaseView;

/**
 * Main frame of app
 *
 * The entry of swing renderer
 *
 * @author yang
 */
public class MainFrame {
    WelcomeView welcome;
    AboutView about;
    GameView game;
    SelectView select;

    public MainFrame() {
        TileGrid tileGrid = UiGlobal.startTileGrid("Connect-4");

        welcome = new WelcomeView(tileGrid, UiGlobal.THEME_ORIGIN);
        about = new AboutView(tileGrid, UiGlobal.THEME_ORIGIN);
        select = new SelectView(tileGrid, UiGlobal.THEME_ORIGIN);
        game = new GameView(tileGrid, UiGlobal.THEME_ORIGIN);
    }

    private void install() {
        clickAtThen(welcome.newGameButton, select);
        clickAtThen(select.backButton, welcome);

        clickAtThen(game.backButton, welcome);

        clickAtThen(welcome.aboutButton, about);
        clickAtThen(about.backButton, welcome);

        welcome.exitButton.handleMouseEvents(MouseEventType.MOUSE_RELEASED, ((event, phase) -> {
            System.exit(0);
            return UIEventResponse.processed();
        }));

        select.startButton.handleMouseEvents(MouseEventType.MOUSE_RELEASED, ((event, phase) -> {
            if (select.group.getSelectedButton().isEmpty()) {
                System.out.println("Pass");
                return UIEventResponse.pass();
            }
            String key = select.group.getSelectedButton().get().getKey();
            switch (key) {
                case "a":
                    game.loadGame(PlayerBMode.Human);
                    break;
                case "b":
                    game.loadGame(PlayerBMode.RNG);
                    break;
                case "c":
                    game.loadGame(PlayerBMode.MiniMax);
                    break;
                default:
            }
            game.dock();
            return UIEventResponse.processed();
        }));
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