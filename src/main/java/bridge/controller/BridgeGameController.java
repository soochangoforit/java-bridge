package bridge.controller;

import java.util.function.Supplier;
import bridge.BridgeNumberGenerator;
import bridge.model.BridgeSize;
import bridge.view.InputView;
import bridge.view.OutputView;

public class BridgeGameController {
    private final InputView inputView;
    private final OutputView outputView;
    private final BridgeNumberGenerator bridgeNumberGenerator;

    public BridgeGameController(InputView inputView, OutputView outputView,
                                BridgeNumberGenerator bridgeNumberGenerator) {
        this.inputView = inputView;
        this.outputView = outputView;
        this.bridgeNumberGenerator = bridgeNumberGenerator;
    }

    public void run() {
        outputView.printStartMessage();
        retryOnException(this::fetchBridgeSize);

    }

    private BridgeSize fetchBridgeSize() {
        int bridgeSize = inputView.readBridgeSize();
        return BridgeSize.from(bridgeSize);
    }

    private <T> T retryOnException(Supplier<T> supplier) {
        try {
            return supplier.get();
        } catch (IllegalArgumentException e) {
            outputView.printExceptionMessage(e.getMessage());
            return retryOnException(supplier);
        }
    }
}
