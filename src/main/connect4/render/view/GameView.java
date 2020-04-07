package connect4.render.view;

import connect4.Options;
import connect4.agent.Agent;
import connect4.agent.AgentState;
import connect4.board.Grid;
import connect4.board.GridType;
import connect4.player.HumanPlayer;
import connect4.player.PlayerBMode;
import connect4.player.computerplayer.RandomComputerPlayer;
import connect4.render.UiGlobal;
import org.hexworks.zircon.api.builder.graphics.LayerBuilder;
import org.hexworks.zircon.api.color.ANSITileColor;
import org.hexworks.zircon.api.component.*;
import org.hexworks.zircon.api.data.Tile;
import org.hexworks.zircon.api.graphics.BoxType;
import org.hexworks.zircon.api.graphics.LayerHandle;
import org.hexworks.zircon.api.grid.TileGrid;
import org.hexworks.zircon.api.uievent.MouseEventType;
import org.hexworks.zircon.api.uievent.UIEventResponse;
import org.hexworks.zircon.api.view.base.BaseView;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;

import static connect4.Utils.strCenter;
import static connect4.render.UiGlobal.colorToFontStyle;
import static connect4.render.UiGlobal.colorToSingleStyle;
import static org.hexworks.zircon.api.ComponentDecorations.box;
import static org.hexworks.zircon.api.Components.label;
import static org.hexworks.zircon.api.Components.vbox;
import static org.hexworks.zircon.api.color.ANSITileColor.*;

/**
 * View: Gaming
 *
 * @author yang
 */

public class GameView extends BaseView {
    int rows, cols, goal;
    private Agent agent;

    public Button backButton;

    private final Label action;
    private final Label player;
    private final VBox[] columns;

    private final int padTop;
    private final int padLeft;

    private final ArrayList<LayerHandle> layerBuffer;

    public GameView(@NotNull TileGrid tileGrid, @NotNull ColorTheme theme) {
        super(tileGrid, theme);

        cols = Options.BOARD_COLUMNS;
        rows = Options.BOARD_ROWS;
        goal = Options.GOAL_TO_WIN;

        int areaHeight = rows * (UiGlobal.UNIT_SIZE - 1) + 1;
        padTop = (UiGlobal.FRAME_HEIGHT - areaHeight) / 2;
        padLeft = (UiGlobal.FRAME_WIDTH - UiGlobal.UNIT_SIZE * cols) / 2;

        action = label().withSize(UiGlobal.ACTION_LABEL_WIDTH, 3)
                .withDecorations(box(BoxType.LEFT_RIGHT_DOUBLE))
                .withPosition(padLeft, padTop - 3)
                .build();

        player = label()
                .withText(" ")
                .withDecorations(box(BoxType.LEFT_RIGHT_DOUBLE))
                .withSize(3, 3)
                .withPosition(padLeft + UiGlobal.UNIT_SIZE * cols - 3, padTop - 3)
                .build();

        getScreen().addComponent(action);
        getScreen().addComponent(player);

        backButton = UiGlobal.makeColoredButton("BACK", BRIGHT_RED)
                .withAlignmentWithin(getScreen(), ComponentAlignment.BOTTOM_CENTER)
                .build();
        getScreen().addComponent(backButton);

        columns = new VBox[Options.BOARD_COLUMNS];
        layerBuffer = new ArrayList<>();

        for (int i = 0; i < cols; ++i) {
            columns[i] = vbox()
                    .withSize(UiGlobal.UNIT_SIZE, areaHeight)
                    .withDecorations(box(BoxType.SINGLE))
                    .withPosition(padLeft + i * UiGlobal.UNIT_SIZE, padTop)
                    .build();
            getScreen().addComponent(columns[i]);
        }
        installMouseSelect();
        installTheme();
    }

    @Override
    public void onDock() {
        start();
    }

