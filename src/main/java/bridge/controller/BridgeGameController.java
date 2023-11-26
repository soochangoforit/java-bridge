package bridge.controller;

import java.util.function.Supplier;
import bridge.BridgeNumberGenerator;
import bridge.model.BridgeGame;
import bridge.model.BridgeSize;
import bridge.model.MoveHistory;
import bridge.model.MovingCommand;
import bridge.model.RetryCommand;
import bridge.model.UserMovingHistory;
import bridge.view.InputView;
import bridge.view.OutputView;

public class BridgeGameController {
    private final InputView inputView;
    private final OutputView outputView;
    private final BridgeNumberGenerator bridgeNumberGenerator;
    private final UserMovingHistory userMovingHistory;

    public BridgeGameController(InputView inputView, OutputView outputView,
                                BridgeNumberGenerator bridgeNumberGenerator, UserMovingHistory userMovingHistory) {
        this.inputView = inputView;
        this.outputView = outputView;
        this.bridgeNumberGenerator = bridgeNumberGenerator;
        this.userMovingHistory = userMovingHistory;
    }

    public void run() {
        outputView.printStartMessage();
        BridgeSize bridgeSize = retryOnException(this::fetchBridgeSize);
        BridgeGame bridgeGame = BridgeGame.create(bridgeNumberGenerator, bridgeSize);

        while (true) {
            MovingCommand movingCommand = retryOnException(this::fetchMovingCommand);

            MoveHistory userMoveHistory = bridgeGame.move(movingCommand);
            userMovingHistory.add(userMoveHistory);
            outputView.printMap(userMovingHistory.getMoveHistories());

            if (userMoveHistory.isNotMovable()) {
                RetryCommand retryCommand = retryOnException(this::fetchRetryCommand);

                // 재시도 할 경우
                if (retryCommand.isRetry()) {
                    bridgeGame.resetPosition();
                    userMovingHistory.clearHistory();
                    continue;
                }

                // 재시도 하지 않을 경우

            }
        }

    }

    private RetryCommand fetchRetryCommand() {
        String rawRetryCommand = inputView.readGameCommand();
        return RetryCommand.from(rawRetryCommand);
    }

    private MovingCommand fetchMovingCommand() {
        String rawMovingCommand = inputView.readMoving();
        return MovingCommand.from(rawMovingCommand);
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
