package bridge.model;

import static bridge.model.MoveDirection.DOWN_MOVING;
import static bridge.model.MoveDirection.UP_MOVING;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

import java.util.stream.Stream;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.EnumSource;
import org.junit.jupiter.params.provider.MethodSource;

class MovedResultTest {

    @ParameterizedTest
    @MethodSource("provideValidMoveDirectionAndIsMoved")
    void 유효한_명령어를_통해서_이동_결과를_생성하면_예외가_발생하지_않는다(MoveDirection moveDirection, boolean isMoved) {
        assertDoesNotThrow(() -> MovedResult.of(moveDirection, isMoved));
    }

    @ParameterizedTest
    @EnumSource(value = MovedResult.class, names = {"CROSS_BRIDGE_UP", "CROSS_BRIDGE_DOWN"})
    void isMoved(MovedResult movedResult) {
        assertThat(movedResult.isMoved()).isTrue();
    }

    @ParameterizedTest
    @EnumSource(value = MovedResult.class, names = {"FAIL_CROSS_BRIDGE_UP", "FAIL_CROSS_BRIDGE_DOWN"})
    void isNotMoved() {
        assertThat(MovedResult.FAIL_CROSS_BRIDGE_UP.isNotMoved()).isTrue();
    }

    private static Stream<Arguments> provideValidMoveDirectionAndIsMoved() {
        return Stream.of(
                Arguments.of(UP_MOVING, true),
                Arguments.of(UP_MOVING, false),
                Arguments.of(DOWN_MOVING, true),
                Arguments.of(DOWN_MOVING, false)
        );
    }
}
