package bridge.model;

import java.util.stream.Stream;

public enum GameCommand {
    RETRY("R"),
    QUIT("Q");

    private final String symbol;

    GameCommand(String symbol) {
        this.symbol = symbol;
    }

    public static GameCommand from(String gameCommandSymbol) {
        return Stream.of(values())
                .filter(gameCommand -> gameCommand.symbol.equals(gameCommandSymbol))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("게임 커멘드에 맞지 않는 문자입니다."));
    }

    public boolean isRetry() {
        return this == RETRY;
    }

    public boolean isQuit() {
        return this == QUIT;
    }
}
