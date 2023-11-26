package bridge.model;

import java.util.ArrayList;
import java.util.List;

public class Bridge {
    private final List<BridgeElement> bridgeElements;
    private int currentPosition;

    private Bridge(List<BridgeElement> bridgeElements, int currentPosition) {
        this.bridgeElements = new ArrayList<>(bridgeElements);
        this.currentPosition = currentPosition;
    }

    public static Bridge from(List<BridgeElement> bridgeElements) {
        int currentPosition = 0;

        return new Bridge(bridgeElements, currentPosition);
    }

    public boolean isMovable(MovingCommand movingCommand) {
        BridgeElement currentBridgeElement = bridgeElements.get(currentPosition);

        boolean movable = movingCommand.isMovable(currentBridgeElement);

        if (movable) {
            currentPosition++;
            return true;
        }

        currentPosition = 0;
        return false;
    }

}
