package connect4.render.view;

import connect4.Utils;
import connect4.render.UiGlobal;
import org.hexworks.zircon.api.Fragments;
import org.hexworks.zircon.api.component.*;
import org.hexworks.zircon.api.fragment.MultiSelect;
import org.hexworks.zircon.api.graphics.BoxType;
import org.hexworks.zircon.api.grid.TileGrid;
import org.hexworks.zircon.api.view.base.BaseView;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.Arrays;

import static connect4.render.UiGlobal.*;
import static org.hexworks.zircon.api.ComponentDecorations.box;
import static org.hexworks.zircon.api.Components.label;
import static org.hexworks.zircon.api.Components.vbox;
import static org.hexworks.zircon.api.Functions.fromBiConsumer;
import static org.hexworks.zircon.api.color.ANSITileColor.BRIGHT_GREEN;
import static org.hexworks.zircon.api.color.ANSITileColor.BRIGHT_RED;

/**
 * View: Select Char
 *
 * @author yang
 */

public class SelectCharView extends BaseView {

    private static final int VBOX_WIDTH = 23;
    private static final int VBOX_HEIGHT = 7;

    private static final int VBOX_PAD = 1;
    private static final int VBOX_DISPLAY_WIDTH = VBOX_WIDTH - 2 * VBOX_PAD;

    private static final int SELECTOR_WIDTH = 5;
    private static final int SELECTOR_PAD = (VBOX_WIDTH - SELECTOR_WIDTH) >> 1;

    public final Button backButton;
    public final Button startButton;

    private final Label playerA;
    private final Label playerB;

    public SelectCharView(@NotNull TileGrid tileGrid, @NotNull ColorTheme theme) {
        super(tileGrid, theme);

        backButton = UiGlobal.makeColoredButton("BACK", BRIGHT_RED)
                .withAlignmentWithin(getScreen(), ComponentAlignment.BOTTOM_RIGHT)
                .build();

        getScreen().addComponent(backButton);

        int padTop = (UiGlobal.FRAME_HEIGHT - VBOX_HEIGHT) / 2;
        int padLeft = (UiGlobal.FRAME_WIDTH - VBOX_WIDTH) / 2;

        VBox container = vbox().withSize(VBOX_WIDTH, VBOX_HEIGHT)
                .withPosition(padLeft, padTop).build();

        getScreen().addComponent(container);

        container.addComponent(label()
                .withText(Utils.strCenter("CHARACTER OF A:",
                        VBOX_DISPLAY_WIDTH, '>', '<'))
                .withPosition(1, VBOX_PAD)
                .build());


        MultiSelect<Character> charSelectorA = Fragments.multiSelect(SELECTOR_WIDTH,
                new ArrayList<>(Arrays.asList(OPTIONAL_CHARS)))
                .withDefaultSelected(charPlayerA)
                .withCallback(fromBiConsumer((oldChar, newChar) -> {
                    charPlayerA = newChar;
                    updateComponents();
                }))
                .withPosition(SELECTOR_PAD, 1).build();
        container.addFragment(charSelectorA);

        container.addComponent(label()
                .withText(Utils.strCenter("CHARACTER OF B:",
                        VBOX_DISPLAY_WIDTH, '>', '<'))
                .withPosition(1, 2)
                .build());

        MultiSelect<Character> charSelectorB = Fragments.multiSelect(SELECTOR_WIDTH,
                new ArrayList<>(Arrays.asList(OPTIONAL_CHARS)))
                .withDefaultSelected(charPlayerB)
                .withCallback(fromBiConsumer((oldChar, newChar) -> {
                    charPlayerB = newChar;
                    updateComponents();
                }))
                .withPosition(SELECTOR_PAD, 2).build();
        container.addFragment(charSelectorB);

        startButton = UiGlobal.makeColoredButton("GO→", BRIGHT_GREEN)
                .withAlignmentAround(container, ComponentAlignment.BOTTOM_CENTER)
                .build();

        getScreen().addComponent(startButton);

        playerA = label()
                .withText(" ")
                .withDecorations(box(BoxType.SINGLE))
                .withSize(UNIT_SIZE, UNIT_SIZE)
                .withComponentStyleSet(colorToFontStyle(PLAYER_A_COLOR))
                .withPosition(padLeft, padTop - UNIT_SIZE)
                .build();

        getScreen().addComponent(label().withText("<<A VS. B>>").withAlignmentAround(container,
                ComponentAlignment.TOP_CENTER).withDecorations(box(BoxType.LEFT_RIGHT_DOUBLE)).build());

        playerB = label()
                .withText(" ")
                .withDecorations(box(BoxType.SINGLE))
                .withSize(UNIT_SIZE, UNIT_SIZE)
                .withComponentStyleSet(colorToFontStyle(PLAYER_B_COLOR))
                .withPosition(padLeft + VBOX_WIDTH - UNIT_SIZE, padTop - UNIT_SIZE)
                .build();
        getScreen().addComponents(playerA, playerB);
        updateComponents();
    }

    private void updateComponents() {
        playerA.setText(String.valueOf(charPlayerA));
        playerB.setText(String.valueOf(charPlayerB));
    }

    @Override
    public void onDock() {
        Utils.logger.fine("Switch to Select Char page.");
    }

    @Override
    public void onUndock() {
        Utils.logger.fine("Unload Select Char page.");
    }
}
