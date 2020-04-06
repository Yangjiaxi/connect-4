package connect4.render.view;

import connect4.Options;
import connect4.agent.Agent;
import connect4.agent.AgentState;
import connect4.board.Grid;
import connect4.board.GridType;
import connect4.player.*;
import connect4.player.computerplayer.RandomComputerPlayer;
import connect4.render.UIGlobal;
import org.hexworks.zircon.api.component.*;
import org.hexworks.zircon.api.graphics.BoxType;
import org.hexworks.zircon.api.grid.TileGrid;
import org.hexworks.zircon.api.uievent.MouseEventType;
import org.hexworks.zircon.api.uievent.UIEventResponse;
import org.hexworks.zircon.api.view.base.BaseView;
import org.jetbrains.annotations.NotNull;

import static connect4.render.UIGlobal.colorToFontStyle;
import static org.hexworks.zircon.api.ComponentDecorations.*;
import static org.hexworks.zircon.api.Components.*;
import static org.hexworks.zircon.api.color.ANSITileColor.*;

public class GameView extends BaseView {

    public Button backButton;
    private final Label action;
    private final Label player;

    VBox[] columns;
    int rows, cols, goal;

    private Agent agent;

    public GameView(@NotNull TileGrid tileGrid, @NotNull ColorTheme theme) {
        super(tileGrid, theme);

        cols = Options.BOARD_COLUMNS;
        rows = Options.BOARD_ROWS;
        goal = Options.GOAL_TO_WIN;

        int areaHeight = rows * (UIGlobal.UNIT_SIZE - 1) + 1;
        int padTop = (UIGlobal.FRAME_HEIGHT - areaHeight) / 2;
        int padLeft = (UIGlobal.FRAME_WIDTH - UIGlobal.UNIT_SIZE * cols) / 2;

        action = label().withSize(11, 3)
                .withDecorations(box(BoxType.LEFT_RIGHT_DOUBLE))
                .withPosition(padLeft, padTop - 3)
                .build();

        player = label()
                .withText(" ")
                .withDecorations(box(BoxType.LEFT_RIGHT_DOUBLE))
                .withSize(3, 3)
                .withPosition(padLeft + UIGlobal.UNIT_SIZE * cols - 3,
                        padTop - 3)
                .build();

        getScreen().addComponent(action);
        getScreen().addComponent(player);

        backButton = UIGlobal.makeColoredButton("BACK", BRIGHT_RED)
                .withAlignmentWithin(getScreen(), ComponentAlignment.BOTTOM_CENTER)
                .build();
        getScreen().addComponent(backButton);

        columns = new VBox[Options.BOARD_COLUMNS];

        for (int i = 0; i < cols; ++i) {
            columns[i] = vbox()
                    .withSize(UIGlobal.UNIT_SIZE, areaHeight)
                    .withDecorations(box(BoxType.SINGLE))
                    .withPosition(padLeft + i * UIGlobal.UNIT_SIZE, padTop)
                    .build();
            getScreen().addComponent(columns[i]);
        }
        installMouseSelect();
        installTheme();
    }

    @Override
    public void onDock() {
        System.out.println("Switch to Board page.");
        start();
    }

    @Override
    public void onUndock() {
        System.out.println("Unload Board page.");
    }

    public void updateComponents() {
        action.setText(stateToText(agent.getState()));

        player.setText(gridTypeToChar(agent.getActivePlayer()));
        player.setComponentStyleSet(gridTypeToColor(agent.getActivePlayer()));

        for (int j = 0; j < cols; ++j) {
            columns[j].clear();
            for (int i = 0; i < rows; ++i) {
                columns[j].addComponent(label()
                        .withText(gridTypeToChar(agent.getGrid(i, j).getType()))
                        .withSize(1, 1)
                        .withComponentStyleSet(gridToColor(agent.getGrid(i, j)))
                        .withPosition(0, i)
                        .build());
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
            case WAITING:
                return "MOVE";
            case WIN:
                return "WINNER";
            case NO_WIN:
                return "NO WINNER";
            default:
        }
        return "BUG :P";
    }

    private ComponentStyleSet gridTypeToColor(GridType type) {
        switch (type) {
            case EMPTY:
                return colorToFontStyle(BRIGHT_WHITE);
            case PLAYER_A:
                return colorToFontStyle(BRIGHT_YELLOW);
            case PLAYER_B:
                return colorToFontStyle(BRIGHT_BLUE);
            default:
        }
        return colorToFontStyle(BRIGHT_WHITE);
    }

    private ComponentStyleSet gridToColor(Grid grid) {
        if (grid.isWinTrace()) {
            return colorToFontStyle(BRIGHT_RED);
        }
        return gridTypeToColor(grid.getType());
    }

    private String gridTypeToChar(GridType type) {
        switch (type) {
            case EMPTY:
                return " ";
            case PLAYER_A:
                return "X";
            case PLAYER_B:
                return "O";
            default:
        }
        return " ";
    }

    private void installMouseSelect() {
        for (int i = 0; i < cols; ++i) {
            int finalI = i;
            columns[i].handleMouseEvents(MouseEventType.MOUSE_RELEASED, ((event, phase) -> {
                agent.reportInput(finalI);
                return UIEventResponse.stopPropagation();
            }));
        }
    }

    private void installTheme() {
        for (int i = 0; i < cols; ++i) {
            setThemeWhen(columns[i], MouseEventType.MOUSE_MOVED, UIGlobal.THEME_AFTER);
            setThemeWhen(columns[i], MouseEventType.MOUSE_ENTERED, UIGlobal.THEME_AFTER);
            setThemeWhen(columns[i], MouseEventType.MOUSE_EXITED, UIGlobal.THEME_ORIGIN);
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
                agent = new Agent(new HumanPlayer(), new RandomComputerPlayer());
                break;
            default:
        }
    }
}
