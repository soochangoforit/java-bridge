package bridge.controller;

import java.util.function.Supplier;
import bridge.BridgeMaker;
import bridge.model.BridgeGame;
import bridge.model.BridgeSize;
import bridge.model.MovedHistory;
import bridge.model.MovingCommand;
import bridge.model.RetryCommand;
import bridge.model.UserMovingHistory;
import bridge.view.InputView;
import bridge.view.OutputView;

public class BridgeGameController {
    private final InputView inputView;
    private final OutputView outputView;
    private final BridgeMaker bridgeMaker;
    private final UserMovingHistory userMovingHistory;

    public BridgeGameController(InputView inputView, OutputView outputView, BridgeMaker bridgeMaker,
                                UserMovingHistory userMovingHistory) {
        this.inputView = inputView;
        this.outputView = outputView;
        this.bridgeMaker = bridgeMaker;
        this.userMovingHistory = userMovingHistory;
    }

    public void run() {
        printStartMessage();
        BridgeGame bridgeGame = createBridgeGame();
        while (bridgeGame.isInProgress()) {
            MovedHistory userMovedHistory = crossBridgeFromUserCommand(bridgeGame);
            handleUnsuccessfulMove(userMovedHistory, bridgeGame);
        }
        printGameFinalResult(bridgeGame);
    }

    private void handleUnsuccessfulMove(MovedHistory userMovedHistory, BridgeGame bridgeGame) {
        if (userMovedHistory.isNotMoved()) {
            RetryCommand retryCommand = retryOnException(this::fetchRetryCommand);
            handleOnReTry(retryCommand, bridgeGame);
            handleOnQuit(retryCommand, bridgeGame);
        }
    }

    private void printGameFinalResult(BridgeGame bridgeGame) {
        outputView.printResult(userMovingHistory.getMoveHistories(), bridgeGame.getTryCount().getValue());
    }

    private void printStartMessage() {
        outputView.printStartMessage();
    }

    private BridgeGame createBridgeGame() {
        BridgeSize bridgeSize = retryOnException(this::fetchBridgeSize);
        return BridgeGame.create(bridgeMaker, bridgeSize);
    }

    private void handleOnQuit(RetryCommand retryCommand, BridgeGame bridgeGame) {
        if (retryCommand.isEnd()) {
            bridgeGame.endGame();
        }
    }

    private MovedHistory crossBridgeFromUserCommand(BridgeGame bridgeGame) {
        MovingCommand movingCommand = retryOnException(this::fetchMovingCommand);
        MovedHistory userMovedHistory = bridgeGame.move(movingCommand);
        printUserMoveHistory(userMovedHistory);
        return userMovedHistory;
    }

    private void handleOnReTry(RetryCommand retryCommand, BridgeGame bridgeGame) {
        if (retryCommand.isRetry()) {
            bridgeGame.resetPosition();
            bridgeGame.increaseTryCount();
            userMovingHistory.clearHistory();
        }
    }

    private void printUserMoveHistory(MovedHistory userMovedHistory) {
        userMovingHistory.add(userMovedHistory);
        outputView.printMap(userMovingHistory.getMoveHistories());
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
