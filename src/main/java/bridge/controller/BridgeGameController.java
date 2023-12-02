package bridge.controller;

import java.util.function.Supplier;
import bridge.model.BridgeSize;
import bridge.view.InputView;
import bridge.view.OutputView;

public class BridgeGameController {
    private final InputView inputView;
    private final OutputView outputView;

    public BridgeGameController(InputView inputView, OutputView outputView) {
        this.inputView = inputView;
        this.outputView = outputView;
    }

    public void run() {
        outputView.printStartMessage();
        BridgeSize bridgeSize = fetch(this::readBridgeSize);

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
