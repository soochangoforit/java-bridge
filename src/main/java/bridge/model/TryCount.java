package bridge.model;

import java.util.Objects;

public final class TryCount {
    private final int value;

    private TryCount(int value) {
        validate(value);
        this.value = value;
    }

    private void validate(int value) {
        if (value < 0) {
            throw new IllegalArgumentException("시도 횟수는 0 이상의 자연수만 가능합니다.");
        }
    }

    public static TryCount firstTry() {
        return new TryCount(1);
    }

    public TryCount increase() {
        return new TryCount(value + 1);
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
        TryCount tryCount = (TryCount) o;
        return value == tryCount.value;
    }

    @Override
    public int hashCode() {
        return Objects.hash(value);
    }
}
