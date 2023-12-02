package bridge.view;

import java.util.List;
import java.util.stream.Stream;
import bridge.view.validator.BlankValidator;
import bridge.view.validator.DigitsOnlyValidator;
import bridge.view.validator.EnglishOnlyValidator;
import camp.nextstep.edu.missionutils.Console;

/**
 * 사용자로부터 입력을 받는 역할을 한다.
 */
public class InputView {

    /**
     * 다리의 길이를 입력받는다.
     */
    public int readBridgeSize() {
        println("다리의 길이를 입력해주세요.");
        String rawBridgeSize = readLine();
        printEmptyLine();
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

    private void println(String message) {
        System.out.println(message);
    }

    private int convertToInt(String input) {
        try {
            return Integer.parseInt(input);
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("숫자로 변환할 수 없는 문자입니다.");
        }
    }

    private void printEmptyLine() {
        System.out.println();
    }

    /**
     * 사용자가 이동할 칸을 입력받는다.
     */
    public String readMoving() {
        println("이동할 칸을 선택해주세요. (위: U, 아래: D)");
        String rawMovingDirection = readLine();
        validateMovingDirection(rawMovingDirection);
        return rawMovingDirection;
    }

    private void validateMovingDirection(String rawMovingDirection) {
        BlankValidator.validate(rawMovingDirection);
        EnglishOnlyValidator.validate(rawMovingDirection);
    }

    /**
     * 사용자가 게임을 다시 시도할지 종료할지 여부를 입력받는다.
     */
    public String readGameCommand() {
        return null;
    }

    private void print(String message) {
        System.out.print(message);
    }

    private List<String> split(String format, String input) {
        return List.of(input.split(format));
    }

    private List<Integer> splitToInt(String delimiter, String input) {
        return Stream.of(input.split(delimiter))
                .map(Integer::parseInt)
                .toList();
    }
}
