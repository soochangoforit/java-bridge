package bridge.model;

import java.util.ArrayList;
import java.util.List;

public final class Bridge {
    private final List<BridgeElement> bridgeElements;

    private Bridge(List<BridgeElement> bridgeElements) {
        this.bridgeElements = new ArrayList<>(bridgeElements);
    }

    public static Bridge from(List<BridgeElement> bridgeElements) {
        return new Bridge(bridgeElements);
    }

    public boolean isMovable(MovePosition movePosition, MoveDirection moveDirection) {
        BridgeElement currentBridgeElement = bridgeElements.get(movePosition.getValue());
        return moveDirection.canMove(currentBridgeElement);
    }

    public int getSize() {
        return bridgeElements.size();
    }
}
