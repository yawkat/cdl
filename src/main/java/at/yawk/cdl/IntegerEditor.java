package at.yawk.cdl;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

/**
 * @author yawkat
 */
@Getter
@Setter
public class IntegerEditor extends AbstractEditor<Integer> {
    @JsonProperty("default")
    private int defaultValue = 0;
    private int min = Integer.MIN_VALUE;
    private int max = Integer.MAX_VALUE;

    @Override
    public void validate(Integer value) throws ValidationException {
        expect(value >= min, "Value too small (minimum is {})", min);
        expect(value <= max, "Value too large (maximum is {})", max);
    }

    @Override
    public Integer getDefaultValue() {
        return defaultValue;
    }

    @Override
    public Integer mergeDefaultValue(Integer configValue) {
        return configValue == null ? defaultValue : configValue;
    }
}
