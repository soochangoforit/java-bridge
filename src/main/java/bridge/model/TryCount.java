package bridge.model;

import java.util.Objects;

public final class TryCount {
    private static final String INVALID_TRY_COUNT = "시도 횟수는 %d 이상의 자연수만 가능합니다.";
    private static final int DEFAULT_TRY_COUNT = 1;

    private final int count;

    TryCount(int count) {
        validate(count);
        this.count = count;
    }

    private void validate(int value) {
        if (value < DEFAULT_TRY_COUNT) {
            String exceptionMessage = String.format(INVALID_TRY_COUNT, DEFAULT_TRY_COUNT);
            throw new IllegalArgumentException(exceptionMessage);
        }
    }

    public static TryCount defaultTryCount() {
        return new TryCount(DEFAULT_TRY_COUNT);
    }

    public TryCount increase() {
        return new TryCount(count + 1);
    }

    public int getCount() {
        return count;
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
        return count == tryCount.count;
    }

    @Override
    public int hashCode() {
        return Objects.hash(count);
    }
}
