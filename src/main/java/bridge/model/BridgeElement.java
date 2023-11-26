package bridge.model;

import java.util.stream.Stream;

public enum BridgeElement {
    UP(1, "U"),
    DOWN(0, "D");

    private final int number;
    private final String element;

    BridgeElement(int number, String element) {
        this.number = number;
        this.element = element;
    }

    public static String getElement(int number) {
        return Stream.of(values())
                .filter(bridgeElement -> bridgeElement.number == number)
                .findFirst()
                .map(bridgeElement -> bridgeElement.element)
                .orElseThrow(() -> new IllegalArgumentException("다리를 생성할 수 없는 숫자입니다."));
    }

    public static BridgeElement from(String element) {
        return Stream.of(values())
                .filter(bridgeElement -> bridgeElement.element.equals(element))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("다리를 생성할 수 없는 문자입니다."));
    }

    public boolean isDown() {
        return this == DOWN;
    }

    public boolean isUp() {
        return this == UP;
    }
}
