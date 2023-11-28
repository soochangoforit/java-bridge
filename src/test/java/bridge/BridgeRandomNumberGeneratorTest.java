package bridge;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.RepeatedTest;

class BridgeRandomNumberGeneratorTest {

    @RepeatedTest(10)
    public void 생성된_숫자_검증() {
        BridgeRandomNumberGenerator generator = new BridgeRandomNumberGenerator();

        int generatedNumber = generator.generate();

        assertThat(generatedNumber).isIn(0, 1);
    }
}
