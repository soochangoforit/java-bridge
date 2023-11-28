package bridge.model;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

class MoveDirectionTest {

    @ParameterizedTest
    @ValueSource(strings = {"U", "D"})
    void 유효한_약어로_이동_방향을_생성하면_예외가_발생하지_않는다(String command) {
        assertDoesNotThrow(() -> MoveDirection.from(command));
    }

    @ParameterizedTest
    @ValueSource(strings = {"u", "d"})
    void 유효하지_않은_약어로_이동_방향을_생성하면_예외가_발생한다(String command) {
        assertThatThrownBy(() -> MoveDirection.from(command))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    void 이동하고자_하는_방향에_맞게_다리를_건널_수_있는_테스트() {
        assertAll(
                () -> assertThat(MoveDirection.UP_MOVING.canMove(BridgeElement.UP)).isTrue(),
                () -> assertThat(MoveDirection.DOWN_MOVING.canMove(BridgeElement.DOWN)).isTrue()
        );
    }

    @Test
    void 이동하고자_하는_방향에_맞게_다리를_건널_수_없는_테스트() {
        assertAll(
                () -> assertThat(MoveDirection.UP_MOVING.canMove(BridgeElement.DOWN)).isFalse(),
                () -> assertThat(MoveDirection.DOWN_MOVING.canMove(BridgeElement.UP)).isFalse()
        );
    }

}

