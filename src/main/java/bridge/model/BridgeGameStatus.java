package bridge.model;

public enum BridgeGameStatus {
    IN_PROGRESS,
    FINISHED;

    public boolean isInProgress() {
        return this == IN_PROGRESS;
    }
}
