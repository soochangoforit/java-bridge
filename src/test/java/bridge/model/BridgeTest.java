package bridge.model;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;
import java.util.stream.Stream;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

class BridgeTest {

    @ParameterizedTest
    @MethodSource("이동_가능성_테스트_데이터")
    void 이동하고자하는_위치와_방향을통해서_이동할수있는지_테스트(int positionIndex, MoveDirection moveDirection, boolean expected) {
        Bridge bridge = Bridge.from(List.of(BridgeElement.UP, BridgeElement.DOWN, BridgeElement.UP));
        MovePosition movePosition = new MovePosition(positionIndex);

        boolean actual = bridge.isMovable(movePosition, moveDirection);

        assertThat(actual).isEqualTo(expected);
    }

    private static Stream<Arguments> 이동_가능성_테스트_데이터() {
        return Stream.of(
                Arguments.of(0, MoveDirection.UP_MOVING, true),
                Arguments.of(0, MoveDirection.DOWN_MOVING, false),
                Arguments.of(1, MoveDirection.UP_MOVING, false),
                Arguments.of(1, MoveDirection.DOWN_MOVING, true)
        );
    }
}
