package connect4.render.view;

import connect4.Options;
import connect4.Utils;
import connect4.agent.Agent;
import connect4.agent.AgentState;
import connect4.board.Grid;
import connect4.board.GridType;
import connect4.player.BasePlayer;
import connect4.player.HumanPlayer;
import connect4.player.PlayerType;
import connect4.player.computerplayer.MiniMaxPlayer;
import connect4.player.computerplayer.RandomComputerPlayer;
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
import static connect4.render.UiGlobal.*;
import static org.hexworks.zircon.api.ComponentDecorations.box;
import static org.hexworks.zircon.api.Components.label;
import static org.hexworks.zircon.api.Components.vbox;
import static org.hexworks.zircon.api.color.ANSITileColor.BRIGHT_RED;
import static org.hexworks.zircon.api.color.ANSITileColor.BRIGHT_WHITE;

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

        int areaHeight = rows * (UNIT_SIZE - 1) + 1;
        padTop = (FRAME_HEIGHT - areaHeight) / 2;
        padLeft = (FRAME_WIDTH - UNIT_SIZE * cols) / 2;

        action = label().withSize(ACTION_LABEL_WIDTH, UNIT_SIZE)
                .withDecorations(box(BoxType.LEFT_RIGHT_DOUBLE))
                .withPosition(padLeft, padTop - UNIT_SIZE)
                .build();

        player = label()
                .withText(" ")
                .withDecorations(box(BoxType.LEFT_RIGHT_DOUBLE))
                .withSize(UNIT_SIZE, UNIT_SIZE)
                .withPosition(padLeft + UNIT_SIZE * cols - UNIT_SIZE, padTop - UNIT_SIZE)
                .build();

        getScreen().addComponent(action);
        getScreen().addComponent(player);

        backButton = makeColoredButton("BACK", BRIGHT_RED)
                .withAlignmentWithin(getScreen(), ComponentAlignment.BOTTOM_CENTER)
                .build();
        getScreen().addComponent(backButton);

        columns = new VBox[Options.BOARD_COLUMNS];
        layerBuffer = new ArrayList<>();

        for (int i = 0; i < cols; ++i) {
            columns[i] = vbox()
                    .withSize(UNIT_SIZE, areaHeight)
                    .withDecorations(box(BoxType.SINGLE))
                    .withPosition(padLeft + i * UNIT_SIZE, padTop)
                    .build();
            getScreen().addComponent(columns[i]);
        }
        installMouseSelect();
        installTheme();
    }

    @Override
    public void onDock() {
        start();
        Utils.logger.info("Switch to Game page.");
    }

    @Override
    public void onUndock() {
        agent.stop();
        layerBuffer.forEach(LayerHandle::removeLayer);
        Utils.logger.info("Unload to Game page.");

    }

    public void updateComponents() {
        action.setText(strCenter(stateToText(agent.getState()), ACTION_TEXT_WIDTH, '>', '<'));

        player.setText(String.valueOf(gridTypeToChar(agent.getActivePlayer())));
        player.setComponentStyleSet(colorToFontStyle(gridTypeToColor(agent.getActivePlayer())));

        for (int j = 0; j < cols; ++j) {
            for (int i = 0; i < rows; ++i) {
                Grid tmp = agent.getGrid(i, j);
                if (tmp.getType() != GridType.EMPTY) {
                    layerBuffer.add(getScreen().addLayer(new LayerBuilder()
                            .withOffset(padLeft + UNIT_SIZE * j + 1,
                                    padTop + 1 + (UNIT_SIZE - 1) * i)
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
            case PLAYER_A:
                return PLAYER_A_COLOR;
            case PLAYER_B:
                return PLAYER_B_COLOR;
            default:
                return BRIGHT_WHITE;
        }
    }

    private ANSITileColor gridToColor(Grid grid) {
        if (grid.isWinTrace()) {
            return WIN_TRACE_COLOR;
        }
        return gridTypeToColor(grid.getType());
    }

    private char gridTypeToChar(GridType type) {
        switch (type) {
            case PLAYER_A:
                return charPlayerA;
            case PLAYER_B:
                return charPlayerB;
            default:
                return ' ';
        }
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
            setThemeWhen(columns[i], MouseEventType.MOUSE_MOVED, THEME_AFTER);
            setThemeWhen(columns[i], MouseEventType.MOUSE_ENTERED, THEME_AFTER);
            setThemeWhen(columns[i], MouseEventType.MOUSE_EXITED, THEME_ORIGIN);
        }
    }

    private void setThemeWhen(VBox target, MouseEventType when, ColorTheme theme) {
        target.handleMouseEvents(when, ((event, phase) -> {
            target.setTheme(theme);
            return UIEventResponse.processed();
        }));
    }

    private BasePlayer modeToPlayer(PlayerType type) {
        switch (type) {
            case Human:
                return new HumanPlayer();
            case RNG:
                return new RandomComputerPlayer();
            case MiniMax:
                return new MiniMaxPlayer();
            default:
        }
        return new HumanPlayer();
    }

    public void loadGame(PlayerType playerA, PlayerType playerB) {
        agent = new Agent(modeToPlayer(playerA), modeToPlayer(playerB));
    }
}
