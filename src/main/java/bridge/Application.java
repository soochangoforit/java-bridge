package bridge;

import bridge.controller.BridgeGameController;
import bridge.model.UserMovingHistory;
import bridge.view.InputView;
import bridge.view.OutputView;

public class Application {

    public static void main(String[] args) {
        InputView inputView = new InputView();
        OutputView outputView = new OutputView();
        BridgeNumberGenerator bridgeNumberGenerator = new BridgeRandomNumberGenerator();
        UserMovingHistory userMovingHistory = UserMovingHistory.initialize();
        BridgeGameController bridgeGameController = new BridgeGameController(inputView, outputView,
                bridgeNumberGenerator, userMovingHistory);
        bridgeGameController.run();
    }
}
