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
}
