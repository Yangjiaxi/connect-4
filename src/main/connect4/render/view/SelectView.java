package connect4.render.view;

import connect4.render.UIGlobal;
import org.hexworks.zircon.api.Components;
import org.hexworks.zircon.api.Maybes;
import org.hexworks.zircon.api.component.*;
import org.hexworks.zircon.api.graphics.BoxType;
import org.hexworks.zircon.api.grid.TileGrid;
import org.hexworks.zircon.api.view.base.BaseView;
import org.jetbrains.annotations.NotNull;

import static org.hexworks.zircon.api.ComponentDecorations.box;
import static org.hexworks.zircon.api.ComponentDecorations.shadow;
import static org.hexworks.zircon.api.Components.*;
import static org.hexworks.zircon.api.color.ANSITileColor.BRIGHT_GREEN;
import static org.hexworks.zircon.api.color.ANSITileColor.BRIGHT_RED;


public class SelectView extends BaseView {

    public Button backButton;
    public Button startButton;
    public RadioButtonGroup group;

    public SelectView(@NotNull TileGrid tileGrid, @NotNull ColorTheme theme) {
        super(tileGrid, theme);

        String content = "~~ YOUR  ENEMY ~~";
        TextBox logo = Components.textBox(content.length())
                .withDecorations(box(BoxType.LEFT_RIGHT_DOUBLE))
                .addNewLine()
                .addHeader(content, false)
                .withAlignmentWithin(getScreen(), ComponentAlignment.TOP_CENTER)
                .build();

        backButton = UIGlobal.makeColoredButton("BACK", BRIGHT_RED)
                .withAlignmentWithin(getScreen(), ComponentAlignment.BOTTOM_RIGHT)
                .build();

        getScreen().addComponent(logo);
        getScreen().addComponent(backButton);

        VBox radioBox = vbox()
                .withSize(getScreen().getWidth(), 6)
                .withAlignmentWithin(getScreen(), ComponentAlignment.CENTER)
                .withDecorations(box(), shadow())
                .build();

        RadioButton a = radioButton()
                .withText("DUMB HUMAN LIKE YOU")
                .withKey("a")
                .build();
        RadioButton b = radioButton()
                .withText("MINDLESS MACHINE")
                .withKey("b")
                .build();
        RadioButton c = radioButton()
                .withText("*THE* HUMAN KILLER")
                .withKey("c")
                .build();

        radioBox.addComponents(a, b, c);

        group = radioButtonGroup().build();
        group.addComponents(a, b, c);

        getScreen().addComponent(radioBox);

        startButton = UIGlobal.makeColoredButton("START", BRIGHT_GREEN)
                .withAlignmentAround(radioBox, ComponentAlignment.BOTTOM_CENTER)
                .build();

        getScreen().addComponent(startButton);
    }

    @Override
    public void onDock() {
        System.out.println("Switch to Select page.");
    }

    @Override
    public void onUndock() {
        System.out.println("Unload Select page.");
    }
}
