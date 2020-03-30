package connect4.render.view;

import connect4.render.UIGlobal;
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
import static org.hexworks.zircon.api.color.ANSITileColor.BRIGHT_RED;


public class AboutView extends BaseView {
    public Button backButton;

    public AboutView(@NotNull TileGrid tileGrid, @NotNull ColorTheme theme) {
        super(tileGrid, theme);

        String content = "~~ CONTRIBUTOR ~~";
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
    }

    @Override
    public void onDock() {
        System.out.println("Switch to Welcome page.");
    }

    @Override
    public void onUndock() {
        System.out.println("Unload Welcome page.");
    }
}
