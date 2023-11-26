package bridge.model;

import java.util.Objects;

public final class BridgeSize {
    private static final int MIN_SIZE = 3;
    private static final int MAX_SIZE = 20;
    private static final String INVALID_BRIDGE_SIZE = "다리의 길이는 %d 이상, %d 이하의 자연수만 가능합니다.";
    
    private final int size;

    private BridgeSize(int size) {
        validate(size);
        this.size = size;
    }

    private void validate(int size) {
        if (size < MIN_SIZE || size > MAX_SIZE) {
            throw new IllegalArgumentException(String.format(INVALID_BRIDGE_SIZE, MIN_SIZE, MAX_SIZE));
        }
    }

    public static BridgeSize from(int size) {
        return new BridgeSize(size);
    }

    public int getSize() {
        return size;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        BridgeSize that = (BridgeSize) o;
        return size == that.size;
    }

    @Override
    public int hashCode() {
        return Objects.hash(size);
    }
}
