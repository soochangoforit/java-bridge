package bridge.view;

import java.util.ArrayList;
import java.util.List;
import bridge.model.MovedHistory;
import bridge.model.MovingResult;

/**
 * 사용자에게 게임 진행 상황과 결과를 출력하는 역할을 한다.
 */
public class OutputView {
    private static final String EXCEPTION_FORMAT = "[ERROR] %s";

    public void printExceptionMessage(String message) {
        println(String.format(EXCEPTION_FORMAT, message));
    }

    private void println(String message) {
        System.out.println(message);
    }

    public void printStartMessage() {
        println("다리 건너기 게임을 시작합니다.");
        printEmptyLine();
    }

    private void printEmptyLine() {
        System.out.println();
    }

    /**
     * 현재까지 이동한 다리의 상태를 정해진 형식에 맞춰 출력한다.
     * <p>
     * 출력을 위해 필요한 메서드의 인자(parameter)는 자유롭게 추가하거나 변경할 수 있다.
     */

    /**
     * 게임의 최종 결과를 정해진 형식에 맞춰 출력한다.
     * <p>
     * 출력을 위해 필요한 메서드의 인자(parameter)는 자유롭게 추가하거나 변경할 수 있다.
     */
    public void printResult(MovedHistory movedHistory, int tryCount) {
        println("최종 게임 결과");
        printMap(movedHistory.getMovingResults());
        println(String.format("게임 성공 여부: %s", checkGameWinning(movedHistory)));
        println(String.format("총 시도한 횟수: %d", tryCount));
    }

    private String checkGameWinning(MovedHistory movedHistory) {
        boolean isMovedAllBridge = movedHistory.isMovedAll();
        if (isMovedAllBridge) {
            return "성공";
        }
        return "실패";
    }

    public void printMap(List<MovingResult> movingResults) {
        List<String> upBridgeRecords = makeUpBridge(movingResults);
        String upBridge = String.join(" | ", upBridgeRecords);

        List<String> downBridgeRecords = makeDownBridge(movingResults);
        String downBridge = String.join(" | ", downBridgeRecords);

        println(String.format("[ %s ]\n[ %s ]", upBridge, downBridge));
        printEmptyLine();
    }

    private List<String> makeDownBridge(List<MovingResult> movingResults) {
        List<String> downBridgeRecords = new ArrayList<>();
        for (MovingResult movingResult : movingResults) {
            markDownBridge(movingResult, downBridgeRecords);
        }
        return downBridgeRecords;
    }

    private void markDownBridge(MovingResult movingResult, List<String> downBridgeRecords) {
        if (movingResult.isMovedDown()) {
            mark(movingResult, downBridgeRecords);
        }
        if (movingResult.isMovedUp()) {
            markEmptySpace(downBridgeRecords);
        }
    }

    private List<String> makeUpBridge(List<MovingResult> movingResults) {
        List<String> upBridgeRecords = new ArrayList<>();
        for (MovingResult movingResult : movingResults) {
            markUpBridge(movingResult, upBridgeRecords);
        }
        return upBridgeRecords;
    }

    private void markUpBridge(MovingResult movingResult, List<String> upBridgeRecords) {
        if (movingResult.isMovedUp()) {
            mark(movingResult, upBridgeRecords);
        }
        if (movingResult.isMovedDown()) {
            markEmptySpace(upBridgeRecords);
        }
    }

    private void markEmptySpace(List<String> bridge) {
        bridge.add(" ");
    }

    private void mark(MovingResult movingResult, List<String> bridgeRecord) {
        if (movingResult.isMoved()) {
            bridgeRecord.add("O");
        }
        if (movingResult.isNotMoved()) {
            bridgeRecord.add("X");
        }
    }
}
