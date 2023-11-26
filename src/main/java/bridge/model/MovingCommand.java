package bridge.model;

import java.util.stream.Stream;

public enum MovingCommand {
    UP_MOVING("U"),
    DOWN_MOVING("D");

    private final String command;

    MovingCommand(String command) {
        this.command = command;
    }

    public static MovingCommand from(String command) {
        return Stream.of(values())
                .filter(movingCommand -> movingCommand.command.equals(command))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("다리 이동에 적합하지 않은 명령어입니다."));
    }

    public boolean isMovable(BridgeElement currentBridgeElement) {
        return this == UP_MOVING && currentBridgeElement.isUp() || this == DOWN_MOVING && currentBridgeElement.isDown();
    }
}
