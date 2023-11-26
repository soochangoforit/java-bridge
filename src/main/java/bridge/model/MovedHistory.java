package bridge.model;

import java.util.ArrayList;
import java.util.List;

public class MovedHistory {
    private final List<MovedResult> moveHistories;

    private MovedHistory(List<MovedResult> moveHistories) {
        this.moveHistories = new ArrayList<>(moveHistories);
    }

    public static MovedHistory initialize() {
        return new MovedHistory(new ArrayList<>());
    }

    public void add(MovedResult userMovedResult) {
        moveHistories.add(userMovedResult);
    }

    public void clearHistory() {
        moveHistories.clear();
    }

    public boolean isMovedAllBridge() {
        return moveHistories.stream()
                .allMatch(MovedResult::isMovable);
    }

    public List<MovedResult> getMoveHistories() {
        return new ArrayList<>(moveHistories);
    }
}
