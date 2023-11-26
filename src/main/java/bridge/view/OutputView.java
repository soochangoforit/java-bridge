package bridge.view;

import static bridge.model.MovingCommand.DOWN_MOVING;
import static bridge.model.MovingCommand.UP_MOVING;

import java.util.ArrayList;
import java.util.List;
import bridge.model.MoveHistory;

/**
 * 사용자에게 게임 진행 상황과 결과를 출력하는 역할을 한다.
 */
public class OutputView {
    private static final String EXCEPTION_FORMAT = "[ERROR] %s";

    /**
     * 현재까지 이동한 다리의 상태를 정해진 형식에 맞춰 출력한다.
     * <p>
     * 출력을 위해 필요한 메서드의 인자(parameter)는 자유롭게 추가하거나 변경할 수 있다.
     */
    public void printMap(List<MoveHistory> moveHistories) {
        String upBridge = String.format("[ %s ]", makeUpBridge(moveHistories));
        String downBridge = String.format("[ %s ]", makeDownBridge(moveHistories));
        String totalBridge = String.format("%s\n%s", upBridge, downBridge);
        println(totalBridge);
        printEmptyLine();
    }

    private String makeUpBridge(List<MoveHistory> moveHistories) {
        List<String> upBridge = new ArrayList<>();
        for (MoveHistory moveHistory : moveHistories) {
            if (moveHistory.getMovingCommand() == UP_MOVING) {
                upBridge.add(moveHistory.getMovable() ? "O" : "X");
            }

            if (moveHistory.getMovingCommand() == DOWN_MOVING) {
                upBridge.add(" ");
            }
        }
        return String.join(" | ", upBridge);
    }

    private String makeDownBridge(List<MoveHistory> moveHistories) {
        List<String> downBridge = new ArrayList<>();
        for (MoveHistory moveHistory : moveHistories) {
            if (moveHistory.getMovingCommand() == DOWN_MOVING) {
                downBridge.add(moveHistory.getMovable() ? "O" : "X");
            }

            if (moveHistory.getMovingCommand() == UP_MOVING) {
                downBridge.add(" ");
            }
        }
        return String.join(" | ", downBridge);
    }

    private void println(String message) {
        System.out.println(message);
    }

    private void printEmptyLine() {
        println("");
    }

    /**
     * 게임의 최종 결과를 정해진 형식에 맞춰 출력한다.
     * <p>
     * 출력을 위해 필요한 메서드의 인자(parameter)는 자유롭게 추가하거나 변경할 수 있다.
     */
    public void printResult() {
    }

    public void printStartMessage() {
        println("다리 건너기 게임을 시작합니다.");
        printEmptyLine();
    }

    public void printExceptionMessage(String message) {
        println(String.format(EXCEPTION_FORMAT, message));
    }
}
