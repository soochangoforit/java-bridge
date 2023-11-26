package bridge.model;

public final class Player {
    private final MovedHistory movedHistory;
    private TryCount tryCount;

    private Player(TryCount tryCount, MovedHistory movedHistory) {
        this.tryCount = tryCount;
        this.movedHistory = movedHistory;
    }

    public static Player initialize() {
        TryCount tryCount = TryCount.firstTryCount();
        MovedHistory movedHistory = MovedHistory.initialize();

        return new Player(tryCount, movedHistory);
    }

    public void increaseTryCount() {
        tryCount = tryCount.increase();
    }

    public void clearHistory() {
        movedHistory.clearHistory();
    }

    public void markMovedHistory(MovedResult userMovedResult) {
        movedHistory.add(userMovedResult);
    }

    public TryCount getTryCount() {
        return tryCount;
    }

    public MovedHistory getMovedHistory() {
        return movedHistory;
    }
}
