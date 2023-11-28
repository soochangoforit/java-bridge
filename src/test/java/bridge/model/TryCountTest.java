package bridge.model;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

class TryCountTest {

    @ParameterizedTest
    @ValueSource(ints = {-1, 0})
    void 유효하지_않는_시도_횟수를_통해서_객체를_생성하면_예외가_발생한다(int tryCount) {
        assertThatThrownBy(() -> new TryCount(tryCount))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @ParameterizedTest
    @ValueSource(ints = {1, 2})
    void 유효한_시도_횟수를_통해서_객체를_생성하면_예외가_발생하지_않는다(int tryCount) {
        assertDoesNotThrow(() -> new TryCount(tryCount));
    }

    @Test
    void 시도횟수는_기본값을_가질_수_있다() {
        TryCount expectedTryCount = new TryCount(1);

        TryCount actualTryCount = TryCount.defaultTryCount();

        assertThat(expectedTryCount).isEqualTo(actualTryCount);
    }

    @Test
    void 시도횟수는_증가할_수_있다() {
        TryCount expectedTryCount = new TryCount(2);

        TryCount actualTryCount = new TryCount(1).increase();

        assertThat(expectedTryCount).isEqualTo(actualTryCount);
    }
}
