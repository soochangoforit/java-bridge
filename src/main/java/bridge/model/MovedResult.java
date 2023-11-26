package bridge.model;

import static bridge.model.MovingCommand.DOWN_MOVING;
import static bridge.model.MovingCommand.UP_MOVING;

import java.util.stream.Stream;

public enum MovedResult {
    UP_TRUE(UP_MOVING, true),
    UP_FALSE(UP_MOVING, false),
    DOWN_TRUE(DOWN_MOVING, true),
    DOWN_FALSE(DOWN_MOVING, false);

    private static final String INVALID_MOVING_COMMAND = "이동하고자 하는 명령어와 이동 가능 여부에 적합하지 않습니다.";

    private final MovingCommand movingCommand;
    private final boolean moved;

    MovedResult(MovingCommand movingCommand, boolean moved) {
        this.movingCommand = movingCommand;
        this.moved = moved;
    }

    public static MovedResult of(MovingCommand movingCommand, boolean isMoved) {
        return Stream.of(values())
                .filter(moveHistory -> moveHistory.movingCommand == movingCommand && moveHistory.moved == isMoved)
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException(INVALID_MOVING_COMMAND));
    }

    public boolean isMoved() {
        return moved;
    }

    public boolean isNotMoved() {
        return !moved;
    }

    public boolean getMovable() {
        return moved;
    }

    public MovingCommand getMovingCommand() {
        return movingCommand;
    }
}
