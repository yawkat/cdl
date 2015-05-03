package at.yawk.cdl;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;

/**
 * @author yawkat
 */
// type property
@JsonTypeInfo(
        use = JsonTypeInfo.Id.NAME,
        include = JsonTypeInfo.As.PROPERTY,
        property = "type"
)
@JsonSubTypes({
        @JsonSubTypes.Type(value = BooleanEditor.class, name = "bool"),
        @JsonSubTypes.Type(value = IntegerEditor.class, name = "int"),
        @JsonSubTypes.Type(value = StringEditor.class, name = "str"),
        @JsonSubTypes.Type(value = ObjectEditor.class, name = "object"),
        @JsonSubTypes.Type(value = ChoiceEditor.class, name = "choice"),
})
// don't include default values
@JsonInclude(JsonInclude.Include.NON_DEFAULT)
// use fields only
@JsonAutoDetect(
        creatorVisibility = JsonAutoDetect.Visibility.NONE,
        isGetterVisibility = JsonAutoDetect.Visibility.NONE,
        getterVisibility = JsonAutoDetect.Visibility.NONE,
        setterVisibility = JsonAutoDetect.Visibility.NONE,
        fieldVisibility = JsonAutoDetect.Visibility.ANY
)
public interface Editor<T> {
    void validate(T t) throws ValidationException;

    T getDefaultValue();

    T mergeDefaultValue(T configValue);

    String getDescription();
}
