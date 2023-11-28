package bridge.model;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

class RetryCommandTest {

    @ParameterizedTest
    @ValueSource(strings = {"R", "Q"})
    void 유효한_재시도_약어로_재시도_명령을_생성하면_예외가_발생하지_않는다(String command) {
        assertDoesNotThrow(() -> RetryCommand.from(command));
    }

    @ParameterizedTest
    @ValueSource(strings = {"r", "q"})
    void 유효하지_않은_재시도_약어로_재시도_명령을_생성하면_예외가_발생한다(String command) {
        assertThatThrownBy(() -> RetryCommand.from(command))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    void isEnd() {
        assertAll(
                () -> assertThat(RetryCommand.RETRY.isEnd()).isFalse(),
                () -> assertThat(RetryCommand.END.isEnd()).isTrue()
        );
    }

    @Test
    void isRetry() {
        assertAll(
                () -> assertThat(RetryCommand.RETRY.isRetry()).isTrue(),
                () -> assertThat(RetryCommand.END.isRetry()).isFalse()
        );
    }
}
