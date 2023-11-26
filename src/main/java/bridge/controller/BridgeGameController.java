package bridge.controller;

import java.util.function.Supplier;
import bridge.BridgeMaker;
import bridge.model.BridgeGame;
import bridge.model.BridgeSize;
import bridge.model.MovedHistory;
import bridge.model.MovedResult;
import bridge.model.MovingCommand;
import bridge.model.Player;
import bridge.model.RetryCommand;
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
        printStartMessage();
        BridgeGame bridgeGame = createBridgeGame();
        Player player = Player.initialize();
        while (bridgeGame.isInProgress()) {
            MovedResult movedResult = crossBridge(player, bridgeGame);
            handleUnsuccessfulMove(player, movedResult, bridgeGame);
        }
        printGameFinalResult(player.getMovedHistory(), player.getTryCount().getValue());
    }

    private void handleUnsuccessfulMove(Player player, MovedResult userMovedResult, BridgeGame bridgeGame) {
        if (userMovedResult.isNotMoved()) {
            RetryCommand retryCommand = retryOnException(this::fetchRetryCommand);
            handleOnReTry(retryCommand, player, bridgeGame);
            handleOnQuit(retryCommand, bridgeGame);
        }
    }

    private void printGameFinalResult(MovedHistory movedHistory, int tryCount) {
        outputView.printResult(movedHistory, tryCount);
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

    private MovedResult crossBridge(Player player, BridgeGame bridgeGame) {
        MovingCommand movingCommand = retryOnException(this::fetchMovingCommand);
        MovedResult userMovedResult = bridgeGame.move(movingCommand);
        player.markMovedHistory(userMovedResult);
        printMovedHistory(player.getMovedHistory());
        return userMovedResult;
    }

    private void handleOnReTry(RetryCommand retryCommand, Player player, BridgeGame bridgeGame) {
        if (retryCommand.isRetry()) {
            bridgeGame.resetPosition();
            player.increaseTryCount();
            player.clearHistory();
        }
    }

    private void printMovedHistory(MovedHistory movedHistory) {
        outputView.printMap(movedHistory);
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
