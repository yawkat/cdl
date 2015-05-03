package at.yawk.cdl;

import lombok.Getter;
import lombok.Setter;

/**
 * @author yawkat
 */
@Getter
@Setter
public abstract class AbstractEditor<T> implements Editor<T> {
    private String description;

    protected static void expect(boolean expectation, String messageFormat, Object... args) throws ValidationException {
        if (!expectation) {
            throw new ValidationException(messageFormat, args);
        }
    }
}
