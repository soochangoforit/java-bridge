package bridge.model;

public class BridgeSize {
    private static final int MIN_BRIDGE_SIZE = 3;
    private static final int MAX_BRIDGE_SIZE = 20;

    private final int size;

    private BridgeSize(int size) {
        validateSize(size);
        this.size = size;
    }

    private void validateSize(int size) {
        if (size < MIN_BRIDGE_SIZE || size > MAX_BRIDGE_SIZE) {
            throw new IllegalArgumentException("다리의 길이는 3 이상, 20 이하의 정수만 가능합니다.");
        }
    }

    public static BridgeSize from(int size) {
        return new BridgeSize(size);
    }

    public int getSize() {
        return size;
    }
}
