package bridge.model;

import java.util.stream.Stream;

public enum RetryCommand {
    RETRY("R"),
    END("Q");

    private final String command;

    RetryCommand(String command) {
        this.command = command;
    }

    public static RetryCommand from(String command) {
        return Stream.of(values())
                .filter(retryCommand -> retryCommand.command.equals(command))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("재시도 여부에 적합하지 않은 명령어입니다."));
    }

    public boolean isRetry() {
        return this == RETRY;
    }
}
