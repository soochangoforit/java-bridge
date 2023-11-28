package bridge.model;

import java.util.stream.Stream;

public enum MoveDirection {
    UP_MOVING("U"),
    DOWN_MOVING("D");

    private static final String INVALID_MOVE_DIRECTION = "다리 이동에 적합하지 않은 약어입니다.";
    private final String abbreviation;

    MoveDirection(String abbreviation) {
        this.abbreviation = abbreviation;
    }

    public static MoveDirection from(String abbreviation) {
        return Stream.of(values())
                .filter(moveDirection -> moveDirection.abbreviation.equals(abbreviation))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException(INVALID_MOVE_DIRECTION));
    }

    public boolean isMovable(BridgeElement currentBridgeElement) {
        return movedUp(currentBridgeElement) || movedDown(currentBridgeElement);
    }

    private boolean movedDown(BridgeElement currentBridgeElement) {
        return this == DOWN_MOVING && currentBridgeElement.isDown();
    }

    private boolean movedUp(BridgeElement currentBridgeElement) {
        return this == UP_MOVING && currentBridgeElement.isUp();
    }
}
