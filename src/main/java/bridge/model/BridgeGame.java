package bridge.model;

import java.util.List;
import bridge.BridgeMaker;

/**
 * 다리 건너기 게임을 관리하는 클래스
 */
public final class BridgeGame {
    private final Bridge bridge;
    private BridgeGameStatus gameStatus;

    private BridgeGame(Bridge bridge, BridgeGameStatus gameStatus) {
        this.bridge = bridge;
        this.gameStatus = gameStatus;
    }

    public static BridgeGame create(BridgeMaker bridgeMaker, BridgeSize bridgeSize) {
        List<String> bridgeElementSymbols = bridgeMaker.makeBridge(bridgeSize.getSize());
        List<BridgeElement> bridgeElements = convertToBridgeElements(bridgeElementSymbols);
        Bridge bridge = Bridge.from(bridgeElements);

        return new BridgeGame(bridge, BridgeGameStatus.IN_PROGRESS);
    }

    private static List<BridgeElement> convertToBridgeElements(List<String> bridgeElementSymbols) {
        return bridgeElementSymbols.stream()
                .map(BridgeElement::from)
                .toList();
    }

    /**
     * 사용자가 칸을 이동할 때 사용하는 메서드
     * <p>
     * 이동을 위해 필요한 메서드의 반환 타입(return type), 인자(parameter)는 자유롭게 추가하거나 변경할 수 있다.
     */
    public MovedResult move(Player player, MoveDirection moveDirection) {
        boolean isMoved = player.move(moveDirection, bridge);
        if (player.crossAllBridge(bridge)) {
            gameStatus = BridgeGameStatus.FINISHED;
        }

        return MovedResult.of(moveDirection, isMoved);
    }

    /**
     * 사용자가 게임을 다시 시도할 때 사용하는 메서드
     * <p>
     * 재시작을 위해 필요한 메서드의 반환 타입(return type), 인자(parameter)는 자유롭게 추가하거나 변경할 수 있다.
     */
    public void retry() {
    }

    public void endGame() {
        gameStatus = BridgeGameStatus.FINISHED;
    }

    public boolean isInProgress() {
        return gameStatus.isInProgress();
    }

}
