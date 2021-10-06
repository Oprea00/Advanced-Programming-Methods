package Model.type;

import Model.value.Value;

public interface Type {
    Value getDefaultValue();
    Type deepCopy();
}
