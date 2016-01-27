package at.yawk.cdl;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

/**
 * @author yawkat
 */
@Getter
@Setter
public class StringEditor extends AbstractEditor<String> {
    public static final String DEFAULT_PATTERN = ".*";

    @JsonProperty("default")
    private String defaultValue = "";
    // we don't use the java.util.Pattern version here to maintain GWT compatibility
    private String pattern = DEFAULT_PATTERN;

    @Override
    public void validate(String value) throws ValidationException {
        expect(value.matches(pattern), "Value does not match input pattern");
    }

    @Override
    public String mergeDefaultValue(String configValue) {
        return configValue == null ? defaultValue : configValue;
    }

    @Override
    public String mergeDefaultValueIfCompatible(Object configValue) {
        return configValue instanceof String ?
                mergeDefaultValue((String) configValue)
                : defaultValue;
    }
}
