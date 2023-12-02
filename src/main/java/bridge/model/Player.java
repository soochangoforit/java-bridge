package bridge.model;

import java.util.ArrayList;
import java.util.List;

public class Player {
    private final List<MovingResult> movingResults;
    private int currentPosition;
    private int tryCount;

    public Player(List<MovingResult> movingResults, int currentPosition, int tryCount) {
        this.movingResults = movingResults;
        this.currentPosition = currentPosition;
        this.tryCount = tryCount;
    }

    public static Player initialize() {
        return new Player(new ArrayList<>(), 0, 1);
    }

    public MovingResult move(MovingDirection movingDirection, Bridge bridge) {
        BridgeElement bridgeElement = bridge.getElement(currentPosition);
        boolean isMoved = movingDirection.canMove(bridgeElement);
        MovingResult movingResult = MovingResult.of(isMoved, movingDirection);
        movingResults.add(movingResult);
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
        movingResults.clear();
    }

    public List<MovingResult> getMovingResults() {
        return movingResults;
    }
}