    @Override
    public void onUndock() {
        agent.stop();
        layerBuffer.forEach(LayerHandle::removeLayer);
    }

    public void updateComponents() {
        action.setText(strCenter(stateToText(agent.getState()), UiGlobal.ACTION_TEXT_WIDTH, '>', '<'));

        player.setText(String.valueOf(gridTypeToChar(agent.getActivePlayer())));
        player.setComponentStyleSet(colorToFontStyle(gridTypeToColor(agent.getActivePlayer())));

        for (int j = 0; j < cols; ++j) {
            for (int i = 0; i < rows; ++i) {
                Grid tmp = agent.getGrid(i, j);
                if (tmp.getType() != GridType.EMPTY) {
                    layerBuffer.add(getScreen().addLayer(new LayerBuilder()
                            .withOffset(padLeft + UiGlobal.UNIT_SIZE * j + 1,
                                    padTop + 1 + (UiGlobal.UNIT_SIZE - 1) * i)
                            .withSize(1, 1)
                            .withFiller(Tile.newBuilder()
                                    .withStyleSet(colorToSingleStyle(gridToColor(tmp)))
                                    .withCharacter(gridTypeToChar(tmp.getType()))
                                    .build())
                            .build()));
                }
            }
        }
    }

    public void start() {
        agent.connect(this);
        agent.start();
    }

    private String stateToText(AgentState state) {
        switch (state) {
            case READY:
                return "START";
            case WAITING_COMPUTER:
                return "THINKING";
            case WAITING_HUMAN:
                return "MOVE";
            case WIN:
                return "WINNER";
            case NO_WIN:
                return "NO WINNER";
            default:
                return "BUG :P";
        }
    }

    private ANSITileColor gridTypeToColor(GridType type) {
        switch (type) {
            case EMPTY:
                return BRIGHT_WHITE;
            case PLAYER_A:
                return BRIGHT_YELLOW;
            case PLAYER_B:
                return BRIGHT_BLUE;
            default:
        }
        return BRIGHT_WHITE;
    }

    private ANSITileColor gridToColor(Grid grid) {
        if (grid.isWinTrace()) {
            return BRIGHT_RED;
        }
        return gridTypeToColor(grid.getType());
    }

    private char gridTypeToChar(GridType type) {
        switch (type) {
            case EMPTY:
                return ' ';
            case PLAYER_A:
                return 'X';
            case PLAYER_B:
                return 'O';
            default:
        }
        return ' ';
    }

    private void installMouseSelect() {
        for (int i = 0; i < cols; ++i) {
            int finalI = i;
            columns[i].handleMouseEvents(MouseEventType.MOUSE_RELEASED, ((event, phase) -> {
                agent.reportUiInput(finalI);
                return UIEventResponse.stopPropagation();
            }));
        }
    }

    private void installTheme() {
        for (int i = 0; i < cols; ++i) {
            setThemeWhen(columns[i], MouseEventType.MOUSE_MOVED, UiGlobal.THEME_AFTER);
            setThemeWhen(columns[i], MouseEventType.MOUSE_ENTERED, UiGlobal.THEME_AFTER);
            setThemeWhen(columns[i], MouseEventType.MOUSE_EXITED, UiGlobal.THEME_ORIGIN);
        }
    }

    private void setThemeWhen(VBox target, MouseEventType when, ColorTheme theme) {
        target.handleMouseEvents(when, ((event, phase) -> {
            target.setTheme(theme);
            return UIEventResponse.processed();
        }));
    }

    public void loadGame(PlayerBMode human) {
        switch (human) {
            case Human:
                agent = new Agent(new HumanPlayer(), new HumanPlayer());
                break;
            case RNG:
                agent = new Agent(new HumanPlayer(), new RandomComputerPlayer());
                break;
            case MiniMax:
                agent = new Agent(new RandomComputerPlayer(), new RandomComputerPlayer());
                break;
            default:
        }
    }
}
