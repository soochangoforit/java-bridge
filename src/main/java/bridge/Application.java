package bridge;

import bridge.controller.BridgeGameController;
import bridge.view.input.InputView;
import bridge.view.output.OutputView;
import bridge.view.print.ConsolePrinter;
import bridge.view.print.Printer;

public class Application {

    public static void main(String[] args) {
        Printer printer = new ConsolePrinter();
        InputView inputView = new InputView(printer);
        OutputView outputView = new OutputView(printer);

        BridgeNumberGenerator bridgeNumberGenerator = new BridgeRandomNumberGenerator();
        BridgeMaker bridgeMaker = new BridgeMaker(bridgeNumberGenerator);
        BridgeGameController bridgeGameController = new BridgeGameController(inputView, outputView, bridgeMaker);
        bridgeGameController.run();
    }
}
