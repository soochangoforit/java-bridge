package bridge.model;

public class BridgeSize {
    private final int size;

    private BridgeSize(int size) {
        validateSize(size);
        this.size = size;
    }

    private void validateSize(int size) {
        if (size < 3 || size > 20) {
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
