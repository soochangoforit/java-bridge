package bridge.model;

import java.util.stream.Stream;

public enum BridgeElement {
    UP(1),
    DOWN(0);

    private final int number;

    BridgeElement(int number) {
        this.number = number;
    }

    public static BridgeElement from(int number) {
        return Stream.of(values())
                .filter(bridgeElement -> bridgeElement.number == number)
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("다리를 생성할 수 없는 숫자입니다."));
    }

    public boolean isDown() {
        return this == DOWN;
    }

    public boolean isUp() {
        return this == UP;
    }
}
