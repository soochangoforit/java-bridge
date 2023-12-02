package bridge.model;

import java.util.stream.Stream;

public enum BridgeElement {
    UP("U", 1),
    DOWN("D", 0);

    private final String abbreviation;
    private final int value;

    BridgeElement(String abbreviation, int value) {
        this.abbreviation = abbreviation;
        this.value = value;
    }

    public static BridgeElement from(int value) {
        return Stream.of(values())
                .filter(bridgeElement -> bridgeElement.value == value)
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("다리의 형태는 0 또는 1 숫자로만 생성이 가능합니다."));
    }

    public static BridgeElement from(String abbreviation) {
        return Stream.of(values())
                .filter(bridgeElement -> bridgeElement.abbreviation.equals(abbreviation))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("다리의 형태는 U 또는 D 문자로만 생성이 가능합니다."));
    }

    public String getAbbreviation() {
        return abbreviation;
    }
}
