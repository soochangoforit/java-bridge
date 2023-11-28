package bridge.model;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;
import org.junit.jupiter.api.Test;

class MovedHistoryTest {

    @Test
    void 모든_다리_이동_성공_확인() {
        List<MovedResult> successfulMoves = List.of(
                MovedResult.CROSS_BRIDGE_UP,
                MovedResult.CROSS_BRIDGE_DOWN,
                MovedResult.CROSS_BRIDGE_UP);
        MovedHistory movedHistory = new MovedHistory(successfulMoves);

        assertThat(movedHistory.isMovedAllBridge()).isTrue();
    }

    @Test
    void 일부_다리_이동_실패_확인() {
        List<MovedResult> mixedMoves = List.of(
                MovedResult.CROSS_BRIDGE_UP,
                MovedResult.FAIL_CROSS_BRIDGE_DOWN,
                MovedResult.CROSS_BRIDGE_UP);
        MovedHistory movedHistory = new MovedHistory(mixedMoves);

        assertThat(movedHistory.isMovedAllBridge()).isFalse();
    }

    @Test
    void 기록_초기화_테스트() {
        MovedHistory movedHistory = MovedHistory.initialize();
        movedHistory.add(MovedResult.CROSS_BRIDGE_UP);

        movedHistory.clearHistory();

        assertThat(movedHistory.getMoveHistories()).isEmpty();
    }

    @Test
    void 초기_이동_기록_생성_테스트() {
        MovedHistory movedHistory = MovedHistory.initialize();

        assertThat(movedHistory.getMoveHistories()).isEmpty();
    }

    @Test
    void 이동_결과_추가_테스트() {
        MovedHistory movedHistory = MovedHistory.initialize();
        movedHistory.add(MovedResult.CROSS_BRIDGE_UP);

        assertThat(movedHistory.getMoveHistories()).containsExactly(MovedResult.CROSS_BRIDGE_UP);
    }
}
