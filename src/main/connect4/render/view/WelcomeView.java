package connect4.render.view;

import connect4.Utils;
import connect4.render.UiGlobal;
import org.hexworks.zircon.api.Components;
import org.hexworks.zircon.api.component.Button;
import org.hexworks.zircon.api.component.ColorTheme;
import org.hexworks.zircon.api.component.ComponentAlignment;
import org.hexworks.zircon.api.component.TextBox;
import org.hexworks.zircon.api.graphics.BoxType;
import org.hexworks.zircon.api.grid.TileGrid;
import org.hexworks.zircon.api.view.base.BaseView;
import org.jetbrains.annotations.NotNull;

import static org.hexworks.zircon.api.ComponentDecorations.box;
import static org.hexworks.zircon.api.color.ANSITileColor.*;

/**
 * View: Welcome
 *
 * @author yang
 */

public class WelcomeView extends BaseView {
    public Button newGameButton;
    public Button aboutButton;
    public Button exitButton;

    public WelcomeView(@NotNull TileGrid tileGrid, @NotNull ColorTheme theme) {
        super(tileGrid, theme);

        String content = "~~~ CONNECT-4 ~~~";
        TextBox logo = Components.textBox(content.length())
                .withDecorations(box(BoxType.LEFT_RIGHT_DOUBLE))
                .addNewLine()
                .addHeader(content, false)
                .withAlignmentWithin(getScreen(), ComponentAlignment.TOP_CENTER)
                .build();

        TextBox teach = Components.textBox(14)
                .withDecorations(box(BoxType.TOP_BOTTOM_DOUBLE))
                .addHeader("* HOW TO PLAY")
                .addListItem("TWO PLAYERS")
                .addListItem("TAKE TURN")
                .addNewLine()
                .addNewLine()
                .addParagraph("* OBJECTIVE")
                .addParagraph("FOUR DISCS OF YOUR OWN IN THE SAME DIRECTION.", false)
                .addNewLine()
                .withPosition(9, 6)
                .build();

        newGameButton = UiGlobal.makeColoredButton("START", BRIGHT_GREEN)
                .withPosition(2, 8)
                .build();

        aboutButton = UiGlobal.makeColoredButton("ABOUT", BRIGHT_YELLOW)
                .withPosition(2, 11)
                .build();

        exitButton = UiGlobal.makeColoredButton("LEAVE", BRIGHT_RED)
                .withPosition(2, 17)
                .build();

        getScreen().addComponent(logo);
        getScreen().addComponent(newGameButton);
        getScreen().addComponent(aboutButton);
        getScreen().addComponent(exitButton);
        getScreen().addComponent(teach);
        getScreen().addComponent(Components.label().withText("C1703 G4 2020.04")
                .withAlignmentWithin(getScreen(), ComponentAlignment.BOTTOM_RIGHT)
                .build());
    }

    @Override
    public void onDock() {
        Utils.logger.info("Switch to Welcome page.");
    }

    @Override
    public void onUndock() {
        Utils.logger.info("Unload Welcome page.");
    }
}
