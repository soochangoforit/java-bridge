package bridge.model;

public class Player {
    private static final int DEFAULT_POSITION = 0;
    private static final int DEFAULT_TRY_COUNT = 1;

    private final MovedHistory movedHistory;
    private int currentPosition;
    private int tryCount;

    public Player(MovedHistory movedHistory, int currentPosition, int tryCount) {
        this.movedHistory = movedHistory;
        this.currentPosition = currentPosition;
        this.tryCount = tryCount;
    }

    public static Player initialize() {
        return new Player(MovedHistory.initialize(), DEFAULT_POSITION, DEFAULT_TRY_COUNT);
    }

    public MovingResult move(MovingDirection movingDirection, Bridge bridge) {
        boolean isMoved = bridge.isCrossed(currentPosition, movingDirection);
        MovingResult movingResult = MovingResult.of(isMoved, movingDirection);
        movedHistory.add(movingResult);
        if (isMoved) {
            ++currentPosition;
        }
        return movingResult;
    }

    public boolean isCrossedAll(Bridge bridge) {
        return bridge.isCrossedAll(currentPosition);
    }

    public void retry() {
        ++tryCount;
        currentPosition = 0;
        movedHistory.clear();
    }

    public MovedHistory getMovedHistory() {
        return movedHistory;
    }

    public int getTryCount() {
        return tryCount;
    }
}
