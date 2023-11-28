package bridge.model;

import static bridge.model.MoveDirection.DOWN_MOVING;
import static bridge.model.MoveDirection.UP_MOVING;

import java.util.stream.Stream;

public enum MovedResult {
    UP_TRUE(UP_MOVING, true),
    UP_FALSE(UP_MOVING, false),
    DOWN_TRUE(DOWN_MOVING, true),
    DOWN_FALSE(DOWN_MOVING, false);

    private static final String INVALID_MOVING_COMMAND = "이동하고자 하는 명령어와 이동 가능 여부에 적합하지 않습니다.";

    private final MoveDirection moveDirection;
    private final boolean moved;

    MovedResult(MoveDirection moveDirection, boolean moved) {
        this.moveDirection = moveDirection;
        this.moved = moved;
    }

    public static MovedResult of(MoveDirection moveDirection, boolean isMoved) {
        return Stream.of(values())
                .filter(moveHistory -> moveHistory.moveDirection == moveDirection && moveHistory.moved == isMoved)
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

    public MoveDirection getMovingCommand() {
        return moveDirection;
    }
}
