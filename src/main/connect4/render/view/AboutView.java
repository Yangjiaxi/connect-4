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
import static org.hexworks.zircon.api.Components.label;
import static org.hexworks.zircon.api.color.ANSITileColor.BRIGHT_RED;


/**
 * View: About us
 *
 * @author yang
 */
public class AboutView extends BaseView {
    public final Button backButton;

    public AboutView(@NotNull TileGrid tileGrid, @NotNull ColorTheme theme) {
        super(tileGrid, theme);

        String content = "~~ CONTRIBUTOR ~~";
        TextBox logo = Components.textBox(content.length())
                .withDecorations(box(BoxType.LEFT_RIGHT_DOUBLE))
                .addNewLine()
                .addHeader(content, false)
                .withAlignmentWithin(getScreen(), ComponentAlignment.TOP_CENTER)
                .build();

        backButton = UiGlobal.makeColoredButton("BACK", BRIGHT_RED)
                .withAlignmentWithin(getScreen(), ComponentAlignment.BOTTOM_RIGHT)
                .build();

        String[][] memberInfo = new String[][]{
                {"Yang Jiaxi", "Yangjiaxi"},
                {"Tian Yize", "Osyruz"},
                {"Chen Liwei", "Penistrong"},
                {"Luo Aojie", "Aoki12138"},
                {"Cheng Li", "mercyyyyy"}};
        String name = memberInfo[0][0];
        String account = memberInfo[0][1];

        int namePadLeft = 5;
        Label last = label().withText("> " + name)
                .withPosition(namePadLeft, logo.getSize().getHeight() + 1)
                .build();
        getScreen().addComponents(last);

        int accountPadLeft = 9;
        last = label().withText("@" + account)
                .withPosition(accountPadLeft,
                        last.getPosition().getY() + last.getSize().getHeight())
                .build();
        getScreen().addComponents(last);

        for (int i = 1; i < memberInfo.length; ++i) {
            name = memberInfo[i][0];
            account = memberInfo[i][1];
            last = label().withText("> " + name)
                    .withPosition(namePadLeft, last.getPosition().getY() + last.getSize().getHeight() + 1)
                    .build();
            getScreen().addComponents(last);

            last = label().withText("@" + account)
                    .withPosition(accountPadLeft,
                            last.getPosition().getY() + last.getSize().getHeight())
                    .build();
            getScreen().addComponents(last);
        }

        getScreen().addComponent(logo);
        getScreen().addComponent(backButton);
    }

    @Override
    public void onDock() {
        Utils.logger.fine("Switch to Welcome page.");
    }

    @Override
    public void onUndock() {
        Utils.logger.fine("Unload Welcome page.");
    }
}
