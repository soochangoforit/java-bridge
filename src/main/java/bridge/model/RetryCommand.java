package bridge.model;

import java.util.stream.Stream;

public enum RetryCommand {
    RETRY("R"),
    END("Q");

    private static final String INVALID_RETRY_COMMAND = "다리 이동에 적합하지 않은 명령어입니다.";
    
    private final String command;

    RetryCommand(String command) {
        this.command = command;
    }

    public static RetryCommand from(String command) {
        return Stream.of(values())
                .filter(retryCommand -> retryCommand.command.equals(command))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException(INVALID_RETRY_COMMAND));
    }

    public boolean isEnd() {
        return this == END;
    }

    public boolean isRetry() {
        return this == RETRY;
    }
}
