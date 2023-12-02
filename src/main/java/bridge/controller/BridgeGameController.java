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
        BridgeSize bridgeSize = fetch(this::readBridgeSize);
        Bridge bridge = Bridge.create(bridgeMaker, bridgeSize);
        BridgeGame bridgeGame = BridgeGame.from(bridge);
        Player player = Player.initialize();
        while (bridgeGame.isInProgress()) {
            MovingDirection movingDirection = fetch(this::readMovingDirection);
            MovingResult movingResult = bridgeGame.move(player, movingDirection);
            outputView.printMap(player.getMovingResults());
            if (movingResult.isNotMoved()) {
                GameCommand gameCommand = fetch(this::readGameCommand);
                if (gameCommand.isQuit()) {
                    bridgeGame.endGame();
                }
                if (gameCommand.isRetry()) {
                    bridgeGame.retry();
                    player.retry();
                }
            }
        }

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
