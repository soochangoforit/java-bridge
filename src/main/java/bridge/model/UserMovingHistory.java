package bridge.model;

import java.util.ArrayList;
import java.util.List;

public class UserMovingHistory {
    private final List<MoveHistory> moveHistories;

    private UserMovingHistory(List<MoveHistory> moveHistories) {
        this.moveHistories = new ArrayList<>(moveHistories);
    }

    public static UserMovingHistory initialize() {
        return new UserMovingHistory(new ArrayList<>());
    }

    public void add(MoveHistory userMoveHistory) {
        moveHistories.add(userMoveHistory);
    }

    public void clearHistory() {
        moveHistories.clear();
    }

    public List<MoveHistory> getMoveHistories() {
        return new ArrayList<>(moveHistories);
    }
}
