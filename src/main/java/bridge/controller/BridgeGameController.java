package bridge.controller;

import bridge.BridgeNumberGenerator;
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
    }
}
