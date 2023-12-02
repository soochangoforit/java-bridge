package bridge.controller;

import java.util.function.Supplier;
import bridge.BridgeMaker;
import bridge.model.Bridge;
import bridge.model.BridgeSize;
import bridge.view.InputView;
import bridge.view.OutputView;

public class BridgeGameController {
    private final InputView inputView;
    private final OutputView outputView;
    private final BridgeMaker bridgeMaker;

    public BridgeGameController(InputView inputView, OutputView outputView, BridgeMaker bridgeMaker) {
        this.inputView = inputView;
        this.outputView = outputView;
        this.bridgeMaker = bridgeMaker;
    }

    public void run() {
        outputView.printStartMessage();
        BridgeSize bridgeSize = fetch(this::readBridgeSize);
        Bridge bridge = Bridge.create(bridgeMaker, bridgeSize);

    }

    private BridgeSize readBridgeSize() {
        int rawBridgeSize = inputView.readBridgeSize();
        return BridgeSize.from(rawBridgeSize);
    }

    private <T> T fetch(Supplier<T> supplier) {
        try {
            return supplier.get();
        } catch (IllegalArgumentException e) {
            outputView.printExceptionMessage(e.getMessage());
            return fetch(supplier);
        }
    }
}
