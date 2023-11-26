package bridge.model;

import java.util.Objects;

public final class TryCount {
    private static final int FIRST_TRY_COUNT = 1;
    private static final int ONE_COUNT = 1;
    private static final String INVALID_TRY_COUNT = "시도 횟수는 %d 이상의 자연수만 가능합니다.";
    
    private final int count;

    private TryCount(int count) {
        validate(count);
        this.count = count;
    }

    private void validate(int value) {
        if (value < FIRST_TRY_COUNT) {
            String exceptionMessage = String.format(INVALID_TRY_COUNT, FIRST_TRY_COUNT);
            throw new IllegalArgumentException(exceptionMessage);
        }
    }

    public static TryCount firstTryCount() {
        return new TryCount(FIRST_TRY_COUNT);
    }

    public TryCount increase() {
        return new TryCount(count + ONE_COUNT);
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
