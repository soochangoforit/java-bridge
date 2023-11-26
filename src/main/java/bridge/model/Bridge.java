package bridge.model;

import java.util.ArrayList;
import java.util.List;

public final class Bridge {
    private static final int INITIAL_POSITION = 0;

    private final List<BridgeElement> bridgeElements;
    private int currentPosition;

    private Bridge(List<BridgeElement> bridgeElements, int currentPosition) {
        this.bridgeElements = new ArrayList<>(bridgeElements);
        this.currentPosition = currentPosition;
    }

    public static Bridge from(List<BridgeElement> bridgeElements) {
        return new Bridge(bridgeElements, INITIAL_POSITION);
    }

    public boolean isMovable(MovingCommand movingCommand) {
        BridgeElement currentBridgeElement = bridgeElements.get(currentPosition);
        boolean movable = movingCommand.isMovable(currentBridgeElement);
        if (movable) {
            ++currentPosition;
            return true;
        }
        return false;
    }

    public void resetPosition() {
        currentPosition = 0;
    }

    public boolean isFinished() {
        return currentPosition == bridgeElements.size();
    }
}
