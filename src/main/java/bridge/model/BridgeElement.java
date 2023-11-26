package bridge.model;

import java.util.stream.Stream;

public enum BridgeElement {
    UP(1, "U"),
    DOWN(0, "D");

    private static final String INVALID_ELEMENT_NUMBER_MESSAGE = "다리를 생성하기엔 적절하지 못한 숫자입니다.";
    private static final String INVALID_ELEMENT_SYMBOL_MESSAGE = "다리를 생성할 수 없는 문자입니다.";

    private final int number;
    private final String symbol;

    BridgeElement(int number, String symbol) {
        this.number = number;
        this.symbol = symbol;
    }

    public static String getBridgeElementSymbol(int number) {
        return Stream.of(values())
                .filter(bridgeElement -> bridgeElement.number == number)
                .findFirst()
                .map(bridgeElement -> bridgeElement.symbol)
                .orElseThrow(() -> new IllegalArgumentException(INVALID_ELEMENT_NUMBER_MESSAGE));
    }

    public static BridgeElement from(String symbol) {
        return Stream.of(values())
                .filter(bridgeElement -> bridgeElement.symbol.equals(symbol))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException(INVALID_ELEMENT_SYMBOL_MESSAGE));
    }

    public boolean isDown() {
        return this == DOWN;
    }

    public boolean isUp() {
        return this == UP;
    }
}
