package at.yawk.cdl;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.Collections;
import java.util.Map;
import lombok.Getter;
import lombok.Setter;

/**
 * @author yawkat
 */
@Getter
@Setter
public class ChoiceEditor extends AbstractEditor<String> {
    private Map<String, String> choices = Collections.emptyMap();
    @JsonProperty("default")
    private String defaultChoice;

    @Override
    public void validate(String s) throws ValidationException {
        if (!choices.containsKey(s)) {
            throw new ValidationException("Invalid choice");
        }
    }

    @Override
    public String getDefaultValue() {
        return defaultChoice;
    }

    @Override
    public String mergeDefaultValue(String configValue) {
        return configValue == null ? defaultChoice : configValue;
    }
}
