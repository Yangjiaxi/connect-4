package connect4.render.view;

import connect4.Utils;
import connect4.render.UiGlobal;
import org.hexworks.zircon.api.Components;
import org.hexworks.zircon.api.component.*;
import org.hexworks.zircon.api.graphics.BoxType;
import org.hexworks.zircon.api.grid.TileGrid;
import org.hexworks.zircon.api.view.base.BaseView;
import org.jetbrains.annotations.NotNull;

import static org.hexworks.zircon.api.ComponentDecorations.box;
import static org.hexworks.zircon.api.Components.*;
import static org.hexworks.zircon.api.color.ANSITileColor.BRIGHT_GREEN;
import static org.hexworks.zircon.api.color.ANSITileColor.BRIGHT_RED;

/**
 * View: Select Player B
 *
 * @author yang
 */

public class SelectPlayerView extends BaseView {

    public Button backButton;
    public Button startButton;
    public RadioButtonGroup groupA, groupB;

    public SelectPlayerView(@NotNull TileGrid tileGrid, @NotNull ColorTheme theme) {
        super(tileGrid, theme);

        String content = "~~ CHOOSE A&B ~~";
        TextBox logo = Components.textBox(content.length())
                .withDecorations(box(BoxType.LEFT_RIGHT_DOUBLE))
                .addNewLine()
                .addHeader(content, false)
                .withAlignmentWithin(getScreen(), ComponentAlignment.TOP_CENTER)
                .build();

        backButton = UiGlobal.makeColoredButton("BACK", BRIGHT_RED)
                .withAlignmentWithin(getScreen(), ComponentAlignment.BOTTOM_RIGHT)
                .build();

        getScreen().addComponent(logo);
        getScreen().addComponent(backButton);

        VBox playerA = vbox()
                .withSize(14, 6)
                .withAlignmentAround(logo, ComponentAlignment.BOTTOM_CENTER)
                .withDecorations(box(BoxType.SINGLE))
                .build();

        RadioButton a1 = radioButton()
                .withText("HUMAN")
                .withKey("a")
                .build();
        RadioButton b1 = radioButton()
                .withText("RANDOM")
                .withKey("b")
                .build();
        RadioButton c1 = radioButton()
                .withText("AI+")
                .withKey("d")
                .build();

        playerA.addComponent(label().withText("Player A:"));
        playerA.addComponents(a1, b1, c1);

        groupA = radioButtonGroup().build();
        groupA.addComponents(a1, b1, c1);
        a1.setSelected(true);

        getScreen().addComponent(playerA);

        VBox playerB = vbox()
                .withSize(14, 8)
                .withDecorations(box(BoxType.SINGLE))
                .withPosition(playerA.getPosition().withRelativeY(playerA.getHeight() + 1))
                .build();

        RadioButton a2 = radioButton()
                .withText("HUMAN")
                .withKey("a")
                .build();
        RadioButton b2 = radioButton()
                .withText("RANDOM")
                .withKey("b")
                .build();
        RadioButton c2 = radioButton()
                .withText("AI")
                .withKey("c")
                .build();
        RadioButton d2 = radioButton()
                .withText("AI+")
                .withKey("d")
                .build();
        RadioButton e2 = radioButton()
                .withText("AI++")
                .withKey("e")
                .build();

        playerB.addComponent(label().withText("Player B:"));
        playerB.addComponents(a2, b2, c2, d2, e2);

        groupB = radioButtonGroup().build();
        groupB.addComponents(a2, b2, c2, d2, e2);
        a2.setSelected(true);

        getScreen().addComponent(playerB);

        startButton = UiGlobal.makeColoredButton("GO→", BRIGHT_GREEN)
                .withAlignmentAround(playerB, ComponentAlignment.BOTTOM_CENTER)
                .build();

        getScreen().addComponent(startButton);
    }

    @Override
    public void onDock() {
        Utils.logger.fine("Switch to Select Player page.");
    }

    @Override
    public void onUndock() {
        Utils.logger.fine("Unload Select Player page.");
    }
}
