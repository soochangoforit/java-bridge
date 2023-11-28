package bridge.model;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.assertAll;

import java.util.stream.Stream;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

class BridgeElementTest {

    @ParameterizedTest
    @MethodSource("provideValidNumberAndSymbol")
    void 유효한_숫자로_다리_요소_심볼을_가져오는경우_예외가_발생하지_않는다(int number, String expectedSymbol) {
        String actualSymbol = BridgeElement.getBridgeElementSymbol(number);

        assertThat(actualSymbol).isEqualTo(expectedSymbol);
    }

    @Test
    void 유효하지_않은_숫자로_다리_요소_심볼을_가져오는경우_예외가_발생한다() {
        assertThatThrownBy(() -> BridgeElement.getBridgeElementSymbol(-1))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @ParameterizedTest
    @MethodSource("provideValidBridgeElementSymbol")
    void 유효한_심볼로_다리_요소를_가져오면_예외가_발생하지_않는다(String symbol, BridgeElement expectedBridgeElement) {
        BridgeElement actualBridgeElement = BridgeElement.from(symbol);

        assertThat(actualBridgeElement).isEqualTo(expectedBridgeElement);
    }

    @Test
    void isDown() {
        assertAll(
                () -> assertThat(BridgeElement.DOWN.isDown()).isTrue(),
                () -> assertThat(BridgeElement.UP.isDown()).isFalse()
        );
    }

    @Test
    void isUp() {
        assertAll(
                () -> assertThat(BridgeElement.UP.isUp()).isTrue(),
                () -> assertThat(BridgeElement.DOWN.isUp()).isFalse()
        );
    }

    private static Stream<Arguments> provideValidBridgeElementSymbol() {
        return Stream.of(
                Arguments.of("U", BridgeElement.UP),
                Arguments.of("D", BridgeElement.DOWN)
        );
    }

    private static Stream<Arguments> provideValidNumberAndSymbol() {
        return Stream.of(
                Arguments.of(1, "U"),
                Arguments.of(0, "D")
        );
    }
}
