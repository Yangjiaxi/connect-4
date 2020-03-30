package connect4.render;

import org.hexworks.zircon.api.CP437TilesetResources;
import org.hexworks.zircon.api.ColorThemes;
import org.hexworks.zircon.api.Components;
import org.hexworks.zircon.api.SwingApplications;
import org.hexworks.zircon.api.application.AppConfig;
import org.hexworks.zircon.api.builder.component.ButtonBuilder;
import org.hexworks.zircon.api.color.ANSITileColor;
import org.hexworks.zircon.api.component.ColorTheme;
import org.hexworks.zircon.api.component.ComponentStyleSet;
import org.hexworks.zircon.api.data.Size;
import org.hexworks.zircon.api.graphics.StyleSet;
import org.hexworks.zircon.api.grid.TileGrid;

import java.util.HashSet;

import static org.hexworks.zircon.api.ComponentDecorations.box;
import static org.hexworks.zircon.api.color.TileColor.transparent;

public class UIGlobal {
    public static int FRAME_HEIGHT = 24; // 24
    public static int FRAME_WIDTH = 27; // 27
    public static int UNIT_SIZE = 3;

    public static ColorTheme THEME_ORIGIN = ColorThemes.techLight();
    public static ColorTheme THEME_AFTER = ColorThemes.gamebookers();

//    public static ColorTheme THEME_ORIGIN = ColorThemes.solarizedDarkBlue();
//    public static ColorTheme THEME_AFTER = ColorThemes.techLight();

    public static Size GRID_SIZE = Size.create(FRAME_WIDTH, FRAME_HEIGHT);

    public static TileGrid startTileGrid(String title) {
        /*
            GOOD:
              - vga8X16
              - acorn8X16
              - markX16x16
              - rexPaint20x20
              -

         */
        return SwingApplications.startTileGrid(AppConfig.newBuilder()
                .withTitle(title)
                .withDefaultTileset(CP437TilesetResources.markX16x16())
                .enableBetaFeatures()
                .withSize(GRID_SIZE)
                .build());
    }

    public static ComponentStyleSet colorToFontStyle(ANSITileColor color) {
         return ComponentStyleSet.newBuilder()
                .withDefaultStyle(StyleSet.create(color, transparent(), new HashSet<>()))
                .build();
    }

    public static ButtonBuilder makeColoredButton(String text, ANSITileColor color) {
        ComponentStyleSet style = ComponentStyleSet.newBuilder()
                .withDefaultStyle(StyleSet.create(color, transparent(), new HashSet<>()))
                .withActiveStyle(StyleSet.create(ANSITileColor.GRAY, color, new HashSet<>()))
                .withMouseOverStyle(StyleSet.create(transparent(), color, new HashSet<>()))
                .build();

        return Components.button()
                .withText(text)
                .withDecorations(box())
                .withComponentStyleSet(style);
    }
}
