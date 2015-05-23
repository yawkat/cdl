package at.yawk.cdl;

/**
 * @author yawkat
 */
public class ValidationException extends Exception {
    public ValidationException(String message) {
        super(message);
    }

    public ValidationException(String message, Object... formatArgs) {
        this(format(message, formatArgs));
    }

    public ValidationException(String message, Throwable cause) {
        super(message, cause);
    }

    private static String format(String fmt, Object[] args) {
        StringBuilder result = new StringBuilder();
        int argIndex = 0;
        int matchStart = 0;
        int matchEnd;
        while ((matchEnd = fmt.indexOf("{}", matchStart)) != -1) {
            result.append(fmt, matchStart, matchEnd);
            result.append(args[argIndex++]);
            matchStart = matchEnd + 2;
        }
        result.append(fmt, matchStart, fmt.length());
        return result.toString();
    }
}
