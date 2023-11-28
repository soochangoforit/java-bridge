package bridge.model;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

class MovePositionTest {

    @Test
    void 초기_위치값을_갖는_객체를_생성할_수_있다() {
        MovePosition expectedMovePosition = new MovePosition(0);

        MovePosition actualMovePosition = MovePosition.defaultPosition();

        assertThat(expectedMovePosition).isEqualTo(actualMovePosition);
    }

    @ParameterizedTest
    @ValueSource(ints = {0, 1})
    void 유효한_위치값을_갖는_객체를_생성할_수_있다(int position) {
        MovePosition expectedMovePosition = new MovePosition(position);

        MovePosition actualMovePosition = new MovePosition(position);

        assertThat(expectedMovePosition).isEqualTo(actualMovePosition);
    }

    @ParameterizedTest
    @ValueSource(ints = {-1, -2})
    void 유효한_위치값을_갖지_않는_객체를_생성할_수_없다(int position) {
        assertThatThrownBy(() -> new MovePosition(position))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    void 같은_위치값을_갖는지_테스트() {
        MovePosition movePosition = new MovePosition(0);

        boolean actual = movePosition.isSame(0);

        assertThat(actual).isTrue();
    }

    @Test
    void 다른_위치값을_갖는지_테스트() {
        MovePosition movePosition = new MovePosition(0);

        boolean actual = movePosition.isSame(1);

        assertThat(actual).isFalse();
    }

    @Test
    void 움직이는_경우_위치_값이_증가한다() {
        MovePosition movePosition = new MovePosition(0);

        MovePosition actualMovePosition = movePosition.move();

        assertThat(actualMovePosition).isEqualTo(new MovePosition(1));
    }

    @Test
    void 같은_위치_값을_갖는_객체는_같은_객체이다() {
        MovePosition movePosition = new MovePosition(0);

        assertThat(movePosition).isEqualTo(new MovePosition(0));
    }

    @Test
    void 다른_위치_값을_갖는_객체는_다른_객체이다() {
        MovePosition movePosition = new MovePosition(0);

        assertThat(movePosition).isNotEqualTo(new MovePosition(1));
    }

    @Test
    void 같은_위치_값을_갖는_객체는_같은_해시코드를_갖는다() {
        MovePosition movePosition = new MovePosition(0);

        assertThat(movePosition).hasSameHashCodeAs(new MovePosition(0));
    }
}
