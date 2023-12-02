package bridge.model;

public class MovingResult {
    private final boolean isMoved;
    private final MovingDirection movingDirection;

    public MovingResult(boolean isMoved, MovingDirection movingDirection) {
        this.isMoved = isMoved;
        this.movingDirection = movingDirection;
    }

    public static MovingResult of(boolean isMoved, MovingDirection movingDirection) {
        return new MovingResult(isMoved, movingDirection);
    }

    public boolean isMovedDown() {
        return movingDirection.isMovedDown();
    }

    public boolean isMovedUp() {
        return movingDirection.isMovedUp();
    }

    public boolean isNotMoved() {
        return !isMoved;
    }

    public boolean isMoved() {
        return isMoved;
    }
}
