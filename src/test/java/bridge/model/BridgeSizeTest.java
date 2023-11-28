package bridge.model;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

class BridgeSizeTest {

    @ParameterizedTest
    @ValueSource(ints = {3, 20})
    public void 유효한_크기의_값으로_다리_사이즈를_생성하면_예외가_발생하지_않는다(int size) {
        assertDoesNotThrow(() -> BridgeSize.from(size));
    }

    @ParameterizedTest
    @ValueSource(ints = {2, 21})
    public void 유효하지_않은_크기의_값으로_다리_사이즈를_생성하면_예외가_발생한다(int size) {
        assertThatThrownBy(() -> BridgeSize.from(size))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    public void 같은_크기의_다리_크기_객체_비교() {
        // given
        BridgeSize size1 = BridgeSize.from(5);
        BridgeSize size2 = BridgeSize.from(5);

        // when / then
        assertThat(size1).isEqualTo(size2);
    }

    @Test
    public void 다른_크기의_다리_크기_객체_비교() {
        // given
        BridgeSize size1 = BridgeSize.from(5);
        BridgeSize size2 = BridgeSize.from(6);

        // when / then
        assertThat(size1).isNotEqualTo(size2);
    }

    @Test
    public void 동일한_객체의_hashcode_비교() {
        // given
        BridgeSize size1 = BridgeSize.from(5);
        BridgeSize size2 = BridgeSize.from(5);

        // when / then
        assertThat(size1.hashCode()).isEqualTo(size2.hashCode());
    }
}
