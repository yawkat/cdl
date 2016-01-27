package at.yawk.cdl;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import lombok.Getter;
import lombok.Setter;

/**
 * @author yawkat
 */
@Getter
@Setter
public class ObjectEditor extends AbstractEditor<Map<String, Object>> {
    private Map<String, Editor<?>> entries = Collections.emptyMap();

    @SuppressWarnings("unchecked")
    @Override
    public void validate(Map<String, Object> value) throws ValidationException {
        for (Map.Entry<String, Object> entry : value.entrySet()) {
            Editor editor = entries.get(entry.getKey());
            expect(editor != null, "Key not found");
            editor.validate(entry.getValue());
        }
    }

    @Override
    public Map<String, Object> getDefaultValue() {
        Map<String, Object> defaults = new HashMap<>();
        for (Map.Entry<String, Editor<?>> entry : entries.entrySet()) {
            defaults.put(entry.getKey(), entry.getValue().getDefaultValue());
        }
        return defaults;
    }

    @Override
    public Map<String, Object> mergeDefaultValue(Map<String, Object> configValue) {
        Map<String, Object> def = getDefaultValue();
        if (configValue != null) {
            def = new HashMap<>(def);
            for (Map.Entry<String, Object> entry : configValue.entrySet()) {
                Editor editor = entries.get(entry.getKey());
                if (editor != null) {
                    def.put(entry.getKey(), editor.mergeDefaultValueIfCompatible(entry.getValue()));
                }
            }
        }
        return def;
    }

    @SuppressWarnings("unchecked")
    @Override
    public Map<String, Object> mergeDefaultValueIfCompatible(Object configValue) {
        return configValue instanceof Map ?
                mergeDefaultValue((Map<String, Object>) configValue)
                : getDefaultValue();
    }
}
