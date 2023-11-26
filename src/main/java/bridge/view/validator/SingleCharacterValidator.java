package bridge.view.validator;

import java.util.regex.Pattern;

public final class SingleCharacterValidator {
    private static final Pattern SINGLE_CHARACTER = Pattern.compile("[A-Za-z0-9가-힣]");  // 영어, 숫자, 한글 중 하나의 문자만 허용
    private static final String FORMAT_EXCEPTION_MESSAGE = "한글자의 영어, 숫자(정수), 한글만 입력 가능합니다.";

    private SingleCharacterValidator() {
    }

    public static void validate(String input) {
        if (!matchesPattern(input, SINGLE_CHARACTER)) {
            throw new IllegalArgumentException(FORMAT_EXCEPTION_MESSAGE);
        }
    }

    private static boolean matchesPattern(String input, Pattern pattern) {
        return pattern.matcher(input).matches();
    }
}
