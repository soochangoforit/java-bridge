package bridge.model;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;

import org.junit.jupiter.api.Test;

class BridgeGameStatusTest {

    @Test
    void 다리건너기_게임이_진행중인지_알_수_있다() {
        assertAll(
                () -> assertThat(BridgeGameStatus.IN_PROGRESS.isInProgress()).isTrue(),
                () -> assertThat(BridgeGameStatus.FINISHED.isInProgress()).isFalse()
        );
    }
}
