package bridge.model;

import java.util.Objects;

public final class MovePosition {
    private static final int DEFAULT_POSITION = 0;
    private static final int MOVE_FORWARD = 1;

    private final int value;

    MovePosition(int value) {
        validate(value);
        this.value = value;
    }

    private void validate(int value) {
        if (value < DEFAULT_POSITION) {
            throw new IllegalArgumentException(String.format("이동 위치는 %d 이상의 자연수만 가능합니다.", DEFAULT_POSITION));
        }
    }

    public static MovePosition defaultPosition() {
        return new MovePosition(DEFAULT_POSITION);
    }

    public boolean isSame(int bridgeSize) {
        return value == bridgeSize;
    }

    public MovePosition move() {
        return new MovePosition(value + MOVE_FORWARD);
    }

    public int getValue() {
        return value;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        MovePosition that = (MovePosition) o;
        return value == that.value;
    }

    @Override
    public int hashCode() {
        return Objects.hash(value);
    }
}
