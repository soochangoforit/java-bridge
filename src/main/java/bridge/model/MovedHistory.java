package bridge.model;

import java.util.ArrayList;
import java.util.List;

public class MovedHistory {
    private final List<MovingResult> movingResults;

    private MovedHistory(List<MovingResult> movingResults) {
        this.movingResults = movingResults;
    }

    public static MovedHistory initialize() {
        return new MovedHistory(new ArrayList<>());
    }

    public void add(MovingResult movingResult) {
        movingResults.add(movingResult);
    }

    public void clear() {
        movingResults.clear();
    }

    public boolean isMovedAll() {
        return movingResults.stream()
                .allMatch(MovingResult::isMoved);
    }

    public List<MovingResult> getMovingResults() {
        return new ArrayList<>(movingResults);
    }
}
