package bridge.model;

/**
 * 다리 건너기 게임을 관리하는 클래스
 */
public class BridgeGame {
    private final Bridge bridge;
    private boolean inProgress;

    private BridgeGame(Bridge bridge, boolean inProgress) {
        this.bridge = bridge;
        this.inProgress = inProgress;
    }

    public static BridgeGame from(Bridge bridge) {
        return new BridgeGame(bridge, true);
    }

    /**
     * 사용자가 칸을 이동할 때 사용하는 메서드
     * <p>
     * 이동을 위해 필요한 메서드의 반환 타입(return type), 인자(parameter)는 자유롭게 추가하거나 변경할 수 있다.
     */
    public void move(Player player, MovingDirection movingDirection) {
        boolean isMoved = player.move(movingDirection, bridge);
        if (isMoved) {
            boolean isCrossedAll = player.isCrossedAll(bridge);
            finishGame(isCrossedAll);
        }
    }

    private void finishGame(boolean isCrossedAll) {
        if (isCrossedAll) {
            inProgress = false;
        }
    }

    /**
     * 사용자가 게임을 다시 시도할 때 사용하는 메서드
     * <p>
     * 재시작을 위해 필요한 메서드의 반환 타입(return type), 인자(parameter)는 자유롭게 추가하거나 변경할 수 있다.
     */
    public void retry() {
    }

    public boolean isInProgress() {
        return inProgress;
    }
}
