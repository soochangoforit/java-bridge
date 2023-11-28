package bridge.model;

public final class Player {
    private final MovedHistory movedHistory;
    private MovePosition movePosition;
    private TryCount tryCount;

    Player(MovedHistory movedHistory, MovePosition movePosition, TryCount tryCount) {
        this.movedHistory = movedHistory;
        this.movePosition = movePosition;
        this.tryCount = tryCount;
    }

    public static Player initialize() {
        MovedHistory movedHistory = MovedHistory.initialize();
        TryCount tryCount = TryCount.defaultTryCount();
        MovePosition movePosition = MovePosition.defaultPosition();

        return new Player(movedHistory, movePosition, tryCount);
    }

    public void markMovedHistory(MovedResult userMovedResult) {
        movedHistory.add(userMovedResult);
    }

    public boolean move(MoveDirection moveDirection, Bridge bridge) {
        boolean isMovable = bridge.isMovable(movePosition, moveDirection);
        if (isMovable) {
            movePosition = movePosition.move();
        }
        return isMovable;
    }

    public boolean crossAllBridge(Bridge bridge) {
        return movePosition.isSame(bridge.getSize());
    }

    public void resetAll() {
        movedHistory.clearHistory();
        movePosition = MovePosition.defaultPosition();
        tryCount = tryCount.increase();
    }

    public TryCount getTryCount() {
        return tryCount;
    }

    public MovedHistory getMovedHistory() {
        return movedHistory;
    }
}
