package bridge.model;

import java.util.ArrayList;
import java.util.List;

public class UserMovingHistory {
    private final List<MovedHistory> moveHistories;

    private UserMovingHistory(List<MovedHistory> moveHistories) {
        this.moveHistories = new ArrayList<>(moveHistories);
    }

    public static UserMovingHistory initialize() {
        return new UserMovingHistory(new ArrayList<>());
    }

    public void add(MovedHistory userMovedHistory) {
        moveHistories.add(userMovedHistory);
    }

    public void clearHistory() {
        moveHistories.clear();
    }

    public List<MovedHistory> getMoveHistories() {
        return new ArrayList<>(moveHistories);
    }
}
