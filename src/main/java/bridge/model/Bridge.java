package bridge.model;

import java.util.List;
import bridge.BridgeMaker;

public class Bridge {
    private final List<BridgeElement> bridgeElements;

    private Bridge(List<BridgeElement> bridgeElements) {
        this.bridgeElements = bridgeElements;
    }

    public static Bridge create(BridgeMaker bridgeMaker, BridgeSize bridgeSize) {
        List<String> rawBridgeElements = bridgeMaker.makeBridge(bridgeSize.getSize());
        List<BridgeElement> bridgeElements = rawBridgeElements.stream()
                .map(BridgeElement::from)
                .toList();

        return new Bridge(bridgeElements);
    }

    public boolean isCrossedAll(int currentPosition) {
        return currentPosition == bridgeElements.size();
    }

    public boolean isCrossed(int currentPosition, MovingDirection movingDirection) {
        BridgeElement bridgeElement = bridgeElements.get(currentPosition);
        return movingDirection.canMove(bridgeElement);
    }
}
