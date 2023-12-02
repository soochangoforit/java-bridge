package bridge.model;

import java.util.stream.Stream;

public enum MovingDirection {
    MOVE_UP("U"),
    MOVE_DOWN("D");

    private final String abbreviation;

    MovingDirection(String abbreviation) {
        this.abbreviation = abbreviation;
    }

    public static MovingDirection from(String abbreviation) {
        return Stream.of(values())
                .filter(movingDirection -> movingDirection.abbreviation.equals(abbreviation))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("이동 방향은 U 또는 D 문자로만 생성이 가능합니다."));
    }
}
