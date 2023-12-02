package bridge.controller;

import java.util.function.Supplier;
import bridge.BridgeMaker;
import bridge.model.Bridge;
import bridge.model.BridgeGame;
import bridge.model.BridgeSize;
import bridge.model.GameCommand;
import bridge.model.MovingDirection;
import bridge.model.MovingResult;
import bridge.model.Player;
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
        Bridge bridge = createBridge();
        BridgeGame bridgeGame = BridgeGame.from(bridge);
        Player player = Player.initialize();
        while (bridgeGame.isInProgress()) {
            MovingResult movingResult = play(bridgeGame, player);
            handleNotMoved(movingResult, bridgeGame, player);
        }
        outputView.printResult(player.getMovedHistory(), player.getTryCount());
    }

    private MovingResult play(BridgeGame bridgeGame, Player player) {
        MovingDirection movingDirection = fetch(this::readMovingDirection);
        MovingResult movingResult = bridgeGame.move(player, movingDirection);
        outputView.printMap(player.getMovedHistory().getMovingResults());
        return movingResult;
    }

    private void handleQuit(GameCommand gameCommand, BridgeGame bridgeGame) {
        if (gameCommand.isQuit()) {
            bridgeGame.endGame();
        }
    }

    private void handleRetry(GameCommand gameCommand, BridgeGame bridgeGame, Player player) {
        if (gameCommand.isRetry()) {
            bridgeGame.retry();
            player.retry();
        }
    }

    private void handleNotMoved(MovingResult movingResult, BridgeGame bridgeGame, Player player) {
        if (movingResult.isNotMoved()) {
            GameCommand gameCommand = fetch(this::readGameCommand);
            handleQuit(gameCommand, bridgeGame);
            handleRetry(gameCommand, bridgeGame, player);
        }
    }

    private Bridge createBridge() {
        BridgeSize bridgeSize = fetch(this::readBridgeSize);
        return Bridge.create(bridgeMaker, bridgeSize);
    }

    private GameCommand readGameCommand() {
        String rawGameCommand = inputView.readGameCommand();
        return GameCommand.from(rawGameCommand);
    }

    private MovingDirection readMovingDirection() {
        String rawMovingDirection = inputView.readMoving();
        return MovingDirection.from(rawMovingDirection);
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
