package bridge.model;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;
import java.util.stream.Stream;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import bridge.BridgeMaker;

class BridgeGameTest {

    @Test
    void 게임_종료된_경우에는_더_이상_진행하지_못한다() {
        BridgeGame bridgeGame = new BridgeGame(Bridge.from(List.of(
                BridgeElement.from("U"),
                BridgeElement.from("U"),
                BridgeElement.from("U")
        )), BridgeGameStatus.IN_PROGRESS);

        bridgeGame.endGame();

        assertThat(bridgeGame.isInProgress()).isFalse();
    }

    @Test
    void 게임_종료된_경우가_아닌_경우에는_진행할_수_있다() {
        BridgeGame bridgeGame = new BridgeGame(Bridge.from(List.of(
                BridgeElement.from("U"),
                BridgeElement.from("U"),
                BridgeElement.from("U")
        )), BridgeGameStatus.IN_PROGRESS);

        assertThat(bridgeGame.isInProgress()).isTrue();
    }

    @Test
    void create() {
        BridgeMaker bridgeMaker = new BridgeMaker(() -> 1);
        BridgeSize bridgeSize = BridgeSize.from(3);
        BridgeGame expectedBridgeGame = new BridgeGame(Bridge.from(List.of(
                BridgeElement.from("U"),
                BridgeElement.from("U"),
                BridgeElement.from("U")
        )), BridgeGameStatus.IN_PROGRESS);

        BridgeGame bridgeGame = BridgeGame.create(bridgeMaker, bridgeSize);

        assertThat(bridgeGame).usingRecursiveComparison().isEqualTo(expectedBridgeGame);
    }

    @ParameterizedTest
    @MethodSource("moveMethodTestData")
    void move(MoveDirection moveDirection, int positionIndex, MovedResult expected) {
        Bridge bridge = Bridge.from(List.of(BridgeElement.UP, BridgeElement.UP, BridgeElement.UP));
        BridgeGame bridgeGame = new BridgeGame(bridge, BridgeGameStatus.IN_PROGRESS);
        Player player = new Player(MovedHistory.initialize(), new MovePosition(positionIndex),
                TryCount.defaultTryCount());

        MovedResult movedResult = bridgeGame.move(player, moveDirection);

        assertThat(movedResult).isSameAs(expected);
    }

    private static Stream<Arguments> moveMethodTestData() {
        return Stream.of(
                Arguments.of(MoveDirection.UP_MOVING, 0, MovedResult.CROSS_BRIDGE_UP),
                Arguments.of(MoveDirection.DOWN_MOVING, 0, MovedResult.FAIL_CROSS_BRIDGE_DOWN),
                Arguments.of(MoveDirection.UP_MOVING, 1, MovedResult.CROSS_BRIDGE_UP),
                Arguments.of(MoveDirection.DOWN_MOVING, 1, MovedResult.FAIL_CROSS_BRIDGE_DOWN),
                Arguments.of(MoveDirection.UP_MOVING, 2, MovedResult.CROSS_BRIDGE_UP),
                Arguments.of(MoveDirection.DOWN_MOVING, 2, MovedResult.FAIL_CROSS_BRIDGE_DOWN)
        );
    }
}
