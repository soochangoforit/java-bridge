package bridge.view.validator;

import java.util.regex.Pattern;

public final class EnglishOnlyValidator {
    private static final Pattern ENGLISH_ONLY = Pattern.compile("[A-Za-z]+");
    private static final String FORMAT_EXCEPTION_MESSAGE = "영어만 입력 가능합니다.";

    private EnglishOnlyValidator() {
    }

    public static void validate(String input) {
        if (!matchesPattern(input, ENGLISH_ONLY)) {
            throw new IllegalArgumentException(FORMAT_EXCEPTION_MESSAGE);
        }
    }

    private static boolean matchesPattern(String input, Pattern pattern) {
        return pattern.matcher(input).matches();
    }
}
