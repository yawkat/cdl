package at.yawk.cdl;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

/**
 * @author yawkat
 */
@Getter
@Setter
public class BooleanEditor extends AbstractEditor<Boolean> {
    @JsonProperty("default")
    private boolean defaultValue;

    @Override
    public void validate(Boolean b) throws ValidationException {}

    @Override
    public Boolean getDefaultValue() {
        return defaultValue;
    }

    @Override
    public Boolean mergeDefaultValue(Boolean configValue) {
        return configValue == null ? defaultValue : configValue;
    }
}
