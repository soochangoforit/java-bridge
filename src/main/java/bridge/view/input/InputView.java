package bridge.view.input;

import bridge.view.print.Printer;
import bridge.view.validator.BlankValidator;
import bridge.view.validator.DigitsOnlyValidator;
import bridge.view.validator.SingleCharacterValidator;
import camp.nextstep.edu.missionutils.Console;

/**
 * 사용자로부터 입력을 받는 역할을 한다.
 */
public class InputView {
    private final Printer printer;

    public InputView(Printer printer) {
        this.printer = printer;
    }

    /**
     * 다리의 길이를 입력받는다.
     */
    public int readBridgeSize() {
        printer.println("다리의 길이를 입력해주세요.");
        String rawBridgeSize = readLine();
        printer.printEmptyLine();
        validateBridgeSize(rawBridgeSize);
        return convertToInt(rawBridgeSize);
    }

    private void validateBridgeSize(String rawBridgeSize) {
        BlankValidator.validate(rawBridgeSize);
        DigitsOnlyValidator.validate(rawBridgeSize);
    }

    private String readLine() {
        return Console.readLine().trim();
    }

    private int convertToInt(String input) {
        try {
            return Integer.parseInt(input);
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("숫자로 변환할 수 없는 문자입니다.");
        }
    }

    /**
     * 사용자가 이동할 칸을 입력받는다.
     */
    public String readMoving() {
        printer.println("이동할 칸을 선택해주세요. (위: U, 아래: D)");
        String rawMovingCommand = readLine();
        validateMovingCommand(rawMovingCommand);
        return rawMovingCommand;
    }

    private void validateMovingCommand(String rawMovingCommand) {
        BlankValidator.validate(rawMovingCommand);
        SingleCharacterValidator.validate(rawMovingCommand);
    }

    /**
     * 사용자가 게임을 다시 시도할지 종료할지 여부를 입력받는다.
     */
    public String readGameCommand() {
        printer.println("게임을 다시 시도할지 여부를 입력해주세요. (재시도: R, 종료: Q)");
        String rawRetryCommand = readLine();
        validateRetryCommand(rawRetryCommand);
        return rawRetryCommand;
    }

    private void validateRetryCommand(String rawRetryCommand) {
        BlankValidator.validate(rawRetryCommand);
        SingleCharacterValidator.validate(rawRetryCommand);
    }
}
