package bridge;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;
import org.junit.jupiter.api.Test;

class BridgeMakerTest {

    @Test
    public void 길이만큼_다리를_생성할_수_있다() {
        BridgeNumberGenerator numberGenerator = () -> 1;
        BridgeMaker bridgeMaker = new BridgeMaker(numberGenerator);

        List<String> bridge = bridgeMaker.makeBridge(3);

        assertThat(bridge).hasSize(3);
    }

    @Test
    public void 다리_생성_테스트() {
        BridgeNumberGenerator numberGenerator = () -> 1;
        BridgeMaker bridgeMaker = new BridgeMaker(numberGenerator);

        List<String> bridge = bridgeMaker.makeBridge(3);

        assertThat(bridge).containsExactly("U", "U", "U");
    }

}
