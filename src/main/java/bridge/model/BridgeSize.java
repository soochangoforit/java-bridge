package bridge.model;

public final class BridgeSize {
    private static final int MIN_SIZE = 3;
    private static final int MAX_SIZE = 20;
    private final int size;

    private BridgeSize(int size) {
        validate(size);
        this.size = size;
    }

    private void validate(int size) {
        if (size < MIN_SIZE || size > MAX_SIZE) {
            throw new IllegalArgumentException("다리의 길이는 3 이상, 20 이하의 자연수만 가능합니다.");
        }
    }

    public static BridgeSize from(int size) {
        return new BridgeSize(size);
    }
}
