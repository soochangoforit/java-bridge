package bridge.model;

import java.util.ArrayList;
import java.util.List;

public class Bridge {
    private final List<BridgeElement> bridgeElements;

    private Bridge(List<BridgeElement> bridgeElements) {
        this.bridgeElements = new ArrayList<>(bridgeElements);
    }

    public static Bridge from(List<BridgeElement> bridgeElements) {
        return new Bridge(bridgeElements);
    }

}
